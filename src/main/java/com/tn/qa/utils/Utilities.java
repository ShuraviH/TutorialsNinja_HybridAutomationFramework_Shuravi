package com.tn.qa.utils;

import java.util.Date;

public class Utilities {

	public static void main(String[] args) {
		generateEmailWithTimeStamp();
	}
	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_").substring(8, 19).replace("_", "");
		return "RuparChand" + timeStamp + "@hotmail.com";
	}

public static final int implitWaitTime = 10;
public static final int pageLoadTime = 10;
public static final int scriptTime = 100;

}
