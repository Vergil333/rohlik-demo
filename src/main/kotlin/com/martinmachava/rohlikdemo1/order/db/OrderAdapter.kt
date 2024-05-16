package com.martinmachava.rohlikdemo1.order.db

import com.martinmachava.rohlikdemo1.order.service.model.ProductQuantityDomain
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Component
class OrderAdapter(
    private val repository: OrderRepository,
) {

    fun findPendingOrdersCreatedBefore(createdAt: ZonedDateTime) = repository.findAllByStatusAndCreatedAtBefore(
        status = Status.PENDING,
        createdAt = createdAt,
    )

    /**
     * @return created Order ID with Product Quantities
     */
    fun create(newOrder: Collection<ProductQuantityDomain>): Pair<UUID, List<ProductQuantityDomain>> =
        newOrder.toNewOrderEntity()
            .let { repository.save(it) }
            .toCreatedOrderDomain()

//    fun getOrder(orderId: UUID): OrderDomain? = repository.findById(orderId).getOrNull()?.toDomain()

    /**
     * Cancel pending order and releases the products quantities
     *
     * @return products from the order to release or null if order is not found
     */
    fun cancel(orderId: UUID) = repository.findById(orderId).getOrNull()?.let {
        require(it.status == Status.PENDING) {
            "Order with id $orderId could not be cancelled (is not in pending state)."
        }
        it.apply { status = Status.CANCELLED }
        repository.save(it)
    }?.products
}

private fun Collection<ProductQuantityDomain>.toNewOrderEntity() = OrderEntity(
    id = UUID.randomUUID(),
    createdAt = ZonedDateTime.now(),
    paidAt = null,
    status = Status.PENDING,
    products = this.associate { it.productId to it.quantity }
)

/**
 * @return created Order ID with Product Quantities
 */
private fun OrderEntity.toCreatedOrderDomain() = this.products.map {
    ProductQuantityDomain(
        productId = it.key,
        quantity = it.value,
    )
}.let { this.id to it }
