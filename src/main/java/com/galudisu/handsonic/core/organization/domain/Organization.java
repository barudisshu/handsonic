package com.galudisu.handsonic.core.organization.domain;

import com.galudisu.handsonic.core.actor.domain.Actor;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_organization")
public class Organization extends Actor {
    // 组织编号
    private String code;
    // 组织标题
    private String title;
    // 组织描述
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
