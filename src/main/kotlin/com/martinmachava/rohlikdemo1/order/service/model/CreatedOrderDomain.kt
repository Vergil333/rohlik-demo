package com.martinmachava.rohlikdemo1.order.service.model

import java.util.*

data class CreatedOrderDomain(
    val orderId: UUID,
    val reservedProducts: Set<ProductQuantityDomain>,
    val insufficientProducts: Set<ProductQuantityDomain>,
    val notFoundProducts: Set<UUID>,
)
