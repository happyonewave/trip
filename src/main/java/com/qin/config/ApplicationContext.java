package com.qin.config;

import com.qin.common.exception.BusinessException;
import com.qin.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

/**
 * 将version版本号写入application中，给css,js引用时用
 */
@Component
public class ApplicationContext implements ServletContextAware {
    public static String imagePath = "";

    private static final Logger log = LoggerFactory.getLogger(ApplicationContext.class);

    @Override
    public void setServletContext(ServletContext context) {
        String datetime = DateUtil.dateToString(Calendar.getInstance().getTime(), DateUtil.fm_yyyyMMddHHmmssSSS);
        String contextPath = context.getContextPath();

        String filePath = null;
        try {
//            filePath = new File(ResourceUtils.getURL(ResourceUtils.WAR_URL_PREFIX).getPath());
//            filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            filePath = context.getRealPath("");
//            filePath =  System.getProperty("user.dir");
//            File upload = new File(filePath.getAbsolutePath(),"src/main/webapp/static/images/product");
            imagePath = filePath + "static/images/product";
            context.setAttribute("imagePath", filePath + "static/images/product");
        } catch (Exception e) {
            throw new BusinessException("获取路径错误");
        }
        log.info("# version={} , contextPath={}", datetime, contextPath);
        context.setAttribute("version_css", datetime);
        context.setAttribute("version_js", datetime);
        context.setAttribute("ctx", contextPath);
    }

}