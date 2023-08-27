package com.cards.cards.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cards.cards.configs.Constants;
import com.cards.cards.dtos.CardDto;
import com.cards.cards.entity.Cards;
import com.cards.cards.services.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Tag(name = "CARD", description = "Cards management")
public class CardController {

	private final CardService cardService;

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@Operation(summary = "Create a card", description = "Returns the details of a card created.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "{\n" + "  \"name\": \"string\",\n" + "  \"color\": \"Z_joAIh\",\n"
			+ "  \"status\": \"TODO\",\n" + "  \"description\": \"string\"\n" + "}")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "POST: /create") })
	public ResponseEntity<Cards> register(@Valid @RequestBody CardDto cardDto,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return new ResponseEntity<>(cardService.create(cardDto, token), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	@Operation(summary = "Get cards", description = "Returns all the cards user has access to.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "GET: /all") })
	public List<Cards> all(
			@RequestParam(value = "pageNo", defaultValue = Constants.Sort.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.Sort.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.Sort.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Constants.Sort.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.findAllCards(pageNo, pageSize, sortBy, sortDir, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/name/{name}")
	@Operation(summary = "Get a card by name", description = "Filter a card user has access to by name.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "/name/test")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "GET: /name/:name") })
	public Cards findByName(@PathVariable String name,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.findByName(name, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/color/{color}")
	@Operation(summary = "Get cards by color", description = "Filter cards user has access to by color.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "/color/#00000H")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "GET: /color/:color") })
	public List<Cards> findByColor(@PathVariable String color,
			@RequestParam(value = "pageNo", defaultValue = Constants.Sort.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.Sort.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.Sort.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Constants.Sort.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.findByColor(color, pageNo, pageSize, sortBy, sortDir, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status/{status}")
	@Operation(summary = "Get cards by color", description = "Filter cards user has access to by status.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "/status/To do")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "GET: /status/:status") })
	public List<Cards> findByStatus(@PathVariable String status,
			@RequestParam(value = "pageNo", defaultValue = Constants.Sort.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.Sort.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.Sort.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Constants.Sort.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.findByStatus(status, pageNo, pageSize, sortBy, sortDir, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/date/{date}")
	@Operation(summary = "Get cards by date", description = "Filter cards user has access to by date created.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "/date/2023-08-25")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "GET: /date/:date") })
	public List<Cards> findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@RequestParam(value = "pageNo", defaultValue = Constants.Sort.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.Sort.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.Sort.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Constants.Sort.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.findByDate(date, pageNo, pageSize, sortBy, sortDir, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
	@Operation(summary = "Get a card by id", description = "Filter cards user has access to by id.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "/status/1")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "GET: /id/:id") })
	public Cards findById(@PathVariable Long id,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.findById(id, token);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/edit/{id}")
	@Operation(summary = "Update a card", description = "Update a card user has access to.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "{\n" + "\"color\": \"#56745U\"\n" + "}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "PATCH: /id/:id") })
	public Cards update(@PathVariable Long id, @RequestBody @Valid Cards cards,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.update(id, cards, token);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	@Operation(summary = "Delete a card", description = "Delete a card user has access to.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "/delete/1")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "PATCH: /id/:id") })
	public boolean delete(@PathVariable Long id,
			@RequestHeader(value = "Authorization", required = false) String token) {
		return cardService.delete(id, token);
	}

}