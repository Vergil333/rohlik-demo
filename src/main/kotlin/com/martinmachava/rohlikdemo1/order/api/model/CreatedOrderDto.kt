package com.martinmachava.rohlikdemo1.order.api.model

import java.util.*

data class CreatedOrderDto(
    val orderId: UUID,
    val reservedProducts: Set<ProductQuantityDto>,
    val insufficientProducts: Set<ProductQuantityDto>,
    val notFoundProducts: Set<UUID>,
)
