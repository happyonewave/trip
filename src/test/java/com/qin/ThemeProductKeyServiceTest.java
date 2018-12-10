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

import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ThemeProductKeyServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ThemeProductKeyServiceTest.class);
    @Autowired
    private ThemeProductMapper themeProductMapper;

    @Test
    public void addThemeProduct() {
        try {
            log.info("# 生产测试数据");
            ThemeProductKey themeProductKey = null;
            for (int i = 6; i < 1007; i++) {
                themeProductKey = new ThemeProductKey();
                themeProductKey.setThemeId(new Random().nextInt(3) + 1);
                themeProductKey.setProductId(i);
//                product.setDate(Calendar.getInstance().getTime());
//                product.setCreateTime(Calendar.getInstance().getTime());
                themeProductMapper.insertSelective(themeProductKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
