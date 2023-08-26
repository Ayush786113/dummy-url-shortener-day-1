package com.sprinroll.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {
    @Autowired
    private Service service;
    @PostMapping(value = "/shorten", params = {"url"})
    public ResponseEntity<String> shortenUrl(@RequestParam String url) {
        try {
            return new ResponseEntity<>(service.process(url), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/shorten")
    public ResponseEntity<ArrayList<String>> shortenUrl(@RequestBody String [] urls) {
        return new ResponseEntity<>(service.process(urls), HttpStatus.OK);
    }
}
