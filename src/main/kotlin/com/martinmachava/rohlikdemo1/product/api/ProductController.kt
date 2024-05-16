package com.martinmachava.rohlikdemo1.product.api

import com.martinmachava.rohlikdemo1.product.api.model.CreateProductDto
import com.martinmachava.rohlikdemo1.product.api.model.ProductDto
import com.martinmachava.rohlikdemo1.product.api.model.UpdateProductDto
import com.martinmachava.rohlikdemo1.product.service.ProductFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping(value = ["/product"])
class ProductController(
    private val productFacade: ProductFacade,
) {

    @PostMapping
    fun create(@RequestBody newProductDto: CreateProductDto): ResponseEntity<ProductDto> = productFacade
        .create(newProductDto = newProductDto)
        .let { ResponseEntity.created(URI.create("/product/${it.id}")).body(it) }

    @GetMapping
    fun getProducts(): ResponseEntity<Set<ProductDto>> = productFacade.getProducts()
        .let { ResponseEntity.ok(it) }

    @GetMapping(value = ["/{id}"])
    fun getProduct(@PathVariable id: UUID): ResponseEntity<ProductDto> = productFacade.getProduct(productId = id)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()

    @PutMapping(value = ["/{id}"])
    fun updateProduct(@PathVariable id: UUID, @RequestBody update: UpdateProductDto): ResponseEntity<ProductDto> =
        productFacade.updateProduct(id = id, update = update)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        productFacade.delete(productId = id)
        return ResponseEntity.ok().build()
    }
}
