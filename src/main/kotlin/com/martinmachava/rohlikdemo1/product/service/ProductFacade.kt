package com.martinmachava.rohlikdemo1.product.service

import com.martinmachava.rohlikdemo1.product.api.model.CreateProductDto
import com.martinmachava.rohlikdemo1.product.api.model.ProductDto
import com.martinmachava.rohlikdemo1.product.api.model.UpdateProductDto
import com.martinmachava.rohlikdemo1.product.service.model.CreateProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.ProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.UpdateProductDomain
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductFacade(
    private val productService: ProductService,
) {

    fun create(newProductDto: CreateProductDto): ProductDto = productService
        .create(newProductDomain = newProductDto.toDomain())
        .toDto()

    fun getProduct(productId: UUID): ProductDto? = productService.getProduct(productId = productId)?.toDto()

    fun updateProduct(id: UUID, update: UpdateProductDto): ProductDto? = productService.update(
        id = id,
        update = update.toDomain(),
    )
        ?.toDto()

    fun delete(productId: UUID) = productService.delete(productId = productId)
}

private fun ProductDomain.toDto() = ProductDto(
    id = this.id,
    name = this.name,
    price = this.price,
    quantity = this.quantity,
)

private fun CreateProductDto.toDomain() = CreateProductDomain(
    name = this.name,
    price = this.price,
    quantity = this.quantity,
)

private fun UpdateProductDto.toDomain() = UpdateProductDomain(
    name = this.name,
    price = this.price,
    quantity = this.quantity,
)
