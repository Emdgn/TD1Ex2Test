package com.inti.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Salarie;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SalarieRepositoryTests {

	@Autowired
	ISalarieRepository isr;
	
	@BeforeAll
	public static void debut() {
		
	}
	
	@BeforeEach
	public void setUp() {
		System.out.println("Début test fonction");
		Salarie salarie = new Salarie();
	}
	
	
	@Test
	public void saveSalarie() {
		Salarie salarie = new Salarie("nom1", "prenom1", "mail@mail.com");
		
		Salarie salarieSaved = isr.save(salarie);
		
		assertThat(salarieSaved).isNotNull();
		assertThat(salarieSaved.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void saveSalarieWithArgsFacultativesTest() {
		Salarie salarie = new Salarie();
		salarie.setNom("okok");
		
		Salarie salarieSaved = isr.save(salarie);
		
		assertThat(salarieSaved).isNotNull();
		assertThat(salarieSaved.getId()).isGreaterThan(0);
		assertThat(salarieSaved.getNom()).isEqualTo("okok");
		assertThat(salarieSaved.getPrenom()).isNull();
		
	}
	
	
	@Test
	public void saveSalarieExceptionParamObligatoireTest() {
		Salarie salarie = new Salarie();
		salarie.setPrenom("Prenom2");
		
		Assertions.assertThrows(Exception.class, () -> isr.save(salarie));
		
	}
	
	
//	@Test
//	public void saveSalarieWithUniqueParamTest() {
//		
//		Salarie salarie = new Salarie();
//		salarie.setNom("Nom2");
//		salarie.setEmail("test@test.fr");
//		isr.save(salarie);
//		
//		Salarie salarie2 = new Salarie();
//		salarie2.setNom("Nommm");
//		salarie2.setEmail("test@test.fr");
//		
//		Assertions.assertThrows(Exception.class, () -> isr.save(salarie2));
//		
//	}
	
	
	@Test
	public void saveSalarieWithToLongName() {
		
		Salarie salarie = new Salarie();
		salarie.setNom("Nooooooooooooom");
		
		Assertions.assertThrows(Exception.class, () -> isr.save(salarie));
		
	}
	
	
	@Test
	public void deleteSalarieTest() {
		
		Salarie salarie = new Salarie();
		salarie.setNom("lalala");
		isr.save(salarie);
		
		int id = salarie.getId();
		
		isr.delete(salarie);
		
		Assertions.assertThrows(Exception.class, () -> isr.getReferenceById(id));
		
	}
	
	
//	@Test
//	public void deleteSalarieNotFound() {
//		
//		Salarie salarie = new Salarie(80, "okok", "ohoh", "e@e.fr");
//		
//		Assertions.assertThrows(Exception.class, () -> isr.delete(salarie));
//		
//	}
	
	
//	@Test
//	public void deleteSalarieNull() {
//		
//		Assertions.assertThrows(Exception.class, () -> isr.delete(null));
//		
//	}
	
	
	@Test
	public void updateSalarieTest() {
		
		Salarie salarie = new Salarie("nom", "p", "mail@m.com");
		Salarie salarieSaved = isr.save(salarie);
		
		salarieSaved.setNom("new");
		Salarie salarieMofied = isr.save(salarieSaved);
		
		assertThat(salarieMofied).isNotNull();
		assertThat(salarieMofied.getNom()).isEqualTo("new");
		
	}
	
	
//	@Test
//	public void updateSalarieUniqueParamTest() {
//		
//		Salarie salarie1 = new Salarie("nom", "p", "mail@m.com");
//		Salarie salarie2 = new Salarie("nom", "p", "mail@mail.com");
//		
//		Salarie salarieSaved1 = isr.save(salarie1);
//		Salarie salarieSaved2 = isr.save(salarie2);
//		
//		salarieSaved2.setEmail("mail@m.com");
//		
//		Assertions.assertThrows(Exception.class, () -> isr.save(salarieSaved2));
//		
//	}
	
	
	@Test
	public void updateSalarieNullTest() {
		
		Salarie salarie= null;
		
		Assertions.assertThrows(Exception.class, () -> salarie.setPrenom("test"));
		
	}
	
	
	@Test
	public void getSalarieTest() {
		
		Salarie salarie = new Salarie("nom", "p", "mail@m.com");
		Salarie salarieSaved = isr.save(salarie);
		
		Salarie salarie1 = isr.getReferenceById(salarieSaved.getId());
		
		assertThat(salarie1).isNotNull();
		assertThat(salarie1.getNom()).isEqualTo("nom");
		assertThat(salarie1).isEqualTo(salarieSaved);
		
	}
	
	
//	@Test
//	public void getSalarieNullTest() {
//		
//		Assertions.assertThrows(Exception.class, () -> isr.getReferenceById(150));
//	}
	
	
//	@Test
//	public void getAllSalarieTest() {
//		
//		Salarie s1 = new Salarie("nom", "p", "mail@m.com");
//		Salarie s2 = new Salarie("nom2", "p2", "a@a.fr");
//		Salarie salarieSaved1 = isr.save(s1);
//		Salarie salarieSaved2 = isr.save(s2);
//		
//		List<Salarie> listeS = isr.findAll();
//		
//		assertThat(listeS).isNotEmpty();
//		assertThat(listeS).hasSize(2);
////		assertThat(listeS.get(0).getClass()).hasSameClassAs(Salarie.class);
//		
//	}
	
	
//	@Test
//	public void getSalarieListeVide() {
//		
//		List<Salarie> listeS = isr.findAll();
//		
//		assertThat(listeS).isEmpty();
//		assertThat(listeS).hasSize(0);
//	}
	
	
	
	
}
