package com.sprinroll.urlshortener;

import com.mifmif.common.regex.Generex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

@org.springframework.stereotype.Service
public class Service {
    private final HashMap<String, String> cache;
    private final Generex generex;
    Service(){
        cache = new HashMap<>();
        generex = new Generex("([a-z]|[0-9]|[A-Z]){15}");
    }
    public String process(String url) throws Exception {
        if(cache.containsKey(url))
            return cache.get(url);
        else if(validUrl(url)) {
            cache.put(url, shorten(url));
            return cache.get(url);
        }
        else
            throw new Exception("Invalid URL!");
    }

    public ArrayList<String> process(String [] urls) {
        ArrayList<String> arrayList = new ArrayList<>();
        for(String url : urls){
            try {
                arrayList.add(process(url));
            } catch (Exception exception){
                arrayList.add(exception.getMessage());
            }
        }
        return arrayList;
    }

    private boolean validUrl(String url) {
        String regex = "(https://|http://)?(\\w+\\.)?\\w+\\.\\w{2,3}(\\/.+)?";
        return Pattern.matches(regex, url);
    }

    private String shorten(String url) {
        return "spri.ng/"+generex.random();
    }
}
