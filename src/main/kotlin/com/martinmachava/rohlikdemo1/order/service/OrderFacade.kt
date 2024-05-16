package com.martinmachava.rohlikdemo1.order.service

import org.springframework.stereotype.Component

@Component
class OrderFacade(
    private val orderService: OrderService,
) {

//    fun create(newOrderDto: CreateOrderDto): OrderDto = orderService
//        .create(newOrderDomain = newOrderDto.toDomain())
//        .toDto()
//
//    fun getOrder(orderId: UUID): OrderDto? = orderService.getOrder(orderId = orderId)?.toDto()
//
//    fun delete(orderId: UUID) = orderService.delete(orderId = orderId)
}
