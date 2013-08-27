package com.example.pas;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	public void btdata(View view){
		Intent intent = new Intent(MainActivity.this, dataActivity.class);
		startActivity(intent);
	}

	public void btgene(View view){
		Intent intent = new Intent(MainActivity.this, geneActivity.class);
		startActivity(intent);
	}
	public void btother(View view){
		Intent intent = new Intent(MainActivity.this, otheActivity.class);
		startActivity(intent);
	}
	
	public void btnyu(View view){
		Intent intent = new Intent(MainActivity.this, nyuryokActivity.class);
		startActivity(intent);
	}
	
	public void bttango(View view){
		Intent intent = new Intent(MainActivity.this, tangoActivity.class);
		startActivity(intent);
	}
	public void btnew(View view){
		Intent intent = new Intent(MainActivity.this, NewdataActivity.class);
		startActivity(intent);
	}
	public void chenge(View view){
		Intent intent = new Intent(MainActivity.this, ConfActivity.class);
		startActivity(intent);
	}
}



