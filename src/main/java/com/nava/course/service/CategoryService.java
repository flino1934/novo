package com.nava.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nava.course.entities.Category;
import com.nava.course.repositories.CategoryRepositories;

@Service//esta anota com,o uma classe de serviço esta classe será responsavel por toda a lógica envolvida 
public class CategoryService {

	@Autowired//estamos fazendo a injeção de dependencia de 
	private CategoryRepositories repositories;
	
	public List<Category> findAll(){
		
		return repositories.findAll();
			
	}
	
	public Category findById(Long id){
		
		Optional<Category> obj = repositories.findById(id);
		return obj.get();
	}
	
}
