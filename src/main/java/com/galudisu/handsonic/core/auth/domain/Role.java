package com.galudisu.handsonic.core.auth.domain;

import com.galudisu.handsonic.commons.domain.IdEntity;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_role")
public class Role extends IdEntity {
    // 角色编号
    private String code;
    // 角色标题
    private String title;
    // 角色描述
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
