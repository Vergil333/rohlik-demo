package com.martinmachava.rohlikdemo1.product.service

import com.martinmachava.rohlikdemo1.config.logger
import com.martinmachava.rohlikdemo1.product.db.ProductAdapter
import com.martinmachava.rohlikdemo1.product.service.model.CreateProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.ProductDomain
import com.martinmachava.rohlikdemo1.product.service.model.UpdateProductDomain
import org.slf4j.Logger
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productAdapter: ProductAdapter,
) {
    private val logger: Logger = logger()

    fun create(newProductDomain: CreateProductDomain): ProductDomain = productAdapter
        .create(newProduct = newProductDomain)

    fun getProduct(productId: UUID): ProductDomain? = productAdapter.getProduct(productId = productId)

    fun getProducts(productIds: Collection<UUID>): Set<ProductDomain> = productAdapter
        .getProducts(productIds = productIds)

    fun getProducts(): Set<ProductDomain> = productAdapter.getProducts()

    fun update(id: UUID, update: UpdateProductDomain) = productAdapter.update(id = id, update = update)

    fun delete(productId: UUID) = productAdapter.delete(productId = productId)

    fun increaseQuantity(productId: UUID, quantity: Long) = productAdapter.increaseQuantity(
        productId = productId,
        addQuantity = quantity
    ) ?: logger.warn("Product (ID: $productId) quantity not increased, product is probably deleted")

    fun decreaseQuantity(productId: UUID, quantity: Long) = productAdapter.decreaseQuantity(
        productId = productId,
        subtractQuantity = quantity
    ) ?: logger.warn("Product (ID: $productId) quantity not decreased, product is probably deleted")
}
