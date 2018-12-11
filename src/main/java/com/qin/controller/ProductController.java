package com.qin.controller;

import com.github.pagehelper.PageInfo;
import com.qin.common.exception.BusinessException;
import com.qin.common.util.ImageUtil;
import com.qin.model.simple.Product;
import com.qin.model.simple.Theme;
import com.qin.service.simple.ProductService;
import com.qin.service.simple.ThemeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品Controller
 */
@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @Autowired
    private ThemeService themeService;


    @Autowired
    private ServletContext context;
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
    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        log.info("# 进入发布产品页面");
        List<Theme> list = themeService.select();
        map.put("list", list);
        return "view/product/add";
    }

    /**
     * @param product
     * @return
     * @Description ajax保存上架产品
     */
    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(@ModelAttribute("productForm") Product product) {
        boolean flag = productService.addProduct(product);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "上架成功");
        } else {
            result.put("status", "0");
            result.put("msg", "上架失败");
        }
        return result;
    }
    /**
     * @param id
     * @return
     * @Description ajax下架产品
     */
    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, String> delete(@PathVariable Integer id) {
        boolean flag = productService.deleteProduct(id);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "下架成功");
        } else {
            result.put("status", "0");
            result.put("msg", "下架失败");
        }
        return result;
    }

    /**
     * @param file
     * @return
     * @Description ajax保存上传产品主图
     */
    @RequestMapping(value = "/product/add/mainImgUrl/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> upload(@RequestParam MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BusinessException("img_not_empty", "图片为空");
        }
        String contentType = file.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException("IMG_FORMAT_ERROR", "图片格式错误");
        }
        String root_fileName = file.getOriginalFilename();
        log.info("上传图片:name={},type={}", root_fileName, contentType);
//        String filePath ="/home/qin/Documents";
//        String filePath ="/home/qin/Documents";
        String file_name = null;
        boolean flag = false;
        try {
//            File filePath = new File(ResourceUtils.getURL(ResourceUtils.WAR_URL_PREFIX).getPath());
//            File upload = new File(filePath.getAbsolutePath(),"src/main/webapp/static/images/pruduct");
//            System.out.println("upload url:" + upload.getAbsolutePath());
//            log.info("图片保存路径={}", upload);
//            file_name = ImageUtil.saveImg(file, upload.getAbsolutePath());
            String imagePath = (String) context.getAttribute("imagePath");
            log.info("图片保存路径={}", imagePath);
            file_name = ImageUtil.saveImg(file, imagePath);
            flag = StringUtils.isNotBlank(file_name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("SAVE_IMG_ERROE", "保存图片错误");
        }

        log.info("文件名={}", file_name);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("url", file_name);
            result.put("msg", "上传成功");
        } else {
            result.put("status", "0");
            result.put("msg", "上传失败");
        }
        return result;
    }


    /**
     * @param fileName
     * @return
     * @Description ajax删除产品主图
     */
    @RequestMapping(value = "/product/add/mainImgUrl/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, String> upload(@RequestBody String fileName) {
        log.info("图片文件名:name={}", fileName);
        if (StringUtils.isBlank(fileName)) {
            throw new BusinessException("IMAGE_NAME_IS_EMPTY", "图片文件名为空");
        }
        log.info("图片文件名:name={}", fileName);
//        String filePath = "/home/qin/Documents";
        String imagePath = (String) context.getAttribute("imagePath");
        log.info("图片保存路径={}", imagePath);
        boolean flag = ImageUtil.deleteFile(imagePath + File.separator + fileName);

        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "删除成功");
        } else {
            result.put("status", "0");
            result.put("url", fileName);
            result.put("msg", "删除失败");
        }
        return result;
    }


    /**
     * @return
     * @Description ajax加载产品对象
     */
    @RequestMapping(value = "/product/load/{id}", method = RequestMethod.GET)
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax加载产品对象");
        Product product = productService.findProductById(Integer.parseInt(id));
        map.addAttribute("product", product);
        return "view/product/edit_form";
    }

    /**
     * @param product
     * @return
     * @Description ajax保存更新重新产品
     */
    @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("productForm") Product product) {
        boolean flag = productService.editProduct(product);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "上架成功");
        } else {
            result.put("status", "0");
            result.put("msg", "上架失败");
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
