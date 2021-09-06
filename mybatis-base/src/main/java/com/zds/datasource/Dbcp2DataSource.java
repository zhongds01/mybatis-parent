package com.zds.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
public class Dbcp2DataSource extends UnpooledDataSourceFactory {
    public Dbcp2DataSource() {
        this.dataSource = new BasicDataSource();
    }
}
