package com.qin.controller;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.Order;
import com.qin.service.simple.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * @Description 订单Controller
 *
 *
 */
@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /*
     * 表单提交日期绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * @Description 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/order/add", method = RequestMethod.GET)
    public String add() {
        log.info("# 进入发布订单页面");
        return "view/order/add";
    }

    /**
     * @Description ajax保存发布订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(@ModelAttribute("orderForm") Order order) {
        boolean flag = orderService.addOrder(order);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "发布成功");
        } else {
            result.put("status", "0");
            result.put("msg", "发布失败");
        }
        return result;
    }

    /**
     * @Description ajax加载订单对象
     *
     * @return
     */
    @RequestMapping(value = "/order/load/{id}", method = RequestMethod.GET)
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax加载订单对象");
        Order order = orderService.findOrderById(Integer.parseInt(id));
        map.addAttribute("order", order);
        return "view/order/edit_form";
    }

    /**
     * @Description ajax保存更新重新发布订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("orderForm") Order order) {
        boolean flag = orderService.editOrder(order);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "发布成功");
        } else {
            result.put("status", "0");
            result.put("msg", "发布失败");
        }
        return result;
    }

    /**
     * @Description ajax保存更新重新发布订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/edit1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit1( @RequestBody Order order) {
        log.info(order.toString());
        boolean flag = orderService.editOrder(order);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "发布成功");
        } else {
            result.put("status", "0");
            result.put("msg", "发布失败");
        }
        return result;
    }

    /**
     * @Description ajax处理订单（接受或拒绝）
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/handle/{id}/{staus}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> handle(@PathVariable Integer id,@PathVariable Integer staus) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(staus);
        boolean flag = orderService.editOrder(order);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "处理成功");
        } else {
            result.put("status", "0");
            result.put("msg", "处理失败");
        }
        return result;
    }



    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        PageInfo<Order> page = orderService.findOrderByPage(null, null);
        map.put("page", page);
        return "view/order/list";
    }

    @RequestMapping(value = "/order/list_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询订单 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Order> page = orderService.findOrderByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/order/list_page";
    }

    @RequestMapping(value = "/order/list1", method = RequestMethod.GET)
    public String list1(ModelMap map) {
        log.info("#分页查询数据库1");
        PageInfo<Order> page = orderService.findOrderByPage1(null, null);
        map.put("page", page);
        return "view/order/list1";
    }

    @RequestMapping(value = "/order/list_page1", method = RequestMethod.POST)
    public String list_page1(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Order> page = orderService.findOrderByPage1(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/order/list_page1";
    }

    @RequestMapping(value = "/order/list2", method = RequestMethod.GET)
    public String list2(ModelMap map) {
        log.info("#分页查询数据库2");
        PageInfo<Order> page = orderService.findOrderByPage2(null, null);
        map.put("page", page);
        return "view/order/list2";
    }

    @RequestMapping(value = "/order/list_page2", method = RequestMethod.POST)
    public String list_page2(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Order> page = orderService.findOrderByPage2(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/order/list_page2";
    }

}
