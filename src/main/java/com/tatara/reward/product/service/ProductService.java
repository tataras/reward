package com.tatara.reward.product.service;

import com.tatara.reward.exceptions.ObjectNotExistException;
import com.tatara.reward.product.model.ProductEntity;
import com.tatara.reward.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void save(ProductEntity product) {
        productRepository.save(product);
    }

    public ProductEntity findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotExistException("Product with id " + productId + " not found"));
    }
}
