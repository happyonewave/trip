package com.qin.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
 * @Description 动态数据源
 *
 * @date Mar 17, 2017 9:00:53 AM  
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}
