package com.example.ajinkya.magafoods.test;

import com.example.ajinkya.magafoods.*;
import junit.framework.TestCase;

import org.scribe.model.Token;

/**
 * Created by az on 5/27/15. Isolated testing of the YelpApi2 class
 */
public class YelpApi2Test extends TestCase {

	private YelpApi2 yelp;

	/**
	 * tests the constructor
	 */
	public void testConstructor() {
		yelp = new YelpApi2();

		assertNotNull(yelp);
	}

	/**
	 * tests the getAccessTokenEndpoint() method
	 */
	public void testGetAccessToken() {
		yelp = new YelpApi2();

		assertNull(yelp.getAccessTokenEndpoint());
	}

	/**
	 * tests the getAuthorizationUrl() method
	 */
	public void testGetAuthTest() {
		yelp = new YelpApi2();
		Token tok = new Token("gW9cP5kVp3NUWhtv5cXouoiBWF8rURdD",
				"sjAKHpwr5CsX2Op58sNV6qKpz1c");

		assertNull(yelp.getAuthorizationUrl(tok));
	}

	/**
	 * tests the getRequestTokenEndpoint() method
	 */
	public void testGetRequestTokenEndpoint() {
		yelp = new YelpApi2();

		assertNull(yelp.getRequestTokenEndpoint());
	}
}