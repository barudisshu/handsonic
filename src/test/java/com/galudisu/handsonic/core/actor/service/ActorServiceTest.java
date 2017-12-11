package com.galudisu.handsonic.core.actor.service;

import com.galudisu.handsonic.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActorServiceTest extends BaseTest {
    @Autowired
    ActorService actorService;

    @Test
    public void crudTest() {
        Assert.assertTrue(true);
    }
}
