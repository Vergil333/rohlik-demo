package com.martinmachava.rohlikdemo1.order.api.model

import java.util.*

data class ProductQuantityDto(
    val productId: UUID,
    val quantity: Long,
)
