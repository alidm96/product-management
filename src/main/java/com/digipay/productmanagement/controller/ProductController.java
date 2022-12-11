package com.digipay.productmanagement.controller;

import com.digipay.productmanagement.model.dto.ProductDto;
import com.digipay.productmanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping
    public Set<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductDto update(@RequestBody ProductDto productDto,@PathVariable Long id) {
        return productService.update(productDto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}