package com.example.utils;

import java.text.NumberFormat;

public class NumberUtils {

	private NumberUtils(){
	}
	
	public static String format(Integer price){
		NumberFormat format = NumberFormat.getInstance();
		return format.format(price);
	}
}
