package com.example.ajinkya.magafoods.test;

import com.example.ajinkya.magafoods.*;
import android.graphics.Rect;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by az on 5/29/15. Extends ActivityInstrumentationTestCase2 in order
 * to test within the context of the Android system
 */
public class MainActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainact;
    private ImageButton enterApp;
    private View layout;
    private EditText welcometext;

    /**
     * calls the superclass constructor to construct the instrumentation test
     */
    public MainActivityTest() {
        super(MainActivity.class);
    }

    /**
     * set up the test fixture for this class. this method is always called
     * before every test run
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        mainact = getActivity();
        enterApp = (ImageButton) mainact.findViewById(R.id.sendButton);
        layout = (View) mainact.findViewById(R.id.mainlayout);
        welcometext = (EditText) mainact.findViewById(R.id.editText);
    }

    /**
     * testing for initial conditions set by onCreate()
     */
    public void testPreconditions() {
        assertNotNull(mainact);
        assertNotNull(enterApp);
        assertNotNull(layout);
        assertNotNull(welcometext);
        assertTrue(Button.VISIBLE == enterApp.getVisibility());
        assertTrue(View.VISIBLE == layout.getVisibility());
        assertTrue(EditText.VISIBLE == welcometext.getVisibility());
    }

    /**
     * testing the single button displayed on the first screen of the app
     */
    public void testEnterAppButton() {
        int width = layout.getWidth();
        int height = layout.getHeight();
        int[] layloc = new int[2];

        layout.getLocationOnScreen(layloc);

        int[] viewloc = new int[2];

        enterApp.getLocationOnScreen(viewloc);

        Rect rect = new Rect();
        enterApp.getDrawingRect(rect);

        assertTrue("Button off the right of the screen",
                width + layloc[0] > rect.width() + viewloc[0]);

        assertTrue("Button off the bottom of the screen",
                height + layloc[1] > rect.height() + viewloc[1]);
    }

    /**
     * tests if the displayed text, "Welcome to MagaFood" is correct
     */
    public void testWelcomeText() {
        assertEquals("Welcome to MagaFood", welcometext.getText().toString());
    }

    /**
     * testing the change in location of the user; should update the GPS
     * coordinates accordingly
     */
    public void testChangeLoc() {
        Location loc = new Location("1641 Deerfield Drive, San Jose CA 95129");
        mainact.onLocationChanged(loc);

        final double lat = loc.getLatitude();
        final double lon = loc.getLongitude();

        assertEquals(lat, mainact.getLat());
        assertEquals(lon, mainact.getLon());
    }
}
