package com.martinmachava.rohlikdemo1.order.api

import com.martinmachava.rohlikdemo1.order.service.OrderFacade
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/order"])
class OrderController(
    private val orderFacade: OrderFacade,
) {

//    @PostMapping
//    fun create(@RequestBody newOrderDto: CreateOrderDto): ResponseEntity<OrderDto> = orderFacade
//        .create(newOrderDto = newOrderDto)
//        .let { ResponseEntity.created(URI.create("/order/${it.id}")).body(it) }
//
//    @GetMapping(value = ["/{id}"])
//    fun getOrder(@PathVariable id: UUID): ResponseEntity<OrderDto> = orderFacade.getOrder(orderId = id)
//        ?.let { ResponseEntity.ok(it) }
//        ?: ResponseEntity.notFound().build()
//
//    @DeleteMapping(value = ["/{id}"])
//    fun delete(@PathVariable id: UUID) = orderFacade.delete(orderId = id)
}
