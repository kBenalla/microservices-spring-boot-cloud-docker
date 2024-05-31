package org.com.microserviceproduct.repository;

import org.com.microserviceproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
