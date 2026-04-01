package com.firstproject.springecom.service;

import com.firstproject.springecom.model.Product;
import com.firstproject.springecom.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(new Product(-1));
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImage(image.getBytes());

        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProduct(keyword);
    }
}
