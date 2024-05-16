package com.martinmachava.rohlikdemo1.order.db

import com.martinmachava.rohlikdemo1.order.service.model.ProductQuantityDomain
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

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

//    fun delete(orderId: UUID) = repository.findById(orderId)
//        .ifPresent { repository.delete(it) }
}

//private fun CreateOrderDomain.toEntity() = OrderEntity(
//    id = UUID.randomUUID(),
//    createdAt = ZonedDateTime.now(),
//    paidAt = null,
//    status = Status.PENDING,
//    products = this.products.associate { it.productId to it.quantity }
//)

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
