package br.com.plataforma.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
	
	@RequestMapping("/findiarista")
	private String irParaHome() {
		return "/index";

	}

}
