package com.sunmonkeyapps.cityweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sunmonkeyapps.cityweather.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String CITY_NAME = "city_name";

    private EditText tvCityName;

    private String mCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCityName =  findViewById(R.id.tvCityName);
        if (savedInstanceState != null) {
            tvCityName.setText(mCityName);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (isCityNameEntered()) {
            outState.putString(CITY_NAME, mCityName);
        } else {
            outState.putString(CITY_NAME, "");
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mCityName = savedInstanceState.getString(CITY_NAME);


    }

    public void retrieveCityWeather(View view) {

        if (isCityNameEntered()) {
            Intent intent = new Intent(this, RetrieveWeatherActivity.class);
            intent.putExtra(CITY_NAME, mCityName);
            startActivity(intent);

        }

    }
    private boolean isCityNameEntered() {

        mCityName = tvCityName.getText().toString().trim();

        if (mCityName.isEmpty()) {
            tvCityName.setError("Please Enter a city name");
            return false;
        } else {
            tvCityName.setError(null);
            return true;
        }
    }
}
