package com.cards.cards.utils;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.cards.cards.entity.Users;
import com.cards.cards.repos.UserRepository;
import com.cards.cards.security.JwtUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneralUtil {
	private final UserRepository userRepository;
	private final JwtUtils jwtUtils;
	Gson gson = new Gson();

	public JsonObject jwtDecode(String token) {
		String[] parts = token.split("\\.");
//		JsonObject header = gson.fromJson(decode(parts[0]), JsonObject.class);
		JsonObject payload = gson.fromJson(decode(parts[1]), JsonObject.class);
		String signature = decode(parts[2]);
		return payload;
	}

	private static String decode(String encodedString) {
		return new String(Base64.getUrlDecoder().decode(encodedString));
	}

	public Users getUser(String token) {
		String username = jwtUtils.getUserNameFromJwtToken(token.split(" ")[1]);
		return userRepository.findByUsername(username).get();
	}
}
