package com.martinmachava.rohlikdemo1.product.api.model

import java.util.*

data class ProductDto(
    val id: UUID,
    val name: String,
    val price: Double,
    val quantity: Long,
)
