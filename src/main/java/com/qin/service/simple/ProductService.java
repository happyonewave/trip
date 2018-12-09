package com.qin.service.simple;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.Product;
import com.qin.model.simple.ProductExample;

import java.util.List;

public interface ProductService {

    public boolean addProduct(Product product);

    public boolean editProduct(Product product);

    public Product findProductById(Integer productId);

    List<Product> selectByExample(ProductExample example);

    public List<Product> findProductByKeywords(String keywords);

    public PageInfo<Product> findProductByPage(Integer pageNum, String keywords);

    public PageInfo<Product> findProductByPage1(Integer pageNum, String keywords);

    public PageInfo<Product> findProductByPage2(Integer pageNum, String keywords);

}