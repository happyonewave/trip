package com.qin.service.simple;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.Order;
import com.qin.model.simple.OrderExample;

import java.util.List;

public interface OrderService {

    public boolean addOrder(Order order);

    public boolean editOrder(Order order);

    public Order findOrderById(Integer orderId);

    List<Order> selectByExample(OrderExample example);

    public List<Order> findOrderByKeywords(String keywords);

    public PageInfo<Order> findOrderByPage(Integer pageNum, String keywords);

    public PageInfo<Order> findOrderByPage1(Integer pageNum, String keywords);

    public PageInfo<Order> findOrderByPage2(Integer pageNum, String keywords);

}