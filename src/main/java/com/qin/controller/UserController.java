package com.qin.controller;

import com.github.pagehelper.PageInfo;
import com.qin.config.shiro.vo.Principal;
import com.qin.model.simple.User;
import com.qin.model.simple.UserVo;
import com.qin.service.auth.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用户Controller
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
     * @return
     * @Description 进入新增页面
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String add() {
        log.info("# 进入发布用户页面");
        return "view/user/add";
    }


    @RequestMapping(value = "/user/update/password", method = RequestMethod.GET)
    String updatePassword(Model model) {
        log.info("#去修改密码");
        Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
        User user = principal.getUser();
        model.addAttribute("user", user);
        return "view/user/update_password_form";
    }

    /**
     * @return
     * @Description ajax更新密码
     */
    @RequestMapping(value = "/user/update/password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updatePassword(@ModelAttribute("passwordForm") UserVo userVo) {

        Map<String, String> result = new HashMap<>();
        User user = userVo.getUser();

        if (user.getId() == null || StringUtils.isBlank(user.getId() + "")) {

            result.put("status", "0");
            result.put("msg", "修改失败，不存在该用户");

            return result;
        }
        User oldUser = userService.findUserById(user.getId());
        //从数据哭获取的密码值
        String dataBaseOldPassword = userService.findUserById(user.getId()).getPassword();
        System.out.println("dataBaseOldPassword=" + dataBaseOldPassword);

        //从页面传过来的旧密码值
        String pageReturnOldPassword = userService.getEntryptPassword(oldUser, userVo.getOldPassword());//这个方法和上面的SysMd5一样，就是换了个马甲
        System.out.println("pageReturnOldPassword=" + pageReturnOldPassword);

        if (!dataBaseOldPassword.equals(pageReturnOldPassword)) {
            result.put("status", "0");
            result.put("msg", "旧密码不正确");

            return result;
        }

//        //如果输入的旧密码和数据库一致，则将用户传进来的新密码覆盖旧密码，修改密码
//        user.setUserpassword(ShiroMd5Util.SysMd5(user));


        boolean flag = userService.updatePassword(user);
        if (flag) {
//            Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
//            principal.setUser(user);
            result.put("status", "1");
            result.put("msg", "修改成功");
        } else {
            result.put("status", "0");
            result.put("msg", "修改失败");
        }
        return result;
    }

    /**
     * @param user
     * @return
     * @Description ajax保存发布用户
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
     * @return
     * @Description ajax加载用户对象
     */
    @RequestMapping(value = "/user/load/{id}", method = RequestMethod.GET)
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax加载用户对象");
        User user = userService.findUserById(Integer.parseInt(id));
        map.addAttribute("user", user);
        return "view/user/update_password_form";
    }

    /**
     * @param user
     * @return
     * @Description ajax保存更新重新发布用户
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
