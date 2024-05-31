package org.com.microserviceproduct.controller;

import lombok.AllArgsConstructor;
import org.com.microserviceproduct.dtos.ProductRequest;
import org.com.microserviceproduct.dtos.ProductResponse;
import org.com.microserviceproduct.entity.Product;
import org.com.microserviceproduct.repository.ProductRepository;
import org.com.microserviceproduct.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse saveProduct(@RequestBody ProductRequest productRequest){
        return productService.saveProduct(productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Integer id,
                                         @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id,productRequest);
    }

    @PutMapping("/{id}/pieces")
    public ProductResponse updateProductByPieces(@PathVariable("id") Integer id,
                                                 @RequestParam Integer numberOfPieces){
        return productService.updateProductByPieces(id,numberOfPieces);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("id") Integer id){
        productService.deleteProductById(id);
    }


}
