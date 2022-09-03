package com.lucas.marvel.service;

import com.lucas.marvel.client.MarvelClient;
import com.lucas.marvel.models.Chars;
import com.lucas.marvel.models.Data;
import com.lucas.marvel.models.MarvelResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarvelAPIServiceTest {
    @Mock
    MarvelClient marvelClient;

    MarvelAPIService marvelAPIService;

    @BeforeEach
    void setup() {
        marvelAPIService = new MarvelAPIService(marvelClient);
    }

    @Test
    void testCharacters() {
        // GIVEN
        MarvelResponse response = new MarvelResponse();
        Data data = new Data();
        List<Chars> chars = new ArrayList<>();
        Chars divit = new Chars();
        divit.setID(1011341);
        chars.add(divit);
        data.setResults(chars);
        response.setData(data);

        when(marvelClient.getCharacters()).thenReturn(response);

        // WHEN
        List<Integer> result = marvelAPIService.getCharID();

        // THEN
        assertEquals(1011341, result.get(0));
    }

    @Test
    void testGetChar() {
        //GIVEN
        MarvelResponse response = new MarvelResponse();
        List<Chars> cha = new ArrayList<>();
        Chars divit = new Chars();
        divit.setID(1011334);
        divit.setName("Divit");
        divit.setDescription("Turbo gliding java chad");
        cha.add(divit);
        Data data = new Data();
        data.setResults(cha);
        response.setData(data);

        when(marvelClient.getCharacterByID(1011334)).thenReturn(response);

        //WHEN
        Chars res = marvelAPIService.getChar(1011334);

        //THEN
        assertEquals(res.getName(), "Jazinda");
    }

}
