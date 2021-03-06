package com.nava.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nava.course.entities.Category;
import com.nava.course.service.CategoryService;

@RestController
@RequestMapping("/categories") // este será o endpoint
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {

		List<Category> obj = service.findAll();
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {

		Category category = service.findById(id);
		return ResponseEntity.ok().body(category);

	}

}
