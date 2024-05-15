package com.martinmachava.rohlikdemo1.product.api.model

import com.martinmachava.rohlikdemo1.product.service.ProductFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/product"])
class ProductController(
    private val productFacade: ProductFacade,
) {

    @PostMapping
    fun create(@RequestBody newProductDto: CreateProductDto): ProductDto = productFacade
        .create(newProductDto = newProductDto)
}
