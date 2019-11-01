package eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomePageController {

	// Default request mapping
	@RequestMapping
	public String sveikiAtvyke(Model model, RedirectAttributes redirectAttributes) {
		// Į modelį dedamas atributas ir jo reikšmė
		model.addAttribute("pasisveikinimas", "Sveiki atvykę į mano e-shop'ą!!");
		model.addAttribute("sloganas", "Vienintelis ir nuostabiausias e-shop'as Lietuvoje (ir ne tik...)");

		// Flash atributai kurie nedingsta iš redirected sesijos bet tik vienai sesijai!
		redirectAttributes.addFlashAttribute("pasisveikinimas", "laba diena redirected");
		redirectAttributes.addFlashAttribute("sloganas", "Už Lietuvą vyrai!");

		return "redirect:/welcome/greeting";
	}

	@RequestMapping("/welcome")
	public ModelAndView allProducts() {
		// Inkapsuliuotas modelis
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("pasisveikinimas", "Kitoks pasisveikinimas");
		modelAndView.addObject("sloganas", "Inkapsuliuotas sloganas");
		modelAndView.setViewName("SveikiAtvyke");
		return modelAndView;
	}

	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "SveikiAtvyke";
	}

}
