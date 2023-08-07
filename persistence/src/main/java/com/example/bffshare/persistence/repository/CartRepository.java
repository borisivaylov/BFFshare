package com.example.bffshare.persistence.repository;

import com.example.bffshare.persistence.entity.Cart;
import com.example.bffshare.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {


}
