package com.example.ajinkya.magafoods.test;

import com.example.ajinkya.magafoods.*;
import junit.framework.TestCase;

/**
 * Created by az on 5/27/15. Isolated testing of the Yelp class
 */
public class YelpTest extends TestCase {

    private Yelp yelp;

    /**
     * tests the constructor
     */
    public void testConstructor() {
        String consumerKey = "dNl7EGgoB_pjDRKixQywbw";
        String consumerSecret = "ZwO8YpCE97xdGOuifEL40rezX8A";
        String token = "gW9cP5kVp3NUWhtv5cXouoiBWF8rURdD";
        String tokenSecret = "sjAKHpwr5CsX2Op58sNV6qKpz1c";

        yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);

        assertNotNull(yelp);
    }

    /**
     * tests the search() method with GPS coordinates as parameters
     */
    public void testSearchCoord() {
        String consumerKey = "dNl7EGgoB_pjDRKixQywbw";
        String consumerSecret = "ZwO8YpCE97xdGOuifEL40rezX8A";
        String token = "gW9cP5kVp3NUWhtv5cXouoiBWF8rURdD";
        String tokenSecret = "sjAKHpwr5CsX2Op58sNV6qKpz1c";

        yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);

        String ret = yelp.search("burritos", 37.299703, -122.003469);

        assertTrue(ret.contains("region"));
    }

    /**
     * tests the search() method with a String location as a parameter
     */
    public void testSearchLoc() {
        String consumerKey = "dNl7EGgoB_pjDRKixQywbw";
        String consumerSecret = "ZwO8YpCE97xdGOuifEL40rezX8A";
        String token = "gW9cP5kVp3NUWhtv5cXouoiBWF8rURdD";
        String tokenSecret = "sjAKHpwr5CsX2Op58sNV6qKpz1c";

        yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);

        String ret = yelp.search("burritos",
                "1280 Johnson Avenue, San Jose CA 95129");

        assertTrue(ret.contains("region"));
    }
}
