package org.com.microserviceproduct.mapper;

import org.com.microserviceproduct.dtos.ProductRequest;
import org.com.microserviceproduct.dtos.ProductResponse;
import org.com.microserviceproduct.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper=new ModelMapper();

    public Product mapToProductEntity(ProductRequest productRequest){
        return modelMapper.map(productRequest, Product.class);
    }

    public ProductResponse mapToProductResponse(Product product){
        return modelMapper.map(product,ProductResponse.class);
    }

    public List<ProductResponse> mapToListProductResponse(List<Product> products){
        List<ProductResponse> productResponses=new ArrayList<>();
        for (Product product:products){
            productResponses.add(mapToProductResponse(product));
        }
        return productResponses;
    }
    public void mapToEntity(ProductRequest productRequest,Product product){
        modelMapper.map(productRequest,product);
    }
}
