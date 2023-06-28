package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Salarie;
import com.inti.repository.ISalarieRepository;

@WebMvcTest(controllers = SalarieController.class)
public class SalarieControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ISalarieRepository isr;
	
	
	@Test
	public void saveSalarie() throws Exception {
		mockMvc.perform(get("/enregistrerSalarie"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void listeSalarie() throws Exception {
		mockMvc.perform(get("/listeSalarie"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
//	@Test
//	public void modifierSalarie() throws Exception {
//				
//		mockMvc.perform(get("/modifierSalarie/1").requestAttr("salarie", new Salarie(1, "test", "test", "t@t.fr")))
//		.andExpect(status().isOk())
//		.andDo(print());
//	}
	
	
	@Test
	public void deleteSalarie() throws Exception {
		mockMvc.perform(get("/supprimerSalarie/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeSalarie"))
		.andDo(print());
	}
	
	
	@Test
	public void saveSalariePost() throws Exception {
		mockMvc.perform(post("/enregistrerSalarie").sessionAttr("salarie", new Salarie("Dupond", "Louis", "a@a.com")))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	
	@Test
	public void modifierSalariePost() throws Exception {
		mockMvc.perform(post("/modifierSalarie/1").sessionAttr("salarie", new Salarie("Dupond", "Louis", "a@a.com")))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeSalarie"))
		.andDo(print());
	}
	
	
//	

}
