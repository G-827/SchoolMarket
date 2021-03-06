package com.example.schoolmarket.service;

import com.example.schoolmarket.dao.ProductDao;
import com.example.schoolmarket.entity.Product;
import com.example.schoolmarket.other.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Value("${absoluteImgPath}")
    String absoluteImgPath;

    @Value("${sonImgPath}")
    String sonImgPath;

    @Override
    public List<Product> GetAllProduct() {

        try {
            List<Product> products = productDao.GetAllProduct();
            return  products;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public List<Product> GetProduct() {
        try {
            List<Product> products = productDao.GetProduct();
            return  products;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public Product GetProductById(Integer id) {

        try {
            Product thisproduct = productDao.GetProductById(id);
            return  thisproduct;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public List<Product> GetProductByUserId(Integer id) {
        try {
            return productDao.GetProductByUserId(id);
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public List<Product> GetProductByType(String type) {
        try {
            return productDao.GetProductByType(type);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public String AddProduct(Product product) {
        try {

            int i = productDao.AddProduct(product);
            return "????????????" + i + "?????????";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public String DeleteProductById(Integer id) {
        try {
            int i = productDao.DeleteProductById(id);
            return "????????????" +i + "?????????";
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    @Override
    public String UpdateProduct(Product product) {
        try {
            int i = productDao.UpdateProduct(product);
            return "????????????" +i + "?????????";
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    //????????????????????????????????????????????????????????????????????????????????????1.??????????????????????????? 2.???imgurl?????????????????????
    @Override
    public Map<String, String> uploadImg(MultipartFile file) {
        Map<String,String> map = new HashMap<>();
        if(file.isEmpty()){
            map.put("code","500");
            map.put("msg","??????????????????");
            return map;
        }
        String originalFilename = file.getOriginalFilename();
        //?????????????????????
        String fileName = RandomUtils.createRandomString(10) + originalFilename;
        File dest = new File(absoluteImgPath + fileName);
        String imgUrl = sonImgPath +fileName;
        try{
            file.transferTo(dest);
            map.put("code","200");
            map.put("msg","????????????");
            map.put("img_url",imgUrl);
            return map;
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public String UpdateStateById(Integer id) {
        try {
            int i = productDao.UpdateStateById(id);
            return "????????????"+ i +"?????????";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
