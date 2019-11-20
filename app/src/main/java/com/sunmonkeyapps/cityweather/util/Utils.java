package com.sunmonkeyapps.cityweather.util;

public class Utils {

    public static String formatDateWithTemp(String dtTxt, double temp) {
        return (dtTxt + "   Temp: " + String.valueOf(temp) + "° F");
    }

    public static String formatTempLowAndHigh(double tempMin, double tempMax) {
        return ( "Low: " + String.valueOf(tempMin) + "° F" + " | "
                + "High: " + String.valueOf(tempMax) + "° F");
    }

    public static String formathumidity(double humidity) {
        return (   " Humidity: " +   String.valueOf(humidity) );
    }
}

