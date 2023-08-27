package com.cards.cards.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cards.cards.configs.UserRoles;
import com.cards.cards.dtos.CardDto;
import com.cards.cards.entity.Cards;
import com.cards.cards.entity.Users;
import com.cards.cards.exceptions.CustomException;
import com.cards.cards.repos.CardRepository;
import com.cards.cards.repos.UserRepository;
import com.cards.cards.security.JwtUtils;
import com.cards.cards.services.CardService;
import com.cards.cards.utils.GeneralUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {
	private final CardRepository cardRepository;
	private final GeneralUtil generalUtil;
	private final ModelMapper modelMapper;

	@Override
	public List<Cards> findAllCards(int pageNo, int pageSize, String sortBy, String sortDir, String token) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Users users = generalUtil.getUser(token);
		Page<Cards> cards = null;
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.findAll(pageable);
		} else {
			cards = cardRepository.findByUser(users, pageable);
		}
		return cards.getContent();
	}

	@Override
	public Cards create(CardDto cardDto, String token) {
		Users users = generalUtil.getUser(token);
		Cards cards = modelMapper.map(cardDto, Cards.class);
		cards.setUser(users);
		return cardRepository.save(cards);
	}

	@Override
	public Cards findByName(String name, String token) {
		Users users = generalUtil.getUser(token);
		Cards cards = null;
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.findByName(name);
		} else {
			cards = cardRepository.findByUserAndName(users, name);
		}
		if (Objects.isNull(cards)) {
			throw new CustomException("Card with name " + name + " not found or user has no access to it");
		}
		return cards;
	}

	@Override
	public List<Cards> findByColor(String color, int pageNo, int pageSize, String sortBy, String sortDir,
			String token) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Users users = generalUtil.getUser(token);
		Page<Cards> cards = null;
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.findByColor(color, pageable);
		} else {
			cards = cardRepository.findByUserAndColor(users, color, pageable);
		}
		return cards.getContent();
	}

	@Override
	public List<Cards> findByStatus(String color, int pageNo, int pageSize, String sortBy, String sortDir,
			String token) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Users users = generalUtil.getUser(token);
		Page<Cards> cards = null;
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.findByStatus(color, pageable);
		} else {
			cards = cardRepository.findByUserAndStatus(users, color, pageable);
		}
		return cards.getContent();
	}

	@Override
	public List<Cards> findByDate(LocalDate date, int pageNo, int pageSize, String sortBy, String sortDir,
			String token) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Users users = generalUtil.getUser(token);
		Page<Cards> cards = null;
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.findByCreatedAt(date, pageable);
		} else {
			cards = cardRepository.findByUserAndCreatedAt(users, date, pageable);
		}
		return cards.getContent();
	}

	@Override
	public Cards findById(Long id, String token) {
		Users users = generalUtil.getUser(token);
		Cards cards = null;
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.findById(id).get();
		} else {
			cards = cardRepository.findByUserAndId(users, id);
		}
		if (Objects.isNull(cards)) {
			throw new CustomException("Card with id " + id + " not found or user has no access to it");
		}
		return cards;
	}

	@Override
	public Cards update(Long id, Cards cards, String token) {
		Users users = generalUtil.getUser(token);
		Cards card2 = cardRepository.findById(id)
				.orElseThrow(() -> new CustomException("Card with id " + id + " not found"));
		if (!Objects.isNull(cards.getName())) {
			card2.setName(cards.getName());
		}
		if (!Objects.isNull(cards.getColor())) {
			card2.setColor(cards.getColor());
		}
		if (!Objects.isNull(cards.getStatus())) {
			card2.setStatus(cards.getStatus());
		}
		if (!Objects.isNull(cards.getDescription())) {
			card2.setDescription(cards.getDescription());
		}
		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cards = cardRepository.save(card2);
		} else if (users.getId() == card2.getUser().getId()) {
			cards = cardRepository.save(card2);
		} else {
			throw new CustomException("You can't update a card you didn't create");
		}
		return cards;
	}

	@Override
	public boolean delete(Long id, String token) {
		Users users = generalUtil.getUser(token);
		Cards card = cardRepository.findById(id)
				.orElseThrow(() -> new CustomException("Card with id " + id + " not found"));

		if (users.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
			cardRepository.delete(card);
		} else if (users.getId() == card.getUser().getId()) {
			cardRepository.delete(card);
		} else {
			throw new CustomException("You can't delete a card you didn't create");
		}
		return true;
	}
}
