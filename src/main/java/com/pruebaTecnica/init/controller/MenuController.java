package com.pruebaTecnica.init.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTecnica.init.services.MenuService;


@RestController
@RequestMapping("/menu")
public class MenuController {
	   @Autowired
	    private MenuService menuService;

	    @PostMapping("/import")
	    public ResponseEntity<?> importMenu() throws IOException {
	        menuService.processMenuJson();
	        return ResponseEntity.ok("Menu importado exitosamente");
	    }
}
