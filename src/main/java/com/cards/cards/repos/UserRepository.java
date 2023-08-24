package com.cards.cards.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cards.cards.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String name);

	boolean existsByUsername(String name);

	Optional<Users> findById(long id);

}