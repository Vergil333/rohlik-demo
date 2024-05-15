package com.martinmachava.rohlikdemo1.product.service.model

import java.util.UUID

data class ProductDomain(
    val id: UUID,
    val name: String,
    val price: Double,
    val quantity: Long,
)
