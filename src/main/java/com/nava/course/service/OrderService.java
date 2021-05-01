package com.nava.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nava.course.entities.Order;
import com.nava.course.repositories.OrderRepository;

@Service // Estou deixando claro que é uma classe de serviço
//A classe de serviço estara responsavel por toda regra de negocio
public class OrderService {

	@Autowired // esta fazendo a injeção de dependencia de forma explicita
	private OrderRepository repository;

	public List<Order> findAll() {

		return repository.findAll();

	}

	public Order findById(Long id) {

		Optional<Order> obj = repository.findById(id);
		return obj.get();

	}

}
