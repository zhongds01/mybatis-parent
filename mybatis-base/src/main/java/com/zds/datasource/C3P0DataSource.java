package com.zds.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
public class C3P0DataSource extends UnpooledDataSourceFactory {
    public C3P0DataSource() {
        this.dataSource = new ComboPooledDataSource();
    }
}
