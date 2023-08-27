package com.cards.cards.configs;

public enum CardStatuses {
	TODO("To Do"), INPROGRESS("In Progress"), DONE("Done");

	private final String status;

	CardStatuses(String status) {
		this.status = status;
	}

	public String getCardStatus() {
		return status;
	}

}
