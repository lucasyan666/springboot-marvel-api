package com.lucas.marvel.controller;

import com.lucas.marvel.models.Chars;
import com.lucas.marvel.service.MarvelAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Slf4j
public class MarvelController {
    private final MarvelAPIService marvelAPIService;
    public MarvelController(MarvelAPIService marvelAPIService) {
        this.marvelAPIService = marvelAPIService;
    }

    @GetMapping(value = "/characters")
    public List<Integer> getCharID() {
        return marvelAPIService.getCharID();
    }

    @GetMapping(value = "/characters/{id}")
    public Chars getChar(@PathVariable Integer id) {
        log.info("Getting character by id {}", id);
        return marvelAPIService.getChar(id);
    }
}
