package com.example.ajinkya.magafoods;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.lang.Void;

import org.json.*;

import java.util.Collections;
import java.util.ArrayList;

import android.view.Window;
import android.view.View;
import android.widget.Toast;


public class MagaActivity extends Activity {

    /**
     * used to pass lists between intents
     */
    public final static String data = "data";

    private ArrayList<Business> maga;

    private double lat;
    private double lon;

    private Intent execute;
    private final String[] searchCuisines = {"thai", "indian", "chinese", "mexican", "african", "malaysian", "pakistani", "spanish", "cuban", "arab", "italian"};
    private final String[] searchFoods = {"burritos", "noodles", "waffles", "ice cream", "cake", "hot dog", "pizza", "pasta", "naan", "fish", "pie", "fajitas", "cornbread"};

    private Button chooseButton;
    private Button cuisineButton;
    private Button foodButton;
    private EditText entryForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maga);

        Intent i = getIntent();
        lat = i.getDoubleExtra(MainActivity.latt, 0.0);
        lon = i.getDoubleExtra(MainActivity.lonn, 0.0);

        chooseButton = (Button)findViewById(R.id.chooseButton);
        cuisineButton = (Button)findViewById(R.id.randomCuisines);
        foodButton = (Button)findViewById(R.id.randomFoods);
        entryForm = (EditText)findViewById(R.id.entryForm);


        chooseButton.setVisibility(View.VISIBLE);
        cuisineButton.setVisibility(View.VISIBLE);
        foodButton.setVisibility(View.VISIBLE);
    }

    /**
     * calls Yelp using an asynchronous task
     * @param searchTerm foodTerm to search for.
     */
    public void yelpIt(final String searchTerm) {
        execute = new Intent(this, MapsActivity.class);
        setProgressBarIndeterminateVisibility(true);
        new AsyncTask<Void, Void, List<Business>>() {

            protected List<Business> doInBackground(Void... params) {
                Yelper maga = new Yelper(getString(R.string.consumerkey), getString(R.string.consumersecret), getString(R.string.token), getString(R.string.tokensecret));
                String businesses = maga.search(searchTerm, lat, lon);
                List<Business> tah = Collections.emptyList();
                try {
                    tah = processJSON(businesses);
                } catch (JSONException e) {
                    return Collections.emptyList();
                }

                return tah;
            }


            protected void onPostExecute(List<Business> businesses) {
                setProgressBarIndeterminateVisibility(false);
                maga = (ArrayList) businesses;
                execute.putExtra(MainActivity.latt, lat);
                execute.putExtra(MainActivity.lonn, lon);
                execute.putParcelableArrayListExtra(data, maga);
                startActivity(execute);
            }
        }.execute();


    }

    /**
     * processes JSON String
     * @param json String
     * @return List of Businesses
     * @throws JSONException
     */
    List<Business> processJSON(String json) throws JSONException {
        JSONObject j = new JSONObject(json);
        JSONArray buss = j.getJSONArray("businesses");
        ArrayList<Business> busObjList = new ArrayList<Business>(buss.length());
        for (int i = 0; i < buss.length(); i++) {
            JSONObject busin = buss.getJSONObject(i);
            busObjList.add(new Business(busin.optString("name"), busin.optString("image_url"), busin.getJSONObject("location").getJSONObject("coordinate").getString("latitude"), busin.getJSONObject("location").getJSONObject("coordinate").getString("longitude")));
        }
        return busObjList;
    }


    /**
     * Button Event for selecting random cuisines
     * @param v View
     */
    public void onRandomCuisines(View v) {
        String searchTerm = searchCuisines[(int)(Math.random()*searchCuisines.length)];

        chooseButton.setVisibility(View.INVISIBLE);
        foodButton.setVisibility(View.INVISIBLE);
        entryForm.setText(searchTerm);

        Toast toast = Toast.makeText(getApplicationContext(), "Searching for: " + searchTerm, Toast.LENGTH_SHORT);
        toast.show();

        yelpIt(searchTerm);
    }

    /**
     * Button Event for selecting random food
     * @param v View
     */
    public void onRandomFood(View v) {
        String searchTerm = searchFoods[(int)(Math.random()*searchFoods.length)];

        cuisineButton.setVisibility(View.INVISIBLE);
        chooseButton.setVisibility(View.INVISIBLE);
        entryForm.setText(searchTerm);

        Toast toast = Toast.makeText(getApplicationContext(), "Searching for: " + searchTerm, Toast.LENGTH_SHORT);
        toast.show();

        yelpIt(searchTerm);
    }

    /**
     * Button Event for choosing your food
     * @param v View
     */
    public void onChooseFood(View v) {
        if (chooseButton.getText().equals(getString(R.string.Choose)))
        {
            entryForm.setFocusable(true);
            entryForm.setFocusableInTouchMode(true);
            entryForm.setText("");

            chooseButton.setText(getString(R.string.search));

            cuisineButton.setVisibility(View.INVISIBLE);
            foodButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            String searchTerm = entryForm.getText().toString();
            if (searchTerm.length() == 0)
            {
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.redo), Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Searching for: " + searchTerm, Toast.LENGTH_SHORT);
                toast.show();

                yelpIt(searchTerm);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maga, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}