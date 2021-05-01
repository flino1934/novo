package com.nava.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nava.course.entities.User;
import com.nava.course.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired//está fazendo a injeção de dependencia 
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll();//através da injeção de dependencia vou chamar o metodo findAll da classe UserService
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {//através da injeção de dependencia vou chamar o metodo finddById da classe UserService
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
}
