package org.com.microservicepayment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @Getter @Setter
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer pieces;
}
