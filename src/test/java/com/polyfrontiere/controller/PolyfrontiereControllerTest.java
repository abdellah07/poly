package com.polyfrontiere.controller;

import com.polyfrontiere.PolyfrontiereApplication;
import com.polyfrontiere.config.ServiceConfig;
import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.service.customs.CustomsService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = PolyfrontiereApplication.class)
@ContextConfiguration(classes = {ServiceConfig.class})
@AutoConfigureMockMvc
public class PolyfrontiereControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private CustomsService customsService;

	@Test
	public void getTestTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("This is a Polyfrontiere test")));
	}
	
	@Test
	public void getDeclarationsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/declarations").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]societeDeTransit").value("transTOTO"))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]conducteur.nom").value("kherraf"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]conducteur.prenom").value("taha"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]conducteur.mail").value("kherraftaha@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]conducteur.passport.passportId").value("A1JH99KH"))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[0].produit").value("Banane"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[0].quantite").value("2000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[0].valeurMonnaie").value("2200.0"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[0].paysOrigine").value("Bresil"))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[1].produit").value("Orange"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[1].quantite").value("2000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[1].valeurMonnaie").value("1600.0"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[1].paysOrigine").value("Espagne"))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[2].produit").value("Pomme"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[2].quantite").value("3000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[2].valeurMonnaie").value("2700.0"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[2].paysOrigine").value("Espagne"))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[3].produit").value("Ananas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[3].quantite").value("3000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[3].valeurMonnaie").value("3400.0"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]listeDeMarchandise[3].paysOrigine").value("France"));
	}


	@Test
	public void getPassports() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/passports/MAR").accept(MediaType.APPLICATION_JSON)
				.param("passports","A1JH99KH"))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.[0]nom").value("kherraf"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.[0]prenom").value("taha"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.[0]dateDeNaissance").value("1999-08-13T00:00:00.000+00:00"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.[0]lieuDeNaissance").value("Casablanca"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.[0]sexe").value("M"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.[0]id").value("A1JH99KH"));

	}
	
	@Test
	public void getIncomingPassengersTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/incoming").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
		
		
		/*
		mvc.perform(MockMvcRequestBuilders.get("/incoming").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]declaration.societeDeTransit").value("null"));
				*/
	}
}