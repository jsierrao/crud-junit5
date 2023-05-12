package com.creando.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creando.security.Usuario;

@RestController
public class AfiliadoUserController {
	
	@PostMapping("/loguear")
	public Usuario login(@RequestParam("user")String username, @RequestParam("password") String password ) {
		return null;
	}

}
