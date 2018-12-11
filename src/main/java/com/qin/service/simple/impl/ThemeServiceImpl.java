package com.qin.service.simple.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.constants.Constants;
import com.qin.config.datasource.DataSourceEnum;
import com.qin.config.datasource.TargetDataSource;
import com.qin.mapper.simple.ThemeMapper;
import com.qin.model.simple.Theme;
import com.qin.model.simple.ThemeExample;
import com.qin.model.simple.ThemeExample.Criteria;
import com.qin.service.simple.ThemeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 分类接口实现类
 */
@Service("themeService")
public class ThemeServiceImpl implements ThemeService {

    private static final Logger log = LoggerFactory.getLogger(ThemeServiceImpl.class);

    @Autowired
    private ThemeMapper themeMapper;

    @Transactional
    @Override
    public boolean addTheme(Theme theme) {
        if (theme != null) {
//            theme.setId(Integer.parseInt(FactoryAboutKey.getPK(TableEnum.PRODUCT)) );
//            theme.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            int flag = themeMapper.insert(theme);
            // if (StringUtils.equals(theme.getTitle(), "a"))
            // throw new BusinessException("001", "测试事务回溯");
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public boolean editTheme(Theme theme) {
        if (theme != null && StringUtils.isNotBlank(theme.getId() + "")) {
            int flag = themeMapper.updateByPrimaryKeySelective(theme);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public Theme findThemeById(Integer id) {
        if (StringUtils.isBlank(id + ""))
            return null;
        else
            return themeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Theme> selectByExample(ThemeExample example) {
        example = new ThemeExample();
        Criteria criteria = example.createCriteria();
//        criteria.andStockEqualTo(99);
        return themeMapper.selectByExample(example);
    }

    @Override
    public List<Theme> select() {
//        return themeMapper.select();
        return themeMapper.selectByExample(new ThemeExample());
    }

    // 默认数据库
    @Override
    public List<Theme> findThemeByKeywords(String keywords) {
        log.info("# 查询默认数据库");
        return themeMapper.findThemeByKeywords(keywords);
    }

    // 数据库1
    @TargetDataSource(DataSourceEnum.DB1)
    @Override
    public PageInfo<Theme> findThemeByPage1(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Theme> theme = themeMapper.findThemeByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Theme> page = new PageInfo<Theme>(theme);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库1 page.toString()={}", page.toString());
        return page;
    }

    // 数据库2
    @TargetDataSource(DataSourceEnum.DB2)
    @Override
    public PageInfo<Theme> findThemeByPage2(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Theme> theme = themeMapper.findThemeByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Theme> page = new PageInfo<Theme>(theme);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库2 page.toString()={}", page.toString());
        return page;
    }

    @Override
    public PageInfo<Theme> findThemeByPage(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Theme> theme = themeMapper.findThemeByPage(keywords);
//        List<Theme> theme = themeMapper.selectByExample(new ThemeExample());
//        List<Theme> theme = themeMapper.select();
        // 用PageInfo对结果进行包装
        PageInfo<Theme> page = new PageInfo<Theme>(theme);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }

}
