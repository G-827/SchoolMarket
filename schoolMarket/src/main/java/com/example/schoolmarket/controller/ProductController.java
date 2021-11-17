package com.example.schoolmarket.controller;

import com.example.schoolmarket.entity.Product;
import com.example.schoolmarket.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @GetMapping("product/getallproduct")
    @ResponseBody
    public List<Product> getProductList()
    {
        return productService.GetAllProduct();
    }

    @GetMapping("product/getproductbyid")
    @ResponseBody
    public Product getProductById(Integer id){ return productService.GetProductById(id);}

    @GetMapping("product/getproductbyuserid")
    @ResponseBody
    public List<Product> getProductByUserId(Integer id)
    {
        return productService.GetProductByUserId(id);
    }

    @PostMapping("product/addproduct")
    @ResponseBody
    public String addProduct(Product product)
    {
        return productService.AddProduct(product);
    }

    @DeleteMapping("product/deleteproductbyid")
    @ResponseBody
    public String deleteProductById(Integer id)
    {
        return productService.DeleteProductById(id);
    }

    @PutMapping("product/updateproduct")
    @ResponseBody
    public String updateProduct(Product product){
        return productService.UpdateProduct(product);
    }

    @RequestMapping("product/addimage")
    @ResponseBody
    public Map uploadImg(@RequestParam(value = "file",required = false) MultipartFile file){
        return productService.uploadImg(file);
    }
}
