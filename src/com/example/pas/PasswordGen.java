package com.example.pas;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PasswordGen {

	public static String generate(Context context, String serviceName) {
		
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context) ;
		
		String password = serviceName ;
		
		return password ;
	}
}
