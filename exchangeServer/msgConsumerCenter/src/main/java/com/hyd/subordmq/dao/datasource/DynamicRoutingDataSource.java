package com.hyd.subordmq.dao.datasource;

/**
 * Created by xieshuai on 2020/1/16 11:16
 */
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        if(null==dataSourceName){
        }
        return DynamicDataSourceContextHolder.getDataSourceRouterKey();
    }
}