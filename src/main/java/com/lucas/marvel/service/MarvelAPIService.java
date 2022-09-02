package com.lucas.marvel.service;

import com.lucas.marvel.client.MarvelClient;
import com.lucas.marvel.models.Chars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MarvelAPIService {

    @Autowired
    private MarvelClient marvelClient;

    @Cacheable("chars")
    public List<Integer> getCharID() {
        List<Integer> CharIDs = new ArrayList<>();
        for (Chars character : marvelClient.getCharacters().data.results) {
            CharIDs.add(character.getID());
        }
        return CharIDs;
    }

    public Chars getChar(Integer id) {
        return marvelClient.getCharacterByID(id).data.results.get(0);
    }
}
