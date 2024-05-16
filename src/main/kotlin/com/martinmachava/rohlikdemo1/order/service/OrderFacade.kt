package com.martinmachava.rohlikdemo1.order.service

import com.martinmachava.rohlikdemo1.order.api.model.CreateOrderDto
import com.martinmachava.rohlikdemo1.order.api.model.CreatedOrderDto
import com.martinmachava.rohlikdemo1.order.api.model.ProductQuantityDto
import com.martinmachava.rohlikdemo1.order.service.model.CreateOrderDomain
import com.martinmachava.rohlikdemo1.order.service.model.CreatedOrderDomain
import com.martinmachava.rohlikdemo1.order.service.model.ProductQuantityDomain
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderFacade(
    private val orderService: OrderService,
) {

    fun create(newOrderDto: CreateOrderDto): CreatedOrderDto = orderService
        .create(newOrderDomain = newOrderDto.toDomain())
        .toDto()

//    fun getOrder(orderId: UUID): OrderDto? = orderService.getOrder(orderId = orderId)?.toDto()

    /**
     * @return false if order was not found, true otherwise
     */
    fun cancel(orderId: UUID) = orderService.cancel(orderId = orderId)
}

private fun CreateOrderDto.toDomain() = CreateOrderDomain(
    products = this.products.map { it.toDomain() }
)

private fun ProductQuantityDto.toDomain() = ProductQuantityDomain(
    productId = this.productId,
    quantity = this.quantity,
)

private fun CreatedOrderDomain.toDto() = CreatedOrderDto(
    orderId = this.orderId,
    reservedProducts = this.reservedProducts.map { it.toDto() }.toSet(),
    insufficientProducts = this.insufficientProducts.map { it.toDto() }.toSet(),
    notFoundProducts = this.notFoundProducts,
)

private fun ProductQuantityDomain.toDto() = ProductQuantityDto(
    productId = this.productId,
    quantity = this.quantity,
)
