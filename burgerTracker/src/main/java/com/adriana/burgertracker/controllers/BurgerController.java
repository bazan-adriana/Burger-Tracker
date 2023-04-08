package com.adriana.burgertracker.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adriana.burgertracker.models.Burger;
import com.adriana.burgertracker.services.BurgerService;



@Controller
@RequestMapping("/burgers")
public class BurgerController {
	private final BurgerService burgerServ;
	public BurgerController(BurgerService burgerServ) {
		this.burgerServ = burgerServ;
	}
	
	@GetMapping("/create")
	public String createBurger(@ModelAttribute("burger") Burger burger, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/login/reg";
		}
		return "burger/create.jsp";
	}
	
	@PostMapping("/process/create")
	public String processCreateBurger(@Valid @ModelAttribute("burger") Burger burger, BindingResult result) {
		if(result.hasErrors()) {
			return "burger/create.jsp";
		}
		burgerServ.create(burger);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editBurger(@PathVariable("id") Long id, Model model) {
		model.addAttribute("burger", burgerServ.getOne(id));
		return "burger/edit.jsp";
	}
	
	@PutMapping("/process/edit/{id}")
	public String processEditBurger(@Valid @ModelAttribute("burger") Burger burger, BindingResult result) {
		if(result.hasErrors()) {
			return "burger/edit.jsp";
		}
		burgerServ.update(burger);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String displayOneBurger(@PathVariable("id") Long id, Model model) {
		Burger burger = burgerServ.getOne(id);
		model.addAttribute("burger", burger);
		return "burger/displayOne.jsp";
	}
	
	@DeleteMapping("/{id}")
	public String deleteBurger(@PathVariable("id") Long id) {
		burgerServ.delete(id);
		return "redirect:/";
	}
	
}