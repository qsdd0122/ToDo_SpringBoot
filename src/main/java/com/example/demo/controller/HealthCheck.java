package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
	// �϶�ƽ ������ ���ø����̼� üũ
	@GetMapping("/")
	public String helthCheck() {
		return "The service is up and running...";
	}
}
