package com.lucas.marvel.service;

import com.lucas.marvel.models.Chars;
import com.lucas.marvel.models.Data;
import com.lucas.marvel.models.MarvelResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarvelAPIServiceTest {
	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	MarvelAPIService marvelAPIService;

	@Test
	void testGetCharID(){
		// GIVEN
		MarvelResponse response = new MarvelResponse();
		Data data = new Data();
		List<Chars> chars = new ArrayList<>();
		Chars divit = new Chars();
		divit.setID(1011341);
		chars.add(divit);
		data.setResults(chars);
		response.setData(data);

		when(restTemplate.getForObject(anyString(), any())).thenReturn(response);

		// WHEN
		Chars res = marvelAPIService.getChar(1011341);

		// THEN
		assertEquals(res.getID(), 1011341);
	}

	@Test
	void  testGetChar(){
		//GIVEN
		MarvelResponse response = new MarvelResponse();
		List<Integer> charIds= new ArrayList<>();
		charIds.add(1011334);
		Data data = new Data();
		data.setIDResults(charIds);
		response.setData(data);

		when(restTemplate.getForObject(anyString(), any())).thenReturn(response);

		//WHEN
		List<Integer> res = marvelAPIService.getCharID();

		//THEN
		assertEquals(res.get(0), null);
	}

}
