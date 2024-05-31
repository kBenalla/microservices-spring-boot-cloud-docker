package org.com.microserviceorder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class OrderRequest {
    private Integer productId;
    private Integer quantity;
}
