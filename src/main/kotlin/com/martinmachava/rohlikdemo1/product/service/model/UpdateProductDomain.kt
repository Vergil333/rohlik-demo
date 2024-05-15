package com.martinmachava.rohlikdemo1.product.service.model

data class UpdateProductDomain(
    val name: String,
    val price: Double,
    val quantity: Long,
)
