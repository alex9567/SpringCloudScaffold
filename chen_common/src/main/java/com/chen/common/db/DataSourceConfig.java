package com.chen.common.db;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.db1")
    public DataSource druidDataSourceOne() {
        return new DruidXADataSource();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.druid.db2")
    public DataSource druidDataSourceTwo() {
        return new DruidXADataSource();
    }

    @Bean
    public DataSource dataSourceOne(DataSource druidDataSourceOne) {
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        sourceBean.setXaDataSource((DruidXADataSource) druidDataSourceOne);
        // 必须为数据源指定唯一标识
        sourceBean.setUniqueResourceName("db1");
        return sourceBean;
    }

    @Bean
    public DataSource dataSourceTwo(DataSource druidDataSourceTwo) {
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        sourceBean.setXaDataSource((DruidXADataSource) druidDataSourceTwo);
        sourceBean.setUniqueResourceName("db2");
        return sourceBean;
    }
}
