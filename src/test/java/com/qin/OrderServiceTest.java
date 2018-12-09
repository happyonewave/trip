package com.qin;

import com.qin.model.simple.Order;
import com.qin.service.simple.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceTest.class);

    @Autowired
    private OrderService orderService;

    @Test
    public void addOrder() {
        try {
            log.info("# 生产测试数据");
            Order order = null;
            for (int i = 1; i < 1001; i++) {
                order = new Order();
                order.setId(i + 99);
                order.setOrderNo("test_" + i);
                order.setUserId(i);
                order.setTotalPrice(Integer.toUnsignedLong(i));
                order.setStatus(new Random().nextInt(6) + 1);
                order.setDate(Calendar.getInstance().getTime());
                order.setTotalCount(i);
                order.setProductId(i);
                order.setContactName("test_name" + i);
                order.setPhone("test_phone" + i);
                order.setIdcard("test_idcard" + i);
                order.setSnapImg("test_img" + i);
                order.setSnapName("test_productname" + i);
                order.setEmail("test_email" + i);
                order.setComments(new Random().nextInt(2));
//                order.setDate(Calendar.getInstance().getTime());
//                order.setCreateTime(Calendar.getInstance().getTime());
                orderService.addOrder(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
