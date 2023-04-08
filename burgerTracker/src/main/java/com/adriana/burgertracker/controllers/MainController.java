package com.adriana.burgertracker.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.adriana.burgertracker.services.BurgerService;
import com.adriana.burgertracker.services.UserService;

@Controller
public class MainController {
	private final BurgerService burgerServ;
	private final UserService userServ;
	public MainController(BurgerService burgerServ, UserService userServ) {
		this.burgerServ = burgerServ;
		this.userServ = userServ;
	}
	
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/login/reg";
		}
		model.addAttribute("allBurgers", burgerServ.getAll());
		model.addAttribute("loggedInUser", userServ.getOne((Long)session.getAttribute("user_id")));
		return "main/dashboard.jsp";
		
	}
}
