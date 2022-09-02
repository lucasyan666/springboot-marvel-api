package com.lucas.marvel.client;

import com.lucas.marvel.models.MarvelResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class MarvelClient {

    private final String publicKey = "22a66c451d78a0ae58b99788a7a07da1";
    private final String privateKey = "be0b10a0505455f5bd87b7c621c8b6a84fe470bb";
    private static final String baseUri = "http://gateway.marvel.com/v1/public/";
    private final int limit = 100;
    private final RestTemplate restTemplate;
    public MarvelClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //GET /v1/public/characters
    public MarvelResponse getCharacters() {
        String URI = baseUri + "characters?" + authInfo() + "&limit=" + limit;
        return restTemplate.getForObject(URI, MarvelResponse.class);
    }

    //GET /v1/public/characters/{characterId}
    public MarvelResponse getCharacterByID(Integer id) {
        String URI = baseUri + "characters/" + id + "?" + authInfo();
        return restTemplate.getForObject(URI, MarvelResponse.class);
    }

    public String authInfo() {
        StringBuilder str = new StringBuilder();
        long time = System.currentTimeMillis();
        str.append("ts=");
        str.append(time);
        str.append("&apikey=");
        str.append(publicKey);
        str.append("&hash=");
        str.append(DigestUtils.md5DigestAsHex((time + privateKey + publicKey).getBytes()));

        return str.toString();
    }
}
