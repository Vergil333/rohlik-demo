package com.martinmachava.rohlikdemo1.product.api.model

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

    @GetMapping(value = ["/{id}"])
    fun getProduct(@PathVariable id: UUID): ResponseEntity<ProductDto> = productFacade.getProduct(productId = id)
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value = "id") id: UUID) = productFacade.delete(productId = id)
}
