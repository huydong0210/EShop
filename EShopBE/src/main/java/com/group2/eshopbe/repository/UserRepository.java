package com.group2.eshopbe.repository;

import com.group2.eshopbe.entity.EUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<EUser, Long> {
    Optional<EUser> findByUsername(String username);
    Boolean existsByUsername(String username);

}
