package org.com.microserviceproduct.service;

import lombok.AllArgsConstructor;
import org.com.microserviceproduct.dtos.ProductRequest;
import org.com.microserviceproduct.dtos.ProductResponse;
import org.com.microserviceproduct.entity.Product;
import org.com.microserviceproduct.exceptions.ProductNotFoundException;
import org.com.microserviceproduct.mapper.ProductMapper;
import org.com.microserviceproduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public Product findProductEntityById(Integer id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<ProductResponse> findAllProducts(){
        List<Product> products=productRepository.findAll();
        if (products.isEmpty()) throw new ProductNotFoundException();
        return productMapper.mapToListProductResponse(products);
    }

    public ProductResponse findProductById(Integer id){
        Product product=findProductEntityById(id);
        return productMapper.mapToProductResponse(product);
    }

    public ProductResponse saveProduct(ProductRequest productRequest){
        Product product=productMapper.mapToProductEntity(productRequest);
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    public ProductResponse updateProduct(Integer id, ProductRequest productRequest){
        Product existingProduct=findProductEntityById(id);
        productMapper.mapToEntity(productRequest,existingProduct);
        productRepository.save(existingProduct);
        return productMapper.mapToProductResponse(existingProduct);
    }

    public ProductResponse updateProductByPieces(Integer id, Integer numberOfPieces){

        Product product=findProductEntityById(id);
        Integer pieces= product.getPieces();
        if (numberOfPieces > pieces || pieces.equals(0)) throw new ProductNotFoundException();
        Integer newPieces=pieces - numberOfPieces;
        product.setPieces(newPieces);
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    public void  deleteProductById(Integer id){
        Product product=findProductEntityById(id);
        productRepository.delete(product);
    }
}
