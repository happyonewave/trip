package com.qin;

import com.qin.mapper.simple.ThemeProductMapper;
import com.qin.model.simple.Product;
import com.qin.model.simple.ThemeProductKey;
import com.qin.service.simple.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

    @Autowired
    private ProductService productService;


    @Test
    public void addProduct() {
        try {
            log.info("# 生产测试数据");
            Product product = null;
            for (int i = 1; i < 1001; i++) {
                product = new Product();
                product.setId(i + 99);

//                product.setDate(Calendar.getInstance().getTime());
//                product.setCreateTime(Calendar.getInstance().getTime());
                productService.addProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectProduct() {
        try {
            log.info("# 查询测试数据");
            List<Product> products = productService.select();
            for (Product product : products) {
                log.info(product.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
