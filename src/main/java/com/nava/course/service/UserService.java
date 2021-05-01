package com.nava.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nava.course.entities.User;
import com.nava.course.repositories.UserRepository;

@Service // estou registrando minha classe no spring como uma classe de serviço
//A classe de serviço é aonde fica toda a regra de negocio
public class UserService {

	@Autowired // esta fazendo a injeção de dependencia
	private UserRepository repository;

	public List<User> findAll() {// vai ser mandado para UserResource
		return repository.findAll();
	}
	
	public User findById(Long id) {// vai ser mandado para UserResource
		
		Optional<User> obj = repository.findById(id);//estamos armazenando o usuario do id correspondente no obj
		return obj.get();
		
	}

}
