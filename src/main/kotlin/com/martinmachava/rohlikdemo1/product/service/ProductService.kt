package com.martinmachava.rohlikdemo1.product.service

import com.martinmachava.rohlikdemo1.product.db.ProductAdapter
import com.martinmachava.rohlikdemo1.product.service.model.CreateProductDomain
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productAdapter: ProductAdapter,
) {

    fun create(newProductDomain: CreateProductDomain) = productAdapter.create(newProduct = newProductDomain)
}
