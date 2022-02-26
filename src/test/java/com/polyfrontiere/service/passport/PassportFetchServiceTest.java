package com.polyfrontiere.service.passport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.polyfrontiere.config.ServiceConfig;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.polyfrontiere.data.passport.Passport;
import com.polyfrontiere.service.declaration.DeclarationMapperService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = PassportInfoService.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class PassportFetchServiceTest {
    @Autowired
    private PassportInfoService service;

    @Autowired
    private DeclarationMapperService declarationService;

    @Test
    public void test() {
        assertNotNull(service);

        Passport[] passPorts = null;
        PassDeclaration[] ids = null;

        try {
            ids = declarationService.getPassportIdsForNextHours(0);
            passPorts = service.getPassportInfoForIds(ids);
        } catch (Exception e) {
            System.out.println(service);
        }

        assertNotNull(passPorts);
        assertEquals(4, passPorts.length);
    }
}
