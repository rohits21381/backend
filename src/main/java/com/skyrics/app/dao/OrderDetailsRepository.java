package com.skyrics.app.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.OrderDetails;
import com.skyrics.app.entities.User;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

}
