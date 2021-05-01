package com.nava.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nava.course.entities.Order;
import com.nava.course.service.OrderService;

@RestController
@RequestMapping(value = "/orders") // endereço do endpoint que vai chamar
public class OrderResource {

	@Autowired // esta fazendoa injeção de independecia de forma explicita
	private OrderService service;

	@GetMapping
	public ResponseEntity<List<Order>> findAll() {

		List<Order> obj = service.findAll();//esta pegando através da injeção de dependencia o serviço que esta sendo processado em OrderService

		return ResponseEntity.ok().body(obj);

	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){// vai buscar o pedido pelo id 
		
		Order obj = service.findById(id);//esta pegando através da injeção de dependencia o serviço que esta sendo processado em OrderService
		return ResponseEntity.ok().body(obj);
		
	}

}
