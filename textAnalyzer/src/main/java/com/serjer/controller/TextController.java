package com.serjer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.serjer.model.Text;
import com.serjer.service.WordCounter;


@Controller
public class TextController {
	
	@Autowired
	WordCounter wc;
	
	@GetMapping("/input")
	public String input() {
		return "input";
	}
	@PostMapping("/input")
	public String output(Model model, @RequestParam String text) {
		Text request = new Text();
		request.setWords(wc.count(text));
		model.addAttribute("text", request);
		return "output";
	}
}
