package com.group2.eshopbe.repository;

import com.group2.eshopbe.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    @Query(value = "select * from OrderDetails  where orders_id = ?1",nativeQuery = true)
    List<OrderDetails> findOrderDetailsByOderID(Long OrderID);
}
