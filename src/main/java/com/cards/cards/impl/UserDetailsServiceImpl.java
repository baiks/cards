package com.cards.cards.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.cards.cards.entity.Users;
import com.cards.cards.exceptions.CustomException;
import com.cards.cards.repos.UserDetailsImpl;
import com.cards.cards.repos.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String name) {
		Users user = userRepository.findByUsername(name)
				.orElseThrow(() -> new CustomException("Invalid Username supplied"));
		return UserDetailsImpl.build(user);
	}

}