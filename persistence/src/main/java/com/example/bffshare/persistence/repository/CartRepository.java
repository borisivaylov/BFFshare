package com.example.bffshare.persistence.repository;

import com.example.bffshare.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<User, UUID> {
}
