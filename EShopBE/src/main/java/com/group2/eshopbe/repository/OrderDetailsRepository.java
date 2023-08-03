package com.group2.eshopbe.repository;

import com.group2.eshopbe.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    @Modifying
    @Query(value = "delete from order_details where product_id = ?1 and user_id = ?2 and status =0",nativeQuery = true)
    void deleteOrderDetailsByProductId(Long productID, Long userID);
}
