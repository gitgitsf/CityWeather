package com.sunmonkeyapps.cityweather.model;

import java.util.List;

public class WeatherResponse{
	private City city;
	private int cnt;
	private String cod;
	private String message;
	private List<ListItem> list;

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setCnt(int cnt){
		this.cnt = cnt;
	}

	public int getCnt(){
		return cnt;
	}

	public void setCod(String cod){
		this.cod = cod;
	}

	public String getCod(){
		return cod;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"WeatherResponse{" + 
			"city = '" + city + '\'' + 
			",cnt = '" + cnt + '\'' + 
			",cod = '" + cod + '\'' + 
			",message = '" + message + '\'' + 
			",list = '" + list + '\'' + 
			"}";
		}
}