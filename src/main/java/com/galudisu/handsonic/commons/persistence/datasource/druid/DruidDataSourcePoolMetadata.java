package com.galudisu.handsonic.commons.persistence.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.metadata.AbstractDataSourcePoolMetadata;

public class DruidDataSourcePoolMetadata extends AbstractDataSourcePoolMetadata<DruidDataSource> {
    public DruidDataSourcePoolMetadata(DruidDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Integer getActive() {
        return this.getDataSource().getActiveCount();
    }

    @Override
    public Integer getMax() {
        return this.getDataSource().getMaxIdle();
    }

    @Override
    public Integer getMin() {
        return this.getDataSource().getMinIdle();
    }

    @Override
    public String getValidationQuery() {
        return this.getDataSource().getValidationQuery();
    }
}
