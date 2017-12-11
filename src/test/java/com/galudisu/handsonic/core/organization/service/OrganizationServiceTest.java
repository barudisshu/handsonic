package com.galudisu.handsonic.core.organization.service;

import com.galudisu.handsonic.Constants;
import com.galudisu.handsonic.core.actor.domain.ActorRelation;
import com.galudisu.handsonic.core.actor.service.ActorService;
import com.galudisu.handsonic.core.organization.domain.Organization;
import com.galudisu.handsonic.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizationServiceTest extends BaseTest {
    @Autowired
    OrganizationService organizationService;

    @Autowired
    ActorService actorService;

    @Test
    public void crudTest() {
        Organization org = new Organization();
        org.setCode("Test");
        org.setTitle("Test");
        org.setDescription("Test");
        org.setStatus(Constants.STATUS_OK);
        organizationService.save(org);

        Organization sub = new Organization();
        sub.setCode("Test");
        sub.setTitle("Test");
        sub.setDescription("Test");
        sub.setStatus(Constants.STATUS_OK);
        organizationService.save(sub);

        actorService.saveOrUpdateRelations(sub.getId(), org.getId(), ActorRelation.R_ORG_PARENT_ORG);

    }
}
