package com.martinmachava.rohlikdemo1.order.db

import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class OrderAdapter(
    private val repository: OrderRepository,
) {

    fun findPendingOrdersCreatedBefore(createdAt: ZonedDateTime) = repository.findAllByStatusAndCreatedAtBefore(
        status = Status.PENDING,
        createdAt = createdAt,
    )
//    fun create(newOrder: CreateOrderDomain) = newOrder.toEntity()
//        .let { repository.save(it) }
//        .toDomain()
//
//    fun getOrder(orderId: UUID): OrderDomain? = repository.findById(orderId).getOrNull()?.toDomain()
//
//    fun delete(orderId: UUID) = repository.findById(orderId)
//        .ifPresent { repository.delete(it) }
}
