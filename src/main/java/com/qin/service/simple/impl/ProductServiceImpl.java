package com.qin.service.simple.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.constants.Constants;
import com.qin.common.exception.BusinessException;
import com.qin.config.datasource.DataSourceEnum;
import com.qin.config.datasource.TargetDataSource;
import com.qin.config.pk.FactoryAboutKey;
import com.qin.config.pk.TableEnum;
import com.qin.mapper.simple.ProductMapper;
import com.qin.mapper.simple.ThemeProductMapper;
import com.qin.model.simple.Product;
import com.qin.model.simple.ProductExample;
import com.qin.model.simple.ProductExample.Criteria;
import com.qin.model.simple.ThemeProductKey;
import com.qin.service.simple.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @Description 产品接口实现类
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ThemeProductMapper themeProductMapper;

    @Transactional
    @Override
    public boolean addProduct(Product product) {
        if (product != null) {
//                product.setId(Integer.parseInt(FactoryAboutKey.getPK(TableEnum.PRODUCT)) );
//                product.setId(Integer.parseInt(FactoryAboutKey.getPK(TableEnum.PRODUCT)) );
                product.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                int flag = productMapper.insertSelective(product);
                ThemeProductKey themeProduct =    new ThemeProductKey();
                themeProduct.setProductId(product.getId());
                themeProduct.setThemeId(Integer.parseInt(product.getTheme()));
                int flag2 = themeProductMapper.insert(themeProduct);
                // if (StringUtils.equals(product.getTitle(), "a"))
                // throw new BusinessException("001", "测试事务回溯");
                if (flag == 1&&flag2 == 1)
                    return true;
                else
                return false;

        } else
            return false;
    }
    @Transactional
    @Override
    public boolean deleteProduct(Integer id) {
        if (id != null) {
            int flag = productMapper.deleteByPrimaryKey(id);
            // if (StringUtils.equals(product.getTitle(), "a"))
            // throw new BusinessException("001", "测试事务回溯");
            if (flag == 1)
                return true;
            else
                return false;

        } else
            return false;
    }
    @Override
    public boolean editProduct(Product product) {
        if (product != null && StringUtils.isNotBlank(product.getId() + "")) {
            int flag = productMapper.updateByPrimaryKeySelective(product);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public Product findProductById(Integer id) {
        if (StringUtils.isBlank(id + ""))
            return null;
        else
            return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectByExample(ProductExample example) {
        example = new ProductExample();
        Criteria criteria = example.createCriteria();
//        criteria.andStockEqualTo(99);
        return productMapper.selectByExample(example);
    }

    @Override
    public List<Product> select() {
        return productMapper.select();
    }

    // 默认数据库
    @Override
    public List<Product> findProductByKeywords(String keywords) {
        log.info("# 查询默认数据库");
        return productMapper.findProductByKeywords(keywords);
    }

    // 数据库1
    @TargetDataSource(DataSourceEnum.DB1)
    @Override
    public PageInfo<Product> findProductByPage1(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Product> product = productMapper.findProductByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Product> page = new PageInfo<Product>(product);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库1 page.toString()={}", page.toString());
        return page;
    }

    // 数据库2
    @TargetDataSource(DataSourceEnum.DB2)
    @Override
    public PageInfo<Product> findProductByPage2(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
        List<Product> product = productMapper.findProductByPage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<Product> page = new PageInfo<Product>(product);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询数据库2 page.toString()={}", page.toString());
        return page;
    }

    @Override
    public PageInfo<Product> findProductByPage(Integer pageNum, String keywords) {
        // request: url?pageNum=1&pageSize=10
        // 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        // 紧跟着的第一个select方法会被分页
//        List<Product> product = productMapper.findProductByPage(keywords);
//        List<Product> product = productMapper.selectByExample(new ProductExample());
        List<Product> product = productMapper.select();
        // 用PageInfo对结果进行包装
        PageInfo<Product> page = new PageInfo<Product>(product);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }

}
