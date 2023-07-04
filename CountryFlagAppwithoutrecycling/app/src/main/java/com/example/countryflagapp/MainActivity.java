package com.example.countryflagapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView countryListView;
    ArrayList<Country> countries = new ArrayList();

    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateTestData();

        countryListView = findViewById(R.id.countryListView);
        adapter = new CountryAdapter(this, countries);
        countryListView.setAdapter(adapter);
    }


    private void populateTestData() {
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));
        countries.add(new Country(101, "India", "in"));
        countries.add(new Country(101, "Malaysia", "my"));
        countries.add(new Country(101, "USA", "us"));
        countries.add(new Country(101, "Singapore", "sg"));
        countries.add(new Country(101, "Indonesia", "id"));
        countries.add(new Country(101, "France", "fr"));
        countries.add(new Country(101, "Indonesia", "it"));

    }
}