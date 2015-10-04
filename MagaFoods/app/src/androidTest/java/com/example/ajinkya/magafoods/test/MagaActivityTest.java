package com.example.ajinkya.magafoods.test;

import com.example.ajinkya.magafoods.*;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ImageView;
import android.test.*;
import android.view.View;
import com.example.ajinkya.magafoods.R;
import android.widget.Button;

/**
 * Created by az on 5/28/15. Extends ActivityInstrumentationTestCase2 in order
 * to test within the context of the Android system
 */
public class MagaActivityTest extends
		ActivityInstrumentationTestCase2<MagaActivity> {

	private MagaActivity maga;
	private View magalayout;
	private EditText entryForm;
	private Button rancuisines;
	private Button ranfoods;
	private Button choose;
	private ImageView imgview;

	/**
	 * calls the superclass constructor to construct the instrumentation test
	 */
	public MagaActivityTest() {
		super(MagaActivity.class);
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
		maga = getActivity();
		magalayout = (View) maga.findViewById(R.id.magaactivity);
		entryForm = (EditText) maga.findViewById(R.id.entryForm);
		rancuisines = (Button) maga.findViewById(R.id.randomCuisines);
		ranfoods = (Button) maga.findViewById(R.id.randomFoods);
		choose = (Button) maga.findViewById(R.id.chooseButton);
		imgview = (ImageView) maga.findViewById(R.id.imageView);
	}

	/**
	 * testing for initial conditions set by onCreate()
	 */
	public void testPreconditions() {
		assertNotNull(maga);
		assertNotNull(magalayout);
		assertNotNull(entryForm);
		assertNotNull(rancuisines);
		assertNotNull(imgview);

		assertEquals("Random Cuisines", rancuisines.getText().toString());
		assertEquals("Random Foods", ranfoods.getText().toString());
		assertEquals("My Choice", choose.getText().toString());

		assertTrue(View.VISIBLE == magalayout.getVisibility());
		assertTrue(EditText.VISIBLE == entryForm.getVisibility());
		assertTrue(Button.VISIBLE == rancuisines.getVisibility());
		assertTrue(ImageView.VISIBLE == imgview.getVisibility());
		assertTrue(ImageView.VISIBLE == imgview.getVisibility());

		assertFalse(entryForm.isFocusable());
	}

	/**
	 * tests the onRandomCuisines() method for proper displaying of buttons
	 */
	public void testOnRandomCuisines() {
		maga.onRandomCuisines(magalayout);

		assertTrue(Button.INVISIBLE == choose.getVisibility());
		assertTrue(Button.INVISIBLE == ranfoods.getVisibility());
	}

	/**
	 * tests the onRandomFood() method for proper displaying of buttons
	 */
	public void testOnRandomFood() {
		maga.onRandomFood(magalayout);

		assertTrue(Button.INVISIBLE == choose.getVisibility());
		assertTrue(Button.INVISIBLE == rancuisines.getVisibility());
	}

	/**
	 * tests the onChooseFood() method for proper displaying of buttons
	 */
	public void testOnChooseFood() {
		maga.onRandomFood(magalayout);

		assertTrue(entryForm.isFocusable());
		assertTrue(entryForm.isFocusableInTouchMode());
		assertTrue(entryForm.getText().toString().equals(""));
	}
}