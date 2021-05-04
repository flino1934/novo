package com.nava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nava.course.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{// esta fazendo as operações basicas do crud através do JPARepository

}
