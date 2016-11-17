package com.T4Niar00StudentsOamkFi.IbeaconprojectJwv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.T4Niar00StudentsOamkFi.IbeaconprojectJwv.estimote.BeaconID;
import com.T4Niar00StudentsOamkFi.IbeaconprojectJwv.estimote.EstimoteCloudBeaconDetails;
import com.T4Niar00StudentsOamkFi.IbeaconprojectJwv.estimote.EstimoteCloudBeaconDetailsFactory;
import com.T4Niar00StudentsOamkFi.IbeaconprojectJwv.estimote.ProximityContentManager;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.cloud.model.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private String nameField;
    private String priceField;
    private String imageField;

    static ArrayList<String> myList = new ArrayList<String>();

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();

    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final int BACKGROUND_COLOR_NEUTRAL = android.graphics.Color.rgb(160, 169, 172);

    private ProximityContentManager proximityContentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String paivitettyNimi = intent.getStringExtra("Päivitetty tuotteen nimi");
        final String paivitettyHinta = intent.getStringExtra("Päivitetty tuotteen hinta");
        final String paivitettyKuva = intent.getStringExtra("Päivitetty tuotteen kuva");
        //final String paivitettyLista = intent.getStringArrayListExtra("Päivitetty tuotteen lista");
        //myList = intent.getStringArrayListExtra("lista");

        final Button infoButton = (Button) findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("Tuotteen nimi", nameField);
                intent.putExtra("Tuotteen hinta", priceField);
                intent.putExtra("Tuotteen kuva", imageField);
                intent.putStringArrayListExtra("Tuotteen ominaisuudet", myList);
                startActivity(intent);
            }
        });

        final TextView nimi = (TextView) findViewById(R.id.tuoteNimi);
        nimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "TextView Clicked!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 50594, 25995),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 60298, 31914),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 92, 11261)),
                new EstimoteCloudBeaconDetailsFactory());
        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {
                String tuote;
                String price;
                Integer backgroundColor;
                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;
                    TextView tuoteNimi = (TextView) findViewById(R.id.tuoteNimi);
                    tuote = "Tuote:";
                    price = paivitettyHinta;
                    if (beaconDetails.getBeaconName().equals ("lemon")) {
                        if(paivitettyHinta != null || paivitettyNimi != null) {
                            tuoteNimi.setText(paivitettyNimi);
                            nameField = tuoteNimi.getText().toString();
                            priceField = price;
                        }
                        else {
                            tuoteNimi.setText("");
                            nameField = tuoteNimi.getText().toString();
                            price = "1.00€";
                            priceField = price;
                        }
                    }
                    else if (beaconDetails.getBeaconName().equals ("candy")) {
                        if(paivitettyHinta != null || paivitettyNimi != null) {
                            tuote = "Tuote: #2";
                            tuoteNimi.setText(paivitettyNimi);
                            nameField = tuoteNimi.getText().toString();
                            priceField = price;
                        }
                        else {
                            tuoteNimi.setText("");
                            nameField = tuoteNimi.getText().toString();
                            price = "2.00€";
                            priceField = price;
                        }
                    }
                    else if (beaconDetails.getBeaconName().equals ("beetroot")) {
                        if(paivitettyHinta != null || paivitettyNimi != null) {
                            tuote = "Tuote: #3";
                            tuoteNimi.setText(paivitettyNimi);
                            nameField = tuoteNimi.getText().toString();
                            priceField = price;
                        }
                        else {
                            tuoteNimi.setText("");
                            nameField = tuoteNimi.getText().toString();
                            price = "3.00€";
                            priceField = price;
                        }
                    }
                } else {
                    TextView tv = (TextView) findViewById(R.id.tuoteNimi);
                    tuote = "No beacons in range.";
                    tv.setText(tuote);
                    price = "###";
                }
                ((TextView) findViewById(R.id.tuoteNimi)).setText(tuote);
                TextView tv = (TextView) findViewById(R.id.tuoteNimi);
                ((TextView) findViewById(R.id.hinta)).setText(price);
            }
        });
    }
    @Override
    public void onClick(View v)
    {

    }

    public void settingsButtonClicked(View view) {

        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else {
            Log.d(TAG, "Starting ProximityContentManager content updates");
            proximityContentManager.startContentUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Stopping ProximityContentManager content updates");
        proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        proximityContentManager.destroy();
    }

}