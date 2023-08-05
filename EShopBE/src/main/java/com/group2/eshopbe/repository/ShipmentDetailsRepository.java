package com.group2.eshopbe.repository;

import com.group2.eshopbe.entity.ShipmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentDetailsRepository extends JpaRepository<ShipmentDetails, Long> {

}
