package com.pucminas.easyfarma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView("hello"); // Arquivo HTML na pasta templates
		modelAndView.addObject("nome", "Paulo");
		return modelAndView;
	}

	@GetMapping("/hello-model")
	public String hello(Model model) {
		model.addAttribute("nome", "Diego");
		return "hello"; // Arquivo HTML na pasta templates
	}

}
