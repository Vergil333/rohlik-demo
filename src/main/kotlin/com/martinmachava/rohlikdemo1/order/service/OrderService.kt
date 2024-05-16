package com.martinmachava.rohlikdemo1.order.service

import com.martinmachava.rohlikdemo1.order.db.OrderAdapter
import com.martinmachava.rohlikdemo1.order.db.Status
import com.martinmachava.rohlikdemo1.product.service.ProductService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class OrderService(
    private val orderAdapter: OrderAdapter,
    private val productService: ProductService,
) {

//    fun create(newOrderDomain: CreateOrderDomain): OrderDomain = orderAdapter
//        .create(newOrder = newOrderDomain)

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
        for (order in pendingOrders) {
            order.status = Status.EXPIRED
            // release reserved product quantities
            for ((productId, quantity) in order.products) {
                productService.increaseQuantity(productId, quantity)
            }
        }
    }
}
