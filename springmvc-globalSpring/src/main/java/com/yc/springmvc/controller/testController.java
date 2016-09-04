package com.yc.springmvc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping(value = "/global")
public class testController {
	@RequestMapping(value = "/test", method = {RequestMethod.GET})
	public String test(HttpServletRequest request, Model model) {
		if (!model.containsAttribute("contentModel")) {
			RequestContext requestContext = new RequestContext(request);
			model.addAttribute("title", requestContext.getMessage("title"));
			model.addAttribute("welcome", requestContext.getMessage("welcome"));
		}
		return "globaltest";
	}
}
