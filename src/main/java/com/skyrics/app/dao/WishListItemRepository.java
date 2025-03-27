package com.skyrics.app.dao;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skyrics.app.entities.WishList;
import com.skyrics.app.entities.WishListItem;

import jakarta.transaction.Transactional;

public interface WishListItemRepository extends JpaRepository<WishListItem, Integer>{
	
	Set<WishListItem> findByWishList(WishList wishList);
	
	@Query(value = "select * from wish_list_item where wish_list_wish_list_id =:whisListId and product_post_id =:productId",nativeQuery = true)
	WishListItem findWishListItemByWishListIdAndProductId(@Param("whisListId")Integer wishListId,@Param("productId") Integer productId);

	@Modifying
	@Transactional
	@Query(value = "delete from wish_list_item where wish_list_wish_list_id =:whisListId and product_post_id =:productId",nativeQuery = true)
	void deleteWishListItemByWishListIdAndProductId(@Param("whisListId")Integer wishListId,@Param("productId") Integer productId);
}
