package com.cards.cards.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.cards.cards.dtos.CardDto;
import com.cards.cards.entity.Cards;

public interface CardService {
	public Cards create(CardDto cardDto, String token);

	public List<Cards> findAllCards(int pageNo, int pageSize, String sortBy, String sortDir, String token);

	public Cards findByName(String name, String token);

	public List<Cards> findByColor(String color, int pageNo, int pageSize, String sortBy, String sortDir, String token);

	public List<Cards> findByStatus(String status, int pageNo, int pageSize, String sortBy, String sortDir,
			String token);

	public List<Cards> findByDate(LocalDate date, int pageNo, int pageSize, String sortBy, String sortDir,
			String token);

	public Cards findById(Long id, String token);

	public Cards update(Long id, Cards cards, String token);

	public boolean delete(Long id, String token);
}
