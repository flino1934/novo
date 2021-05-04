package com.nava.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nava.course.entities.Product;
import com.nava.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {//vai acessar o repositorio e utilizar o metodo findAll()

		return repository.findAll();

	}

	public Product findById(Long id) {//vai acessar o repositorio e utilizar o metodo findById()

		Optional<Product> obj = repository.findById(id);

		return obj.get();

	}

}
