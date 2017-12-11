package com.galudisu.handsonic.commons.domain;

import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

public class IdEntity implements Serializable {
    @TableId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IdEntity) {
            IdEntity entity = (IdEntity) obj;
            if (getId() != null && entity.getId() != null && getId().longValue() == entity.getId().longValue()) {
                return true;
            }
        }
        return false;
    }
}
