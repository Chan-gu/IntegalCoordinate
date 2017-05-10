package com.hanuritien.integalcoordinate.multidatasource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

/**
 * DataSource and Properties set
 */
public class DataSourceSet {

    private javax.sql.DataSource dataSource;
    private DataSourceProperties dataSourceProperties;

    public DataSourceSet(DataSource dataSource, DataSourceProperties dataSourceProperties) {
        this.dataSource = dataSource;
        this.dataSourceProperties = dataSourceProperties;
    }

    public DataSourceSet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public DataSourceProperties getDataSourceProperties() {
        return dataSourceProperties;
    }
}
