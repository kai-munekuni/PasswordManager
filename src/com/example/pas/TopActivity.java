package com.example.pas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class TopActivity extends Activity {

	EditText editPass;

	SharedPreferences pref;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top);

		editPass = (EditText) findViewById(R.id.editText_pas);

		pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
	}

	public void enter(View v) {
		String pass = editPass.getText().toString();
		if (pass.equals(pref.getString("master", ""))) {
			Intent intent = new Intent(TopActivity.this, NewdataActivity.class);
			startActivity(intent);
		}
	}
}
