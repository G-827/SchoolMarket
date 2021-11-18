package com.example.schoolmarket.dao;

import com.example.schoolmarket.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {
    @Select("select * from product WHERE state = 0 ORDER BY datetime desc ")
    List<Product> GetAllProduct();

    @Select("SELECT * FROM product WHERE id = #{id} ")
    Product GetProductById(Integer id);

    @Select("SELECT * FROM product WHERE owner_id = #{id} ")
    List<Product> GetProductByUserId(Integer id);

    @Select("SELECT * FROM product WHERE type = #{type}")
    List<Product> GetProductByType(String type);

    @Insert("INSERT into product(productname,price,type,detail,img_url,owner_id,owner_name,datetime) values(#{productname},#{price},#{type},#{detail},#{img_url},#{owner_id},#{owner_name},#{datetime})")
    int AddProduct(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    int DeleteProductById(Integer id);

    @Update("UPDATE product " +
            "SET productname = #{productname} , price = #{price} , type = #{type} , detail = #{detail}" +
            "WHERE id = #{id}")
    int UpdateProduct(Product product);

}
