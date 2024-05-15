package com.martinmachava.rohlikdemo1.product.db

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface ProductRepository : JpaRepository<ProductEntity, UUID>
