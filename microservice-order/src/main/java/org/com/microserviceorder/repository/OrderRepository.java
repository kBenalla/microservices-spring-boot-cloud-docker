package org.com.microserviceorder.repository;

import org.com.microserviceorder.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
