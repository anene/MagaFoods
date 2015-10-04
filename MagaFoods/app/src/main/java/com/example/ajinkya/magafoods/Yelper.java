package com.example.ajinkya.magafoods;

/**
 * Created by ajinkya on 5/20/15.
 */
public class Yelper {

    private Yelp yelper;

    /**
     * constructs yelper object
     * @param consumerKey String
     * @param consumerSecret String
     * @param token String
     * @param tokenSecret String
     */
    public Yelper(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        yelper = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
    }

    /**
     * searches using certain params
     * @param term String
     * @param latitude double
     * @param longitude double
     * @return String JSON
     */
    public String search(String term, double latitude, double longitude) {
        return yelper.search(term, latitude, longitude);
    }

    /**
     * Searches using location
     * @param term String
     * @param location String
     * @return String JSON
     */
    public String search(String term, String location) {
        return yelper.search(term, location);
    }


}
