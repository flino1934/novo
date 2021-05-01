package com.nava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nava.course.entities.Order;

@Repository // estou deixando claro para o spring que é uma classe de repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
