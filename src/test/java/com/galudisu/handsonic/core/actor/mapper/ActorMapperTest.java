package com.galudisu.handsonic.core.actor.mapper;

import com.galudisu.handsonic.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActorMapperTest extends BaseTest {

    @Autowired
    ActorMapper actorMapper;

    @Test
    public void test() {
        actorMapper.selectById(1L);
    }
}
