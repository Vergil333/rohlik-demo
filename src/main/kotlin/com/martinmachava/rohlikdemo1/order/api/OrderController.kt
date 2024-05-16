package com.martinmachava.rohlikdemo1.order.api

import com.martinmachava.rohlikdemo1.order.api.model.CreateOrderDto
import com.martinmachava.rohlikdemo1.order.api.model.CreatedOrderDto
import com.martinmachava.rohlikdemo1.order.service.OrderFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping(value = ["/order"])
class OrderController(
    private val orderFacade: OrderFacade,
) {

    @PostMapping
    fun create(@RequestBody newOrderDto: CreateOrderDto): ResponseEntity<CreatedOrderDto> = orderFacade
        .create(newOrderDto = newOrderDto)
        .let { ResponseEntity.created(URI.create("/order/${it.orderId}")).body(it) }

//    @GetMapping(value = ["/{id}"])
//    fun getOrder(@PathVariable id: UUID): ResponseEntity<OrderDto> = orderFacade.getOrder(orderId = id)
//        ?.let { ResponseEntity.ok(it) }
//        ?: ResponseEntity.notFound().build()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> = if (orderFacade.cancel(orderId = id))
        ResponseEntity.ok().build()
    else ResponseEntity.notFound().build()
}
