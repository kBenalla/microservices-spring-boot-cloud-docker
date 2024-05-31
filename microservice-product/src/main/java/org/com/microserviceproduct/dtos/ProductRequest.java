package org.com.microserviceproduct.dtos;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer pieces;
}
