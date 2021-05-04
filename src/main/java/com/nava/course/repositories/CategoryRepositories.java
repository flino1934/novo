package com.nava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nava.course.entities.Category;

@Repository
public interface CategoryRepositories extends JpaRepository<Category, Long> {//esta herdando os metdos do jpa repository

}
