package com.th.app.estock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.service.interfaces.LovBusinessService;

@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
@RestController
@RequestMapping("/lov")
public class LovController {
	
	@Autowired
	private LovBusinessService lovBusinessService;
	
	@GetMapping("/getMProductType")
	@Procedure(value = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<?>> getMProductType() {
		return ResponseEntity.ok(lovBusinessService.getMProductType());
	}

}
