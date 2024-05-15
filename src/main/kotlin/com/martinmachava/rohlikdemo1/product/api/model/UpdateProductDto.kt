package com.martinmachava.rohlikdemo1.product.api.model

data class UpdateProductDto(
    val name: String,
    val price: Double,
    val quantity: Long,
)
