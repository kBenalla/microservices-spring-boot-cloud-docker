package org.com.microserviceorder.model;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer pieces;
}
