package com.martinmachava.rohlikdemo1.order.service

import com.martinmachava.rohlikdemo1.order.db.OrderAdapter
import com.martinmachava.rohlikdemo1.order.db.Status
import com.martinmachava.rohlikdemo1.order.service.model.CreateOrderDomain
import com.martinmachava.rohlikdemo1.order.service.model.CreatedOrderDomain
import com.martinmachava.rohlikdemo1.order.service.model.ProductQuantityDomain
import com.martinmachava.rohlikdemo1.product.service.ProductService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class OrderService(
    private val orderAdapter: OrderAdapter,
    private val productService: ProductService,
) {

    @Transactional
    fun create(newOrderDomain: CreateOrderDomain): CreatedOrderDomain {
        val actualProducts = productService.getProducts(productIds = newOrderDomain.products.map { it.productId })

        val insufficientProducts = mutableMapOf<UUID, Long>()
        val notFoundProducts: MutableSet<UUID> = mutableSetOf()
        val productsToReserve = mutableListOf<ProductQuantityDomain>()

        actualProducts.forEach { product ->
            val requestedQuantity = newOrderDomain.products.find { it.productId == product.id }?.quantity
            if (requestedQuantity == null) {
                notFoundProducts.add(product.id)
                return@forEach
            }

            val availableQuantityToReserve = if (product.quantity < requestedQuantity) {
                insufficientProducts[product.id] = requestedQuantity - product.quantity
                product.quantity
            } else requestedQuantity

            productService.decreaseQuantity(product.id, availableQuantityToReserve)
            productsToReserve.add(ProductQuantityDomain(
                productId = product.id,
                quantity = availableQuantityToReserve,
            ))
        }

        val reservedProducts = orderAdapter.create(newOrder = productsToReserve)

        return CreatedOrderDomain(
            orderId = reservedProducts.first,
            reservedProducts = reservedProducts.second.toSet(),
            insufficientProducts = insufficientProducts.toProductQuantity().toSet(),
            notFoundProducts = notFoundProducts,
        )
    }

//    fun getOrder(orderId: UUID): OrderDomain? = orderAdapter.getOrder(orderId = orderId)
//
//    fun delete(orderId: UUID) = orderAdapter.delete(orderId = orderId)

    @Transactional
    fun expirePendingOrders(expirationTime: ZonedDateTime) {
        val pendingOrders = orderAdapter.findPendingOrdersCreatedBefore(expirationTime)
        pendingOrders.forEach { pendingOrder ->
            pendingOrder.status = Status.EXPIRED
            pendingOrder.products.forEach {
                productService.increaseQuantity(productId = it.key, quantity = it.value)
            }
        }
    }
}

private fun Map<UUID, Long>.toProductQuantity() = this.map {
    ProductQuantityDomain(
        productId = it.key,
        quantity = it.value,
    )
}
