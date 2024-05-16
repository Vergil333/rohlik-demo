package com.martinmachava.rohlikdemo1.order.service.model

data class CreateOrderDomain(
    val products: Collection<ProductQuantityDomain>,
)
