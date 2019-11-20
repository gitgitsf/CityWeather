package com.sunmonkeyapps.cityweather.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sunmonkeyapps.cityweather.R;
import com.sunmonkeyapps.cityweather.model.ListItem;
import com.sunmonkeyapps.cityweather.model.WeatherItem;
import com.sunmonkeyapps.cityweather.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private static final String TAG = "WeatherAdapter";

    public List<ListItem> mWeatherList;

    public WeatherAdapter(List<ListItem> listItems) {
        mWeatherList = listItems;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return (new WeatherViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");

        ListItem mListItem = mWeatherList.get(position);

        // date & current temp
        holder.tvDtNtemp.setText(Utils.formatDateWithTemp(mListItem.getDtTxt(), mListItem.getMain().getTemp()));

        // weather description - e.g Light rain
        List<WeatherItem> weather = mListItem.getWeather();
        holder.tvDesc.setText(weather.get(0).getDescription());

        // temp low & high
        holder.tvTempLowHigh.setText(Utils.formatTempLowAndHigh(mListItem.getMain().getTempMin(), mListItem.getMain().getTempMax()));

        // humidity
        holder.tvHumidity.setText( Utils.formathumidity( mListItem.getMain().getHumidity()));


    }

    @Override
    public int getItemCount() {
        return (mWeatherList == null ? 0 : mWeatherList.size());
    }


    public static class WeatherViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.tvDtNtemp)
        TextView tvDtNtemp;

        @Nullable @BindView(R.id.tvTempLowHigh)
        TextView tvTempLowHigh;

        @Nullable @BindView(R.id.tvDesc)
        TextView tvDesc;

        @Nullable @BindView(R.id.tvHumidity)
        TextView tvHumidity;


        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
