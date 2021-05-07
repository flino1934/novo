package com.nava.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nava.course.entities.User;
import com.nava.course.repositories.UserRepository;
import com.nava.course.service.exceptions.DatabaseExceptions;
import com.nava.course.service.exceptions.ResourceNotFoundException;

@Service // estou registrando minha classe no spring como uma classe de serviço
//A classe de serviço é aonde fica toda a regra de negocio
public class UserService {

	@Autowired // esta fazendo a injeção de dependencia
	private UserRepository repository;

	public List<User> findAll() {// vai ser mandado para UserResource
		return repository.findAll();
	}

	public User findById(Long id) {// vai ser mandado para UserResource

		Optional<User> obj = repository.findById(id);// estamos armazenando o usuario do id correspondente no obj
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}

	public User insert(User obj) {

		return repository.save(obj);

	}
	
	public void deleteById(Long id) {
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseExceptions(e.getMessage());
		}
	}
	
	public User update(long id, User obj) {
		
		User entity = repository.getOne(id);//vai deixar o objeto monitorado pelo jpa pronto para uso
		updateData(entity,obj);
		
		return repository.save(entity);
		
	}

	private void updateData(User entity, User obj) {
		
		entity.setName(obj.getName());
		entity.setEmail(obj.getName());
		entity.setPhone(obj.getPhone());
		
	}

}
