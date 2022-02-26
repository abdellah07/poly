package com.polyfrontiere.service.declaration;

import com.polyfrontiere.config.ServiceConfig;
import com.polyfrontiere.data.declaration.domain.Declaration;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeclarationMapperService.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class DeclarationMapperServiceTest {	
	@Autowired
	private DeclarationMapperService service;
	
	@Test
	public void testGetDeclarations() {
		assertNotNull(service);
		
		Declaration[] declarations = null;
		
		try {
			declarations = service.getDeclarationsForNextHours(0);
		} catch (Exception e) {
			Assert.fail();
		}
				
		assertNotNull(declarations);
		assertEquals(4, declarations.length);
	}

}
