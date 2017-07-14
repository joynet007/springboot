package com.liang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class LiangApplication {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "/index";
	}

	public static void main(String[] args) {
		SpringApplication.run(LiangApplication.class, args);
	}
}
