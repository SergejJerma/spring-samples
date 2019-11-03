package com.serjer.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.serjer.model.Review;
import com.serjer.repo.ReviewRepo;

@Controller
public class SiteController {
	
	@Autowired
	private ReviewRepo repo;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				dateFormat, false));
//	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("rv", new Review());
		model.addAttribute("reviews", repo.findAll());
		return "index";
	}
	
//	@GetMapping
//	public ModelAndView index(Model model) {
//		model.addAttribute("rv", new Review());
//        return new ModelAndView("site/index", Collections.singletonMap("reviews", repo.findAll()));
//    }
	//@RequestParam String reviewText
	 @PostMapping ()
	    public String createReview(ModelMap model, @ModelAttribute("rv") @Valid Review rv, BindingResult result) {
	if(result.hasErrors()) {
		return "index";
	}
		 repo.save(new Review(rv.getReviewText(), new Date()));
	 
	        return "redirect:/";
	    }
	 
	 @GetMapping("/delete")
		public String delete(ModelMap model, @RequestParam int id) {
			repo.deleteById(id);
			model.clear();
			return "redirect:/";
		}
	 @GetMapping("/update")
		public String update(ModelMap model, @RequestParam int id) {
		    Review review = repo.findById(id).get();
			model.addAttribute("rv", review);
			repo.deleteById(id);
			return "index";
		}
	 @PostMapping("/update")
		public String update(@ModelAttribute("rv") @Valid Review rv, BindingResult result) {
			if(result.hasErrors()) {
				return "index";
			}
		 repo.save(new Review(rv.getReviewText(), new Date()));
			return "redirect:/";
		}
}
