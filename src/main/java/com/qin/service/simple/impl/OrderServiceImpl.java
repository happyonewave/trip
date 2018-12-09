package com.qin.service.simple.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.constants.Constants;
import com.qin.config.datasource.DataSourceEnum;
import com.qin.config.datasource.TargetDataSource;
import com.qin.mapper.simple.OrderMapper;
import com.qin.model.simple.Order;
import com.qin.model.simple.OrderExample;
import com.qin.model.simple.OrderExample.Criteria;
import com.qin.service.simple.OrderService;
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
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @Override
    public boolean addOrder(Order order) {
        if (order != null) {
//            order.setId(Integer.parseInt(FactoryAboutKey.getPK(TableEnum.PRODUCT)) );
            order.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            int flag = orderMapper.insert(order);
            // if (StringUtils.equals(order.getTitle(), "a"))
            // throw new BusinessException("001", "测试事务回溯");
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public boolean editOrder(Order order) {
        if (order != null && StringUtils.isNotBlank(order.getId() + "")) {
            int flag = orderMapper.updateByPrimaryKeySelective(order);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public Order findOrderById(Integer id) {
        if (StringUtils.isBlank(id + ""))
            return null;
        else
            return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> selectByExample(OrderExample example) {
        example = new OrderExample();
        Criteria criteria = example.createCriteria();
//        criteria.andStockEqualTo(99);
        return orderMapper.selectByExample(example);
    }

    // 默认数据库
    @Override
    public List<Order> findOrderByKeywords(String keywords) {
        log.info("# 查询默认数据库");
        return orderMapper.findOrderByKeywords(keywords);
    }

    // 数据库1
    @TargetDataSource(DataSourceEnum.DB1)
    @Override
    public PageInfo<Order> findOrderByPage1(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Order> order = orderMapper.findOrderByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Order> page = new PageInfo<Order>(order);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库1 page.toString()={}", page.toString());
        return page;
    }

    // 数据库2
    @TargetDataSource(DataSourceEnum.DB2)
    @Override
    public PageInfo<Order> findOrderByPage2(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Order> order = orderMapper.findOrderByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Order> page = new PageInfo<Order>(order);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库2 page.toString()={}", page.toString());
        return page;
    }

    @Override
    public PageInfo<Order> findOrderByPage(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
//        List<Order> order = orderMapper.findOrderByPage(keywords);
        List<Order> order = orderMapper.selectByExample(new OrderExample());
        // 用PageInfo对结果进行包装
        PageInfo<Order> page = new PageInfo<Order>(order);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }

}
