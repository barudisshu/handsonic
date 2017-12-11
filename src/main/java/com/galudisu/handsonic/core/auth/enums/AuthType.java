package com.galudisu.handsonic.core.auth.enums;

/**
 * 登录验证方式
 */
public enum AuthType {
    WEB("WEB", true, "表单登录"),
    API("API", true, "接口登录"),
    SSO("SSO", false, "单点登录");

    // 类型
    private final String type;
    // 描述
    private final String desc;
    // 当前角色包含的权限定义类型
    private final boolean checkPasswordInd;

    AuthType(final String type, final boolean checkPasswordInd, final String desc) {
        this.type = type;
        this.desc = desc;
        this.checkPasswordInd = checkPasswordInd;
    }

    /**
     * 根据类型编码获取对应的角色类型
     */
    public static AuthType getAuthType(String type) {
        AuthType[] ts = AuthType.values();
        for (AuthType t : ts) {
            if (t.getType().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return WEB;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isCheckPasswordInd() {
        return checkPasswordInd;
    }
}
