package com.martinmachava.rohlikdemo1.order.api.model

data class CreateOrderDto(
    val products: Collection<ProductQuantityDto>,
)
