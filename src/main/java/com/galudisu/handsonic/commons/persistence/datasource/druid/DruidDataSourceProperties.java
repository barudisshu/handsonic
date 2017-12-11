package com.galudisu.handsonic.commons.persistence.datasource.druid;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "druid.datasource")
public class DruidDataSourceProperties {
    // 默认数据源最大连接数
    private final static int DEFAULT_MAX_ACTIVE = 100;
    // 默认数据源初始连接数
    private final static int DEFAULT_MIN_IDLE = 10;

    // 是否启用读写分离
    private boolean masterSlaveEnable = false;
    // 主数据库是否使用Jndi
    private boolean masterJndiEnable = false;
    private String masterJndiName;
    private String masterDriver;
    private String masterUrl;
    private String masterUsername;
    private String masterPassword;
    private int masterMin = DEFAULT_MIN_IDLE;
    private int masterMax = DEFAULT_MAX_ACTIVE;
    // 从数据库是否使用Jndi
    private boolean slaveJndiEnable = false;
    private String slaveJndiName;
    private String slaveDriver;
    private String slaveUrl;
    private String slaveUsername;
    private String slavePassword;
    private int slaveMin = DEFAULT_MIN_IDLE;
    private int slaveMax = DEFAULT_MAX_ACTIVE;

    public boolean isMasterSlaveEnable() {
        return masterSlaveEnable;
    }

    public void setMasterSlaveEnable(boolean masterSlaveEnable) {
        this.masterSlaveEnable = masterSlaveEnable;
    }

    public boolean isMasterJndiEnable() {
        return masterJndiEnable;
    }

    public void setMasterJndiEnable(boolean masterJndiEnable) {
        this.masterJndiEnable = masterJndiEnable;
    }

    public String getMasterJndiName() {
        return masterJndiName;
    }

    public void setMasterJndiName(String masterJndiName) {
        this.masterJndiName = masterJndiName;
    }

    public String getMasterDriver() {
        return masterDriver;
    }

    public void setMasterDriver(String masterDriver) {
        this.masterDriver = masterDriver;
    }

    public String getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String masterUrl) {
        this.masterUrl = masterUrl;
    }

    public String getMasterUsername() {
        return masterUsername;
    }

    public void setMasterUsername(String masterUsername) {
        this.masterUsername = masterUsername;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public int getMasterMin() {
        return masterMin;
    }

    public void setMasterMin(int masterMin) {
        this.masterMin = masterMin;
    }

    public int getMasterMax() {
        return masterMax;
    }

    public void setMasterMax(int masterMax) {
        this.masterMax = masterMax;
    }

    public boolean isSlaveJndiEnable() {
        return slaveJndiEnable;
    }

    public void setSlaveJndiEnable(boolean slaveJndiEnable) {
        this.slaveJndiEnable = slaveJndiEnable;
    }

    public String getSlaveJndiName() {
        return slaveJndiName;
    }

    public void setSlaveJndiName(String slaveJndiName) {
        this.slaveJndiName = slaveJndiName;
    }

    public String getSlaveDriver() {
        return slaveDriver;
    }

    public void setSlaveDriver(String slaveDriver) {
        this.slaveDriver = slaveDriver;
    }

    public String getSlaveUrl() {
        return slaveUrl;
    }

    public void setSlaveUrl(String slaveUrl) {
        this.slaveUrl = slaveUrl;
    }

    public String getSlaveUsername() {
        return slaveUsername;
    }

    public void setSlaveUsername(String slaveUsername) {
        this.slaveUsername = slaveUsername;
    }

    public String getSlavePassword() {
        return slavePassword;
    }

    public void setSlavePassword(String slavePassword) {
        this.slavePassword = slavePassword;
    }

    public int getSlaveMin() {
        return slaveMin;
    }

    public void setSlaveMin(int slaveMin) {
        this.slaveMin = slaveMin;
    }

    public int getSlaveMax() {
        return slaveMax;
    }

    public void setSlaveMax(int slaveMax) {
        this.slaveMax = slaveMax;
    }
}
