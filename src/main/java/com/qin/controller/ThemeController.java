package com.qin.controller;

import com.github.pagehelper.PageInfo;
import com.qin.common.exception.BusinessException;
import com.qin.common.util.ImageUtil;
import com.qin.model.simple.Theme;
import com.qin.service.simple.ThemeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 分类Controller
 *
 *
 */
@Controller
public class ThemeController {

    private static final Logger log = LoggerFactory.getLogger(ThemeController.class);

    @Autowired
    private ThemeService themeService;

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
    @RequestMapping(value = "/theme/add", method = RequestMethod.GET)
    public String add() {
        log.info("# 进入发布分类页面");
        return "view/theme/add";
    }

    /**
     * @Description ajax保存发布分类
     *
     * @param theme
     * @return
     */
    @RequestMapping(value = "/theme/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(@ModelAttribute("themeForm") Theme theme) {
        boolean flag = themeService.addTheme(theme);
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
     * @Description ajax保存上传分类主图
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/theme/add/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> upload(@RequestParam MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BusinessException("img_not_empty","图片为空");
        }
        String contentType = file.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException("IMG_FORMAT_ERROR","图片格式错误");
        }
        String root_fileName = file.getOriginalFilename();
        log.info("上传图片:name={},type={}", root_fileName, contentType);
        String filePath ="/home/qin/Documents";
        log.info("图片保存路径={}", filePath);
        String file_name = null;
        boolean flag =false;
        try {
            file_name = ImageUtil.saveImg(file, filePath);
            flag = StringUtils.isNotBlank(file_name);
        } catch (IOException e) {
            throw new BusinessException("SAVE_IMG_ERROE","保存图片错误");
        }

        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("url", file_name);
            result.put("msg", "发布成功");
        } else {
            result.put("status", "0");
            result.put("msg", "发布失败");
        }
        return result;
    }


    /**
     * @Description ajax加载分类对象
     *
     * @return
     */
    @RequestMapping(value = "/theme/load/{id}", method = RequestMethod.GET)
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax加载分类对象");
        Theme theme = themeService.findThemeById(Integer.parseInt(id));
        map.addAttribute("theme", theme);
        return "view/theme/edit_form";
    }

    /**
     * @Description ajax保存更新重新发布分类
     *
     * @param theme
     * @return
     */
    @RequestMapping(value = "/theme/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("themeForm") Theme theme) {
        boolean flag = themeService.editTheme(theme);
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
    @RequestMapping(value = "/theme/list", method = RequestMethod.GET)
    public String list( ModelMap map) {
//        PageInfo<Theme> page = themeService.findThemeByPage(null, null);
        List<Theme> list = themeService.select();
        map.put("list",list);
        return  "view/product/add";
    }

    @RequestMapping(value = "/theme/list_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询分类 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Theme> page = themeService.findThemeByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/theme/list_page";
    }

    @RequestMapping(value = "/theme/list1", method = RequestMethod.GET)
    public String list1(ModelMap map) {
        log.info("#分页查询数据库1");
        PageInfo<Theme> page = themeService.findThemeByPage1(null, null);
        map.put("page", page);
        return "view/theme/list1";
    }

    @RequestMapping(value = "/theme/list_page1", method = RequestMethod.POST)
    public String list_page1(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Theme> page = themeService.findThemeByPage1(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/theme/list_page1";
    }

    @RequestMapping(value = "/theme/list2", method = RequestMethod.GET)
    public String list2(ModelMap map) {
        log.info("#分页查询数据库2");
        PageInfo<Theme> page = themeService.findThemeByPage2(null, null);
        map.put("page", page);
        return "view/theme/list2";
    }

    @RequestMapping(value = "/theme/list_page2", method = RequestMethod.POST)
    public String list_page2(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询数据库2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<Theme> page = themeService.findThemeByPage2(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/theme/list_page2";
    }

}
