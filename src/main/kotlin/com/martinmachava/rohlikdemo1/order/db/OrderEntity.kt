package com.martinmachava.rohlikdemo1.order.db

import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "order")
class OrderEntity(
    @Id
    val id: UUID,
    val createdAt: ZonedDateTime,
    val paidAt: ZonedDateTime,
    @Enumerated(EnumType.STRING)
    var status: Status,
    @ElementCollection
    @CollectionTable(name = "order_product", joinColumns = [JoinColumn(name = "order_id")])
    var products: Map<UUID, Long> = mapOf()
)

enum class Status {
    PENDING,
    PAID,
    CANCELLED,
    EXPIRED,
}
