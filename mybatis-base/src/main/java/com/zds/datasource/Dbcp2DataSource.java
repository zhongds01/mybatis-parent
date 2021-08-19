package com.zds.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class Dbcp2DataSource extends UnpooledDataSourceFactory {
    public Dbcp2DataSource() {
        this.dataSource = new BasicDataSource();
    }
}
