package com.digipay.productmanagement.service;

import com.digipay.productmanagement.model.dto.ProductDto;
import com.digipay.productmanagement.model.entity.Product;
import com.digipay.productmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService implements CrudService<ProductDto, Long>{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    private Product convertDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(CategoryService.convertDtoToEntity(categoryService.findById(productDto.getId())));
        return product;
    }
    @Override
    public Set<ProductDto> findAll() {
        Set<ProductDto> productDto = new HashSet<>();
        for (Product product : productRepository.findAll()) {
            productDto.add(convertEntityToDto(product));
        }
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertEntityToDto).orElse(null);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        productRepository.save(convertDtoToEntity(productDto));
        return productDto;
    }

    @Override
    public ProductDto update(ProductDto productDto, Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setName(productDto.getName());
            product.get().setDescription(productDto.getDescription());
            product.get().setPrice(productDto.getPrice());
            product.get().setQuantity(productDto.getQuantity());
            product.get().setCategory(CategoryService.convertDtoToEntity(categoryService.findById(productDto.getCategoryId())));
            productRepository.save(product.get());

            return productDto;
        }else return null;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
