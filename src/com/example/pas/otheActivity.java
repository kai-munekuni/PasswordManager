package com.example.pas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class otheActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.othe_activity);
    }
	
	
	
	
	
	
	public void btget(View view){
		Intent intent = new Intent(otheActivity.this, getActivity.class);
    	startActivity(intent);
	}
}
