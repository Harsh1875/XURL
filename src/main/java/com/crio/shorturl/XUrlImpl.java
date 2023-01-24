package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

public class XUrlImpl implements XUrl{

    // key = short url && value = long url
    HashMap<String,String> shortFind = new HashMap<String,String>(); 
    HashMap<String,Integer> countHit = new HashMap<>(); 
    private String s = "http://short.url/";
    private String AplhaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";

    void addCountMap(String longUrl) {
        if (countHit.get(longUrl) == null) {
            countHit.put(longUrl, 1);
        }
        else {
            countHit.put(longUrl, countHit.get(longUrl) + 1);
        }
    }

    @Override
    public String registerNewUrl(String longUrl) {

        for (Map.Entry<String,String> entry : shortFind.entrySet())
        {
            if (longUrl.equals(entry.getValue()))
            {
                addCountMap(longUrl);
                return entry.getKey();
            }
        }

        StringBuilder sb = new StringBuilder(s);
        for (int i=0 ; i<9 ;i++) {
                int index = (int) (AplhaNumeric.length() * Math.random());
                sb.append(AplhaNumeric.charAt(index));
        }
        String shrt = sb.toString();
        shortFind.put(shrt, longUrl);
        addCountMap(longUrl);
        return shrt;
    }

    public String registerNewUrl(String longUrl, String shortUrl) {
        if (shortFind.containsKey(shortUrl)) {
            return null;
        }
        shortFind.put(shortUrl,longUrl);
        addCountMap(longUrl);
        return shortUrl;
    }

    public String getUrl(String shortUrl) {
        for (Map.Entry<String,String> entry : shortFind.entrySet()) {
            if (shortUrl.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Integer getHitCount(String longUrl) {
        if (countHit.containsKey(longUrl)) {
            return countHit.get(longUrl);
        }
        return 0;
    }

    public String delete(String longUrl) {
        for(Map.Entry<String,String> entry : shortFind.entrySet()) {
            if (longUrl.equals(entry.getValue())) {
                return shortFind.remove(entry.getKey());
            }
        }
        return null;
    }

}