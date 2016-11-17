package com.T4Niar00StudentsOamkFi.IbeaconprojectJwv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private String paivitettyNimi;
    private String paivitettyHinta;
    private String paivitettyKuva;

    private ListView listView;

    ArrayList<String> myList = new ArrayList<String>();
    String[] uusiarray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupActionBar();

        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(aa);

        Intent intent = getIntent();                                    //HAE MAINACTIVITY MUUTTUJAT
        String nimi = intent.getStringExtra("Tuotteen nimi");
        String hinta = intent.getStringExtra("Tuotteen hinta");
        String kuva = intent.getStringExtra("Tuotteen kuva");


    }

    public void saveButtonClicked(View view) {                          //SAVE BUTTON FUNCTIONALITY

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Päivitetty tuotteen nimi", paivitettyNimi);
        intent.putExtra("Päivitetty tuotteen hinta", paivitettyHinta);
        intent.putExtra("Päivitetty tuotteen kuva", paivitettyKuva);
        startActivity(intent);
    }

    public void addButtonClicked(View view) {                           //ADD BUTTON FUNCTIONALITY

        String infoToAdd;

        listView = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);

        EditText editText = (EditText) findViewById(R.id.addProperty);
        infoToAdd = editText.getText().toString();
            myList.add(infoToAdd);
            listView.setAdapter(aa);
        editText.setText("");
    }

    /*############################################################################################*/

    private void setupActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void hintaClicked(View view) {

        String infoToAdd;

        TextView hintaTeksti = (TextView) findViewById(R.id.hinta);
        EditText editText = (EditText) findViewById(R.id.addProperty);
        infoToAdd = editText.getText().toString();
        hintaTeksti.setText("Hinta: " + infoToAdd);
        editText.setText("");
    }

    public void tuoteClicked(View view) {

        String infoToAdd;

        TextView tuoteNimi = (TextView) findViewById(R.id.tuoteNimi);
        EditText editText = (EditText) findViewById(R.id.addProperty);
        infoToAdd = editText.getText().toString();
        tuoteNimi.setText(infoToAdd);
        editText.setText("");
    }

    public void removeButtonClicked(View view) {

        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(aa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = myList.get(position);
                myList.remove(selectedItem);
                listView.setAdapter(aa);
            }
        });
        };
        }



