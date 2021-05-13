package com.ufersa.webru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String formularioLogin() {
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/webru", method = RequestMethod.GET)
	public String webru() {
		return "webru";
	}
}
