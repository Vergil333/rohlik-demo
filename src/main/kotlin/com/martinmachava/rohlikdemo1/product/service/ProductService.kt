package com.martinmachava.rohlikdemo1.product.service

import com.martinmachava.rohlikdemo1.product.db.ProductAdapter
import com.martinmachava.rohlikdemo1.product.service.model.CreateProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.ProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.UpdateProductDomain
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productAdapter: ProductAdapter,
) {

    fun create(newProductDomain: CreateProductDomain): ProductDomain = productAdapter
        .create(newProduct = newProductDomain)

    fun getProduct(productId: UUID): ProductDomain? = productAdapter.getProduct(productId = productId)

    fun update(id: UUID, update: UpdateProductDomain) = productAdapter.update(id = id, update = update)

    fun delete(productId: UUID) = productAdapter.delete(productId = productId)

    fun increaseQuantity(productId: UUID, quantity: Long) = productAdapter.increaseQuantity(
        productId = productId,
        addQuantity = quantity
    )
}
