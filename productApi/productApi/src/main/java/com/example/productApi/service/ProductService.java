package com.example.productApi.service;

import com.example.productApi.exception.InvalidInputDataException;
import com.example.productApi.exception.ProductNotFoundException;
import com.example.productApi.model.Product;
import com.example.productApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(Long id){
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return product.get();
    }

    public Product createProduct(Product product){
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputDataException("Price should be positive");
        }
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        if(repository.existsById(id)){
            product.setId(id);
            return repository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(Long id){
        if(repository.existsById(id)){
            repository.delete(getProductById(id));
            return true;
        }
        return false;
    }
}

