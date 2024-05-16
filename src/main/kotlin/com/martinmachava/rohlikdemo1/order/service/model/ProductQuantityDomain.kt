package com.martinmachava.rohlikdemo1.order.service.model

import java.util.*

data class ProductQuantityDomain(
    val productId: UUID,
    val quantity: Long,
)
