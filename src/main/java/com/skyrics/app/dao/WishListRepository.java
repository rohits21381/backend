package com.skyrics.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyrics.app.entities.User;
import com.skyrics.app.entities.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer>{

	WishList findWishListByUser(User user);
}
