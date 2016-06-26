package com.baotoan.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkHandler {
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String registryPage() {
		return "product_detail";
	}
	
	@RequestMapping(value="/about", method = RequestMethod.GET)
	public String aboutPage() {
		return "about_us";
	}
	
	@RequestMapping(value="404error", method = RequestMethod.GET)
	public String error404() {
		return "404error";
	}
	
	@RequestMapping(value="sitemap", method = RequestMethod.GET)
	public String sitePage() {
		return "sitemap";
	}
	
	@RequestMapping(value="faq", method = RequestMethod.GET)
	public String faqPage() {
		return "faq";
	}
	
	@RequestMapping(value="delivery", method = RequestMethod.GET)
	public String deliveryPage() {
		return "delivery";
	}
	
}