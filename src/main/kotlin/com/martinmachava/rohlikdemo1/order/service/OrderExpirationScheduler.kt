package com.martinmachava.rohlikdemo1.order.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class OrderExpirationScheduler(
    private val orderService: OrderService,
) {
    @Scheduled(fixedDelay = 60000) // run every minute
    fun expirePendingOrders() {
        val expirationTime = ZonedDateTime.now().minusMinutes(30)
        orderService.expirePendingOrders(expirationTime)
    }
}
