package com.example.ajinkya.magafoods.test;

import com.example.ajinkya.magafoods.*;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

/**
 * Created by az on 5/26/15. Isolated testing of the Business class
 */
public class BusinessTest extends TestCase {

	private String name;
	private String photoUrl;
	private double latitude;
	private double longitude;
	private Business bus;

	/**
	 * tests the public constructor
	 */
	public void testConstructor() {
		name = "MOD Pizza";
		photoUrl = "http://s3-media2.fl.yelpcdn.com/bphoto/GUP9wSkiKgkIUb47FeM9yQ/o.jpg";
		latitude = 37.292183;
		longitude = -121.994278;
		bus = new Business(name, photoUrl, "" + latitude, "" + longitude);

		assertNotNull(bus);
		assertNotNull(bus.toString());
		assertNotNull(bus.getPhotoUrl());
		assertTrue(bus.getLatitude() == latitude);
		assertTrue(bus.getLongitude() == longitude);
	}

	/**
	 * tests the toString() method
	 */
	public void testToString() {
		name = "MOD Pizza";
		photoUrl = "http://s3-media2.fl.yelpcdn.com/bphoto/GUP9wSkiKgkIUb47FeM9yQ/o.jpg";
		latitude = 37.292183;
		longitude = -121.994278;
		bus = new Business(name, photoUrl, "" + latitude, "" + longitude);

		assertTrue(bus.toString().equals(name));
	}

	/**
	 * tests the getPhotoUrl() method
	 */
	public void testGetPhotoUrl() {
		name = "MOD Pizza";
		photoUrl = "http://s3-media2.fl.yelpcdn.com/bphoto/GUP9wSkiKgkIUb47FeM9yQ/o.jpg";
		latitude = 37.292183;
		longitude = -121.994278;
		bus = new Business(name, photoUrl, "" + latitude, "" + longitude);

		assertTrue(bus.getPhotoUrl().equals(photoUrl));
	}

	/**
	 * tests the describeContents() method
	 */
	public void testDescribeContentst() {
		name = "MOD Pizza";
		photoUrl = "http://s3-media2.fl.yelpcdn.com/bphoto/GUP9wSkiKgkIUb47FeM9yQ/o.jpg";
		latitude = 37.292183;
		longitude = -121.994278;
		bus = new Business(name, photoUrl, "" + latitude, "" + longitude);

		assertEquals(0, bus.describeContents());
	}
}