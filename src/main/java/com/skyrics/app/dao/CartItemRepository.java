package com.skyrics.app.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skyrics.app.entities.Cart;
import com.skyrics.app.entities.CartItem;

import jakarta.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	Set<CartItem> findByCart(Cart Cart);
	
	@Query(value = "select * from cart_item where cart_cart_id =:cartId and product_post_id =:productId",nativeQuery = true)
	CartItem findCartItemByCartIdAndProductId(@Param("cartId")Integer cartId,@Param("productId") Integer productId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from cart_item where cart_cart_id =:cartId and product_post_id =:productId",nativeQuery = true)
	void deleteCartItemByCartIdAndProductId(@Param("cartId")Integer cartId,@Param("productId") Integer productId);

	@Modifying
	@Transactional
	@Query(value = "delete from cart_item where cart_cart_id =:cartId ",nativeQuery = true)
	Integer deleteAllCartItemByCartId(@Param("cartId")Integer cartId);

}
