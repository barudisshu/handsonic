package com.galudisu.handsonic.commons.service;

import com.galudisu.handsonic.commons.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
    @Autowired
    IdWorker idWorker;

    public Long generateNextId() {
        return idWorker.nextId();
    }

    public IdWorker getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(IdWorker idWorker) {
        this.idWorker = idWorker;
    }
}
