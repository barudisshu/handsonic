package com.galudisu.handsonic.commons.persistence.datasource.druid;

import com.galudisu.handsonic.commons.persistence.datasource.DynamicDataSource;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class DruidDataSourceAutoConfiguration {
    @Autowired
    DruidDataSourceProperties properties;

    @Bean
    public DataSourcePoolMetadataProvider druidPoolDataSourceMetadataProvider() {
        return dataSource -> {
            if (dataSource instanceof DruidDataSource) {
                return new DruidDataSourcePoolMetadata((DruidDataSource) dataSource);
            }
            return null;
        };
    }

    @Bean
    @ConditionalOnWebApplication
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() throws SQLException {
        if (properties.isMasterSlaveEnable()) { // 使用启用读写分离主从多数据源
            // 创建主数据库数据源
            DataSource masterDataSource = masterDataSource();
            // 创建从数据库数据源
            DataSource slaveDataSource = slaveDataSource();

            Map<Object, Object> targetDataSources = Maps.newHashMap();
            targetDataSources.put("master", masterDataSource);
            targetDataSources.put("slave", slaveDataSource);

            DynamicDataSource dynamicDataSource = new DynamicDataSource();
            dynamicDataSource.setTargetDataSources(targetDataSources);
            // 默认使用主数据库数据源
            dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
            return dynamicDataSource;
        } else {
            // 未使用读写分离的时候，默认使用主数据源
            return masterDataSource();
        }
    }

    public DataSource masterDataSource() throws SQLException {
        if (properties.isMasterJndiEnable()) {
            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            return dataSourceLookup.getDataSource(properties.getMasterJndiName());
        } else {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(properties.getMasterUrl());
            datasource.setDriverClassName(properties.getMasterDriver());
            datasource.setUsername(properties.getMasterUsername());
            datasource.setPassword(properties.getMasterPassword());
            datasource.setInitialSize(properties.getMasterMin());
            datasource.setMaxActive(properties.getMasterMax());
            datasource.setMinIdle(properties.getMasterMin());
            datasource.setFilters("stat,slf4j");
            datasource.setProxyFilters(getDruidFilters());
            return datasource;
        }
    }

    public DataSource slaveDataSource() throws SQLException {
        if (properties.isSlaveJndiEnable()) {
            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            return dataSourceLookup.getDataSource(properties.getSlaveJndiName());
        } else {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(properties.getSlaveUrl());
            datasource.setDriverClassName(properties.getSlaveDriver());
            datasource.setUsername(properties.getSlaveUsername());
            datasource.setPassword(properties.getSlavePassword());
            datasource.setInitialSize(properties.getSlaveMin());
            datasource.setMaxActive(properties.getSlaveMax());
            datasource.setMinIdle(properties.getSlaveMin());
            datasource.setFilters("stat,slf4j");
            datasource.setProxyFilters(getDruidFilters());
            return datasource;
        }
    }

    public List<Filter> getDruidFilters() {
        List<Filter> filters = new ArrayList<>();

        Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        slf4jLogFilter.setDataSourceLogEnabled(false);
        slf4jLogFilter.setStatementLogEnabled(false);
        slf4jLogFilter.setStatementExecutableSqlLogEnable(true);
        slf4jLogFilter.setResultSetLogEnabled(false);
        slf4jLogFilter.setResultSetCloseAfterLogEnabled(false);
        slf4jLogFilter.setConnectionLogEnabled(false);

        filters.add(new StatFilter());
        filters.add(slf4jLogFilter);
        return filters;
    }
}
