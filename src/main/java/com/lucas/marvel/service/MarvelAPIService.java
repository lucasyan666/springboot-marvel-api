package com.lucas.marvel.service;

import com.lucas.marvel.client.MarvelClient;
import com.lucas.marvel.models.Chars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class MarvelAPIService {

    private final MarvelClient marvelClient;
    public MarvelAPIService(MarvelClient marvelClient){
        this.marvelClient = marvelClient;
    }
    @Cacheable("chars")
    public List<Integer> getCharID() {
        log.info("Getting Character IDs...");
        return marvelClient.getCharacters()
                           .getData()
                           .getResults()
                           .stream()
                           .map(Chars::getID)
                           .toList();
    }

    public Chars getChar(Integer id) {
        log.info("Getting Character by ID");
        return marvelClient.getCharacterByID(id)
                           .getData()
                           .getResults()
                           .get(0);
    }
}
