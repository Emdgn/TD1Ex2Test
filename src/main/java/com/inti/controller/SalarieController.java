package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Salarie;
import com.inti.repository.ISalarieRepository;

@Controller
public class SalarieController {

	@Autowired
	ISalarieRepository isr;

	@GetMapping("enregistrerSalarie")
	public String enregistrerSalarie() {
		return "enregistrerSalarie";
	}

	@PostMapping("enregistrerSalarie")
	public String enregistrerSalarie(@ModelAttribute("salarie") Salarie salarie) {
		isr.save(salarie);
		return "enregistrerSalarie";
	}

	@GetMapping("listeSalarie")
	public String listeSalarie(Model m) {
		m.addAttribute("listeSalarie", isr.findAll());
		return "listeSalarie";
	}

	@GetMapping("supprimerSalarie/{id}")
	public String supprimerSalarie(@PathVariable("id") int id) {
		isr.delete(isr.getReferenceById(id));
		return "redirect:/listeSalarie";
	}

	@GetMapping("modifierSalarie/{id}")
	public String modifierSalarie(@PathVariable("id") int id, Model m) {
		m.addAttribute("salarie", isr.getReferenceById(id));
		return "modifierSalarie";
	}

	@PostMapping("modifierSalarie/{id}")
	public String modifierSalarie(@ModelAttribute("salarie") Salarie salarie) {
		isr.save(salarie);
		return "redirect:/listeSalarie";
	}

}
