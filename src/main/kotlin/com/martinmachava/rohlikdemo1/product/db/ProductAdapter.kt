package com.martinmachava.rohlikdemo1.product.db

import com.martinmachava.rohlikdemo1.product.service.model.CreateProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.ProductDomain
import org.springframework.stereotype.Component
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Component
class ProductAdapter(
    private val repository: ProductRepository,
) {

    fun create(newProduct: CreateProductDomain) = newProduct.toEntity()
        .let { repository.save(it) }
        .toDomain()

    fun getProduct(productId: UUID) = repository.findById(productId).getOrNull()?.toDomain()

    fun delete(productId: UUID) = repository.findById(productId)
        .ifPresent { repository.delete(it) }
}

private fun CreateProductDomain.toEntity() = ProductEntity(
    id = UUID.randomUUID(),
    name = this.name,
    price = this.price,
    quantity = this.quantity,
)

private fun ProductEntity.toDomain() = ProductDomain(
    id = this.id,
    name = this.name,
    price = this.price,
    quantity = this.quantity,
)
