package com.skyrics.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.OrderHistory;
import com.skyrics.app.entities.User;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {

	OrderHistory findOrderHistoryByUser(User user);
}
