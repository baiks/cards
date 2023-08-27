package com.cards.cards.configs;

public class Constants {
	public static class Jwt {
		public static String secret = "card@123";
		public static int expiry = 600;
	}

	public interface Sort {
		String DEFAULT_PAGE_NUMBER = "0";
		String DEFAULT_PAGE_SIZE = "10";
		String DEFAULT_SORT_BY = "id";
		String DEFAULT_SORT_DIRECTION = "asc";
	}
}
