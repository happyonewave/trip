package com.qin;

import com.qin.model.simple.Comments;
import com.qin.service.simple.CommentsService;
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
public class CommentsServiceTest {

    private static final Logger log = LoggerFactory.getLogger(CommentsServiceTest.class);

    @Autowired
    private CommentsService commentsService;


    @Test
    public void addComments() {
        try {
            log.info("# 生产测试数据");
            Comments comments = null;
            for (int i = 1; i < 1001; i++) {
                comments = new Comments();
                comments.setId(i + 21);
                comments.setUserName("test_name"+i);
                comments.setProductId(i);
                comments.setText("test_text"+i);
                comments.setTime(new Date(Calendar.getInstance().getTime().getYear(),new Random().nextInt(12),new Random().nextInt(30)+1));
                switch (new Random().nextInt(3)){
                  case 0:
                      comments.setGrade("不满意");
                      break;
                  case 1:
                      comments.setGrade("一般");
                      break;
                  case 2:
                      comments.setGrade("满意");
                      break;
                  default:
                      break;
                 }
//                comments.setDate(Calendar.getInstance().getTime());
//                comments.setCreateTime(Calendar.getInstance().getTime());
                commentsService.addComments(comments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectComments() {
        try {
            log.info("# 查询测试数据");
            List<Comments> commentss = commentsService.select();
            for (Comments comments : commentss) {
                log.info(comments.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
