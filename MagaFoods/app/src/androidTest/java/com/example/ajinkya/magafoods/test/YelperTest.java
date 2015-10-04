package com.example.ajinkya.magafoods.test;

import com.example.ajinkya.magafoods.*;
import junit.framework.TestCase;

/**
 * Created by az on 5/27/15. Isolated testing of the Yelper class
 */
public class YelperTest extends TestCase {

    private Yelper yelper;

    /**
     * tests the constructor
     */
    public void testConstructor() {
        String consumerKey = "dNl7EGgoB_pjDRKixQywbw";
        String consumerSecret = "ZwO8YpCE97xdGOuifEL40rezX8A";
        String token = "gW9cP5kVp3NUWhtv5cXouoiBWF8rURdD";
        String tokenSecret = "sjAKHpwr5CsX2Op58sNV6qKpz1c";

        yelper = new Yelper(consumerKey, consumerSecret, token, tokenSecret);

        assertNotNull(yelper);
    }

    /**
     * tests the search() method with GPS coordinates as parameters
     */
    public void testSearchCoord() {
        String consumerKey = "dNl7EGgoB_pjDRKixQywbw";
        String consumerSecret = "ZwO8YpCE97xdGOuifEL40rezX8A";
        String token = "gW9cP5kVp3NUWhtv5cXouoiBWF8rURdD";
        String tokenSecret = "sjAKHpwr5CsX2Op58sNV6qKpz1c";

        yelper = new Yelper(consumerKey, consumerSecret, token, tokenSecret);

        String ret = yelper.search("burritos", 37.299703, -122.003469);

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

        yelper = new Yelper(consumerKey, consumerSecret, token, tokenSecret);

        String ret = yelper.search("burritos",
                "1280 Johnson Avenue, San Jose CA 95129");

        assertTrue(ret.contains("region"));
    }
}