package com.qin;

import com.qin.model.simple.User;
import com.qin.service.auth.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;


    @Test
    public void addUser() {
        try {
            log.info("# 生产测试数据");
            User user = null;
            for (int i = 1; i < 1001; i++) {
                user = new User();
                user.setId(10 + i);
                user.setNickname("test_nickname" + i);
                user.setPhone("1345678" + i + "32" + i);
//                user.setCreateTime();
//                user.setId(i + 21);
//                user.setUserName("test_name"+i);
//                user.setProductId(i);
//                user.setText("test_text"+i);
//                user.setTime(new Date(Calendar.getInstance().getTime().getYear(),new Random().nextInt(12),new Random().nextInt(30)+1));
//                switch (new Random().nextInt(3)){
//                  case 0:
//                      user.setGrade("不满意");
//                      break;
//                  case 1:
//                      user.setGrade("一般");
//                      break;
//                  case 2:
//                      user.setGrade("满意");
//                      break;
//                  default:
//                      break;
//                 }
//                user.setDate(Calendar.getInstance().getTime());
//                user.setCreateTime(Calendar.getInstance().getTime());
                userService.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectUser() {
        try {
            log.info("# 查询测试数据");
            List<User> users = userService.select();
            for (User user : users) {
                log.info(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
