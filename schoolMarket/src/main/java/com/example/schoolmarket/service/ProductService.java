package com.example.schoolmarket.service;

import com.example.schoolmarket.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {
    List<Product> GetAllProduct();
    Product GetProductById(Integer id);
    List<Product> GetProductByUserId(Integer id);
    List<Product> GetProductByType(String type);
    String AddProduct(Product product);
    String DeleteProductById(Integer id);
    String UpdateProduct(Product product);
    Map<String, String> uploadImg(MultipartFile file);
}
