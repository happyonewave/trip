package com.qin.service.simple.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.constants.Constants;
import com.qin.config.datasource.DataSourceEnum;
import com.qin.config.datasource.TargetDataSource;
import com.qin.mapper.simple.CommentsMapper;
import com.qin.model.simple.Comments;
import com.qin.model.simple.CommentsExample;
import com.qin.model.simple.CommentsExample.Criteria;
import com.qin.service.simple.CommentsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @Description 产品接口实现类
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    private static final Logger log = LoggerFactory.getLogger(CommentsServiceImpl.class);

    @Autowired
    private CommentsMapper commentsMapper;

    @Transactional
    @Override
    public boolean addComments(Comments comments) {
        if (comments != null) {
//            comments.setId(Integer.parseInt(FactoryAboutKey.getPK(TableEnum.PRODUCT)) );
            comments.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            int flag = commentsMapper.insert(comments);
            // if (StringUtils.equals(comments.getTitle(), "a"))
            // throw new BusinessException("001", "测试事务回溯");
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public boolean editComments(Comments comments) {
        if (comments != null && StringUtils.isNotBlank(comments.getId() + "")) {
            int flag = commentsMapper.updateByPrimaryKeySelective(comments);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public Comments findCommentsById(Integer id) {
        if (StringUtils.isBlank(id + ""))
            return null;
        else
            return commentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comments> selectByExample(CommentsExample example) {
        example = new CommentsExample();
        Criteria criteria = example.createCriteria();
//        criteria.andStockEqualTo(99);
        return commentsMapper.selectByExample(example);
    }

    @Override
    public List<Comments> select() {
        return commentsMapper.selectByExample(new CommentsExample());
    }

    // 默认数据库
    @Override
    public List<Comments> findCommentsByKeywords(String keywords) {
        log.info("# 查询默认数据库");
        return commentsMapper.findCommentsByKeywords(keywords);
    }

    // 数据库1
    @TargetDataSource(DataSourceEnum.DB1)
    @Override
    public PageInfo<Comments> findCommentsByPage1(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Comments> comments = commentsMapper.findCommentsByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Comments> page = new PageInfo<Comments>(comments);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库1 page.toString()={}", page.toString());
        return page;
    }

    // 数据库2
    @TargetDataSource(DataSourceEnum.DB2)
    @Override
    public PageInfo<Comments> findCommentsByPage2(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Comments> comments = commentsMapper.findCommentsByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Comments> page = new PageInfo<Comments>(comments);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库2 page.toString()={}", page.toString());
        return page;
    }

    @Override
    public PageInfo<Comments> findCommentsByPage(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
//        List<Comments> comments = commentsMapper.findCommentsByPage(keywords);
        CommentsExample example = new CommentsExample();
        example.setOrderByClause("time desc");

        if (keywords!=null&&!StringUtils.isBlank(keywords)){
            example.createCriteria().andProductIdEqualTo(Integer.parseInt(keywords));
        }
        List<Comments> comments = commentsMapper.selectByExampleWithBLOBs(example);
//        List<Comments> comments = commentsMapper.select();
        // 用PageInfo对结果进行包装
        PageInfo<Comments> page = new PageInfo<Comments>(comments);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }

}
