package com.firstproject.springecom.controller;

import com.firstproject.springecom.model.Product;
import com.firstproject.springecom.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        Product product = productService.getProductById(id);

        if(product.getId() > 0) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable long id){
        Product product = productService.getProductById(id);
        if(product.getId() > 0) {
            return new ResponseEntity<>(product.getImage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(
            @RequestPart Product product, @RequestPart MultipartFile imageFile
    ) {
        Product savedProduct = null;
        try {
            savedProduct = productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ) {
        Product updatedProduct = null;
        try {
            updatedProduct = productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable int id
    ) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam("keyword") String keyword
    ) {
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }
}
