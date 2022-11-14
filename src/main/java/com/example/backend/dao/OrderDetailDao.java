package com.example.backend.dao;


import com.example.backend.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
}