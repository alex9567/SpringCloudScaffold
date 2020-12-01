package com.chen.config.db;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@NacosPropertySource(dataId = "db_config" ,groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class DataSourceConfig {
    @NacosValue(value = "${spring.datasource.druid.db1.url}",autoRefreshed = true)
    private String db1Url;
    @NacosValue(value = "${spring.datasource.druid.db1.username}",autoRefreshed = true)
    private String db1Username;
    @NacosValue(value = "${spring.datasource.druid.db1.password}",autoRefreshed = true)
    private String db1Password;
    @NacosValue(value = "${spring.datasource.druid.db1.driver-class-name}",autoRefreshed = true)
    private String db1dDiverClassName;
    @NacosValue(value = "${spring.datasource.druid.db1.initialSize}",autoRefreshed = true)
    private int db1initialSize;
    @NacosValue(value = "${spring.datasource.druid.db1.minIdle}",autoRefreshed = true)
    private int db1minIdle;
    @NacosValue(value = "${spring.datasource.druid.db1.maxActive}",autoRefreshed = true)
    private int db1maxActive;
    @NacosValue(value = "${spring.datasource.druid.db1.maxWait}",autoRefreshed = true)
    private long db1maxWait;
    @NacosValue(value = "${spring.datasource.druid.db1.timeBetweenEvictionRunsMillis}",autoRefreshed = true)
    private long db1timeBetweenEvictionRunsMillis;
    @NacosValue(value = "${spring.datasource.druid.db1.minEvictableIdleTimeMillis}",autoRefreshed = true)
    private long db1minEvictableIdleTimeMillis;
    @NacosValue(value = "${spring.datasource.druid.db1.validationQuery}",autoRefreshed = true)
    private String db1validationQuery;
    @NacosValue(value = "${spring.datasource.druid.db1.testWhileIdle}",autoRefreshed = true)
    private boolean db1testWhileIdle;
    @NacosValue(value = "${spring.datasource.druid.db1.testOnBorrow}",autoRefreshed = true)
    private boolean db1testOnBorrow;
    @NacosValue(value = "${spring.datasource.druid.db1.testOnReturn}",autoRefreshed = true)
    private boolean db1testOnReturn;
    @NacosValue(value = "${spring.datasource.druid.db1.removeAbandoned}",autoRefreshed = true)
    private boolean db1removeAbandoned;
    @NacosValue(value = "${spring.datasource.druid.db1.remove-abandoned-timeout}",autoRefreshed = true)
    private int db1RemoveAbandonedTimeout;
    @NacosValue(value = "${spring.datasource.druid.db2.url}",autoRefreshed = true)
    private String db2Url;
    @NacosValue(value = "${spring.datasource.druid.db2.username}",autoRefreshed = true)
    private String db2Username;
    @NacosValue(value = "${spring.datasource.druid.db2.password}",autoRefreshed = true)
    private String db2Password;
    @NacosValue(value = "${spring.datasource.druid.db2.driver-class-name}",autoRefreshed = true)
    private String db2dDiverClassName;
    @NacosValue(value = "${spring.datasource.druid.db2.initialSize}",autoRefreshed = true)
    private int db2initialSize;
    @NacosValue(value = "${spring.datasource.druid.db2.minIdle}",autoRefreshed = true)
    private int db2minIdle;
    @NacosValue(value = "${spring.datasource.druid.db2.maxActive}",autoRefreshed = true)
    private int db2maxActive;
    @NacosValue(value = "${spring.datasource.druid.db2.maxWait}",autoRefreshed = true)
    private long db2maxWait;
    @NacosValue(value = "${spring.datasource.druid.db2.timeBetweenEvictionRunsMillis}",autoRefreshed = true)
    private long db2timeBetweenEvictionRunsMillis;
    @NacosValue(value = "${spring.datasource.druid.db2.minEvictableIdleTimeMillis}",autoRefreshed = true)
    private long db2minEvictableIdleTimeMillis;
    @NacosValue(value = "${spring.datasource.druid.db2.validationQuery}",autoRefreshed = true)
    private String db2validationQuery;
    @NacosValue(value = "${spring.datasource.druid.db2.testWhileIdle}",autoRefreshed = true)
    private boolean db2testWhileIdle;
    @NacosValue(value = "${spring.datasource.druid.db2.testOnBorrow}",autoRefreshed = true)
    private boolean db2testOnBorrow;
    @NacosValue(value = "${spring.datasource.druid.db2.testOnReturn}",autoRefreshed = true)
    private boolean db2testOnReturn;
    @NacosValue(value = "${spring.datasource.druid.db2.removeAbandoned}",autoRefreshed = true)
    private boolean db2removeAbandoned;
    @NacosValue(value = "${spring.datasource.druid.db2.remove-abandoned-timeout}",autoRefreshed = true)
    private int db2RemoveAbandonedTimeout;
    @Bean
    public DataSource druidDataSourceOne() {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(db1Url);
        druidXADataSource.setUsername(db1Username);
        druidXADataSource.setPassword(db1Password);
        druidXADataSource.setDriverClassName(db1dDiverClassName);
        druidXADataSource.setInitialSize(db1initialSize);
        druidXADataSource.setMinIdle(db1minIdle);
        druidXADataSource.setMaxActive(db1maxActive);
        druidXADataSource.setMaxWait(db1maxWait);
        druidXADataSource.setTimeBetweenEvictionRunsMillis(db1timeBetweenEvictionRunsMillis);
        druidXADataSource.setMinEvictableIdleTimeMillis(db1minEvictableIdleTimeMillis);
        druidXADataSource.setValidationQuery(db1validationQuery);
        druidXADataSource.setTestWhileIdle(db1testWhileIdle);
        druidXADataSource.setTestOnBorrow(db1testOnBorrow);
        druidXADataSource.setTestOnReturn(db1testOnReturn);
        druidXADataSource.setRemoveAbandoned(db1removeAbandoned);
        druidXADataSource.setRemoveAbandonedTimeout(db1RemoveAbandonedTimeout);
        return druidXADataSource;
    }
    @Bean
    public DataSource druidDataSourceTwo() {
        //没有接入分布式事务的时候使用普通DruidDataSource数据源即可
        //DruidDataSource druidDataSource = new DruidDataSource();
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(db2Url);
        druidXADataSource.setUsername(db2Username);
        druidXADataSource.setPassword(db2Password);
        druidXADataSource.setDriverClassName(db2dDiverClassName);
        druidXADataSource.setInitialSize(db2initialSize);
        druidXADataSource.setMinIdle(db2minIdle);
        druidXADataSource.setMaxActive(db2maxActive);
        druidXADataSource.setMaxWait(db2maxWait);
        druidXADataSource.setTimeBetweenEvictionRunsMillis(db2timeBetweenEvictionRunsMillis);
        druidXADataSource.setMinEvictableIdleTimeMillis(db2minEvictableIdleTimeMillis);
        druidXADataSource.setValidationQuery(db2validationQuery);
        druidXADataSource.setTestWhileIdle(db2testWhileIdle);
        druidXADataSource.setTestOnBorrow(db2testOnBorrow);
        druidXADataSource.setTestOnReturn(db2testOnReturn);
        druidXADataSource.setRemoveAbandoned(db2removeAbandoned);
        druidXADataSource.setRemoveAbandonedTimeout(db2RemoveAbandonedTimeout);
        return druidXADataSource;
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
