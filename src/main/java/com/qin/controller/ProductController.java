package com.qin.controller;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.Product;
import com.qin.service.simple.ProductService;
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
 * @Description 产品Controller
 *
 *
 */
@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

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
    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String add() {
        log.info("# 进入发布产品页面");
        return "view/product/add";
    }

    /**
     * @Description ajax保存发布产品
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(@ModelAttribute("productForm") Product product) {
        boolean flag = productService.addProduct(product);
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
     * @Description ajax加载产品对象
     *
     * @return
     */
    @RequestMapping(value = "/product/load/{id}", method = RequestMethod.GET)
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax加载产品对象");
        Product product = productService.findProductById(Integer.parseInt(id));
        map.addAttribute("product", product);
        return "view/product/edit_form";
    }

    /**
     * @Description ajax保存更新重新发布产品
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("productForm") Product product) {
        boolean flag = productService.editProduct(product);
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

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        PageInfo<Product> page = productService.findProductByPage(null, null);
        map.put("page", page);
        return "view/product/list";
    }

    @RequestMapping(value = "/product/list_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询产品 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Product> page = productService.findProductByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/product/list_page";
    }

    @RequestMapping(value = "/product/list1", method = RequestMethod.GET)
    public String list1(ModelMap map) {
        log.info("#分页查询数据库1");
        PageInfo<Product> page = productService.findProductByPage1(null, null);
        map.put("page", page);
        return "view/product/list1";
    }

    @RequestMapping(value = "/product/list_page1", method = RequestMethod.POST)
    public String list_page1(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Product> page = productService.findProductByPage1(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/product/list_page1";
    }

    @RequestMapping(value = "/product/list2", method = RequestMethod.GET)
    public String list2(ModelMap map) {
        log.info("#分页查询数据库2");
        PageInfo<Product> page = productService.findProductByPage2(null, null);
        map.put("page", page);
        return "view/product/list2";
    }

    @RequestMapping(value = "/product/list_page2", method = RequestMethod.POST)
    public String list_page2(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Product> page = productService.findProductByPage2(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/product/list_page2";
    }

}
