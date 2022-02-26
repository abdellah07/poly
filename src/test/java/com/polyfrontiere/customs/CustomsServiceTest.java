package com.polyfrontiere.customs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.polyfrontiere.config.ServiceConfig;
import com.polyfrontiere.data.customs.IncomingPassenger;
import com.polyfrontiere.data.declaration.domain.Conducteur;
import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import com.polyfrontiere.service.customs.CustomsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomsServiceTest.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class CustomsServiceTest {
	@Autowired
	private CustomsService service;
	
	@Test
	public void notifyIncomingPassengerTest() {
		assertTrue(service.getIncomingPassengers().isEmpty());
		
		Declaration mockDeclaration = Mockito.mock(Declaration.class);
		Conducteur mockConducteur = Mockito.mock(Conducteur.class);
		PassDeclaration mockPassDec = Mockito.mock(PassDeclaration.class);
		Mockito.when(mockPassDec.getPassportId()).thenReturn("00");
		Mockito.when(mockConducteur.getPassport()).thenReturn(mockPassDec);
		Mockito.when(mockDeclaration.getConducteur()).thenReturn(mockConducteur);
		
		service.addIncomingPassenger(new IncomingPassenger(mockDeclaration, "0", false));
		
		assertFalse(service.getIncomingPassengers().isEmpty());
		Assertions.assertEquals(mockDeclaration, service.getIncomingPassengers().get(0).getDeclaration());
		Assertions.assertEquals("0", service.getIncomingPassengers().get(0).getValidationCode());
	}
}
