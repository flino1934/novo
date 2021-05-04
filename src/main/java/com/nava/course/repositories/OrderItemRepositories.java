package com.nava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nava.course.entities.OrderItem;

public interface OrderItemRepositories extends JpaRepository<OrderItem, Long>{

}
