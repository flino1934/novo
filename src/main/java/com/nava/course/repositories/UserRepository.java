package com.nava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nava.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	

}
