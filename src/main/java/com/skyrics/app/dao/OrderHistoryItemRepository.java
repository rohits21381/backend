package com.skyrics.app.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skyrics.app.entities.OrderDetails;
import com.skyrics.app.entities.OrderHistoryItem;

import jakarta.transaction.Transactional;

public interface OrderHistoryItemRepository extends JpaRepository<OrderHistoryItem, Integer>{

	@Query(value = "select * from order_history_item where order_history_order_history_id =:orderHistoryId",
            countQuery = "select count(*) from order_history_item where order_history_order_history_id =:orderHistoryId",
            nativeQuery = true)
	Page<OrderHistoryItem> findByOrderHistory(@Param("orderHistoryId")Integer orderHistoryId, Pageable pageInf );
	
	Set<OrderHistoryItem> findByOrderDetails(OrderDetails orderDetails);


	@Query(value = "select * from order_history_item where order_history_order_history_id =:orderHistoryId and product_post_id =:productId",nativeQuery = true)
	OrderHistoryItem findOrderHistoryItemByOrderHistoryIdAndProductId(@Param("orderHistoryId")Integer orderHistoryId,@Param("productId") Integer productId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from order_history_item where order_history_order_history_id =:orderHistoryId and product_post_id =:productId",nativeQuery = true)
	void deleteOrderHistoryItemByOrderHistoryIdAndProductId(@Param("orderHistoryId")Integer orderHistoryId,@Param("productId") Integer productId);

}
