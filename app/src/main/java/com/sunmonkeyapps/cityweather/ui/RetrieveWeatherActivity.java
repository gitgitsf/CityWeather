package com.sunmonkeyapps.cityweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunmonkeyapps.cityweather.R;
import com.sunmonkeyapps.cityweather.adapter.WeatherAdapter;
import com.sunmonkeyapps.cityweather.api.WeatherApi;
import com.sunmonkeyapps.cityweather.app.AppConstants;
import com.sunmonkeyapps.cityweather.model.ListItem;
import com.sunmonkeyapps.cityweather.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrieveWeatherActivity extends AppCompatActivity {

    private static final String TAG = "RetrieveWeatherActivity";
    private String mCityName;
    private String mErrorMessage;

    private boolean isLoading=true;
    private boolean isDataFromApi=true;
     RecyclerView rvWeather;
     ProgressBar rvProgressBar;
     TextView tvError;


    List<ListItem> mWeatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_weather);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupView();
        retrieveCityNameFromBundle();

        // change the default android:label to a meaning title
        String title = "Weather for " + mCityName;
        actionBar.setTitle(title);


        getWeatherFromApi();

    }

    private void setupView() {
        rvWeather = (RecyclerView)findViewById(R.id.rvWeather);
        rvProgressBar = (ProgressBar) findViewById(R.id.rvProgressBar);
        tvError = (TextView) findViewById(R.id.tvError);

        rvWeather.setLayoutManager(new LinearLayoutManager(this));
        rvWeather.setItemAnimator(new DefaultItemAnimator());
    }


    private void getWeatherFromApi() {

        Retrofit retrofit = createRetrofitObject();
        final WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        isLoading=true;
        setVisibility();

        Call<WeatherResponse> call = weatherApi.getWeatherForCity(mCityName, AppConstants.MODE, AppConstants.UNITS, AppConstants.appid);


        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {


//                WeatherResponse resp = response.body();
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body() );

                    WeatherResponse resp = response.body();
                    Log.d(TAG, "onResponse: " + resp.getCod());

                    if (resp.getCod().equals(AppConstants.HTTP_CODE_GOOD)) {
                        Log.d(TAG, "onResponse: " + resp.getList());

                        mWeatherList.addAll(resp.getList());

                        isDataFromApi= true;
                        rvWeather.setAdapter(new WeatherAdapter(mWeatherList));
                        Objects.requireNonNull(rvWeather.getAdapter()).notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "onResponse: error message=" + resp.getMessage());
                        isDataFromApi=false;
                        tvError.setText(resp.getMessage());

                    }

                } else {
                    Log.e(TAG, "onResponse - Not successful " + response.body() );
                    tvError.setText(response.message());
                    isDataFromApi=false;
                }

                isLoading=false;
                setVisibility();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage() );
                isLoading= false;
                isDataFromApi=false;
                tvError.setText(t.getMessage());
                setVisibility();
            }
        });

    }

    private Retrofit createRetrofitObject() {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void retrieveCityNameFromBundle() {

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        mCityName = intent.getStringExtra(MainActivity.CITY_NAME);
    }

    private void setVisibility() {
        if (isLoading) {
            rvProgressBar.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.INVISIBLE);
            rvWeather.setVisibility(View.INVISIBLE);

        } else {
            rvProgressBar.setVisibility(View.INVISIBLE);
        }

        if (isDataFromApi) {
            rvWeather.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.INVISIBLE);
        } else {
            rvWeather.setVisibility(View.INVISIBLE);
            tvError.setVisibility(View.VISIBLE);
        }

    }
}
