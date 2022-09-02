package com.lucas.marvel.service;

import com.lucas.marvel.models.Chars;
import com.lucas.marvel.models.MarvelResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
@Slf4j
public class MarvelAPIService {

    @Value("${marvel-domain}")
    private static String marvelDomain;
    private String publicKey = "22a66c451d78a0ae58b99788a7a07da1";
    private String privateKey = "be0b10a0505455f5bd87b7c621c8b6a84fe470bb";
    private static final String baseUri = "http://gateway.marvel.com/v1/public/";
    private int limit = 100;
    private int offset = 0;
    @Autowired
    RestTemplate restTemplate;
    @Cacheable("chars")
    public List<Integer> getCharID() {
        List<Integer> CharIDs = new ArrayList<>();
        String URI = baseUri + "characters?" + authInfo() + "&limit=" + limit;
        log.info(URI);
        while (offset > 1000) {
            MarvelResponse res = restTemplate.getForObject(URI, MarvelResponse.class);
            for (Chars character : res.data.results) {
                CharIDs.add(character.getID());
            }
            offset += limit;
        }
        return CharIDs;
    }

    @Cacheable("char")
    public Chars getChar(Integer id) {
        String URI = baseUri + "characters/" + id + "?" + authInfo();
        MarvelResponse res = restTemplate.getForObject(URI, MarvelResponse.class);
        return res.data.results.get(0);
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
