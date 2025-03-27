package com.skyrics.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.Cart;
import com.skyrics.app.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findCartByUser(User user);
}
