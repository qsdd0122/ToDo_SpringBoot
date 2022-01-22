package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
	// 일라스틱 빈스톡이 어플리케이션 체크
	@GetMapping("/")
	public String helthCheck() {
		return "The service is up and running...";
	}
}
