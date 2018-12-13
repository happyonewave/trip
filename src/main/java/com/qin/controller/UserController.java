package com.qin.controller;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.User;
import com.qin.service.auth.UserService;
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
 * @Description 用户Controller
 *
 *
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

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
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String add() {
        log.info("# 进入发布用户页面");
        return "view/user/add";
    }

    /**
     * @Description ajax保存发布用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(@ModelAttribute("userForm") User user) {
        boolean flag = userService.addUser(user);
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
     * @Description ajax加载用户对象
     *
     * @return
     */
    @RequestMapping(value = "/user/load/{id}", method = RequestMethod.GET)
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax加载用户对象");
        User user = userService.findUserById(Integer.parseInt(id));
        map.addAttribute("user", user);
        return "view/user/edit_form";
    }

    /**
     * @Description ajax保存更新重新发布用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("userForm") User user) {
        boolean flag = userService.editUser(user);
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

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        PageInfo<User> page = userService.findUserByPage(null, null);
        map.put("page", page);
        return "view/user/list";
    }

    @RequestMapping(value = "/user/list_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询用户 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<User> page = userService.findUserByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/user/list_page";
    }

    @RequestMapping(value = "/user/list1", method = RequestMethod.GET)
    public String list1(ModelMap map) {
        log.info("#分页查询数据库1");
        PageInfo<User> page = userService.findUserByPage1(null, null);
        map.put("page", page);
        return "view/user/list1";
    }

    @RequestMapping(value = "/user/list_page1", method = RequestMethod.POST)
    public String list_page1(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<User> page = userService.findUserByPage1(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/user/list_page1";
    }

    @RequestMapping(value = "/user/list2", method = RequestMethod.GET)
    public String list2(ModelMap map) {
        log.info("#分页查询数据库2");
        PageInfo<User> page = userService.findUserByPage2(null, null);
        map.put("page", page);
        return "view/user/list2";
    }

    @RequestMapping(value = "/user/list_page2", method = RequestMethod.POST)
    public String list_page2(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<User> page = userService.findUserByPage2(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/user/list_page2";
    }

}
