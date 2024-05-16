package com.martinmachava.rohlikdemo1.order.db

import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime
import java.util.*


interface OrderRepository : JpaRepository<OrderEntity, UUID> {
    fun findAllByStatusAndCreatedAtBefore(status: Status, createdAt: ZonedDateTime): List<OrderEntity>
}
