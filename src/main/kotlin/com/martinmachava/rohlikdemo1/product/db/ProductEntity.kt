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
    var name: String,
    var price: Double,
    var quantity: Long,
)
