package com.martinmachava.rohlikdemo1.product.db

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    val id: UUID,
    val name: String,
    val price: Double,
    val quantity: Long,
)
