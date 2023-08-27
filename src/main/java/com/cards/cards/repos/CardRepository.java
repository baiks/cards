package com.cards.cards.repos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cards.cards.entity.Cards;
import com.cards.cards.entity.Users;

public interface CardRepository extends JpaRepository<Cards, Long> {
	Cards findByName(String name);

	Cards findByUserAndName(Users user, String name);

	Page<Cards> findByColor(String color, Pageable pageable);

	Page<Cards> findByUserAndColor(Users user, String name, Pageable pageable);

	Page<Cards> findByStatus(String status, Pageable pageable);

	Page<Cards> findByUserAndStatus(Users user, String name, Pageable pageable);

	Page<Cards> findByCreatedAt(LocalDate createdAt, Pageable pageable);

	Page<Cards> findByUserAndCreatedAt(Users user, LocalDate createdAt, Pageable pageable);

	Page<Cards> findByUser(Users user, Pageable pageable);

	Cards findByUserAndId(Users user, Long id);
}