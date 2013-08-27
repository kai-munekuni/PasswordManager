package com.example.pas;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class getActivity extends Activity {
	EditText editPs;
	public static String text;
	public static SharedPreferences sp;
	String a;



	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get);
		editPs = (EditText)findViewById(R.id.editPS);
		sp =PreferenceManager.getDefaultSharedPreferences(this);
		int i = 9;
		while(i >= 0){
			sp.edit().putString("hozon"+i,a).commit();
			i--;
		}
	}

	public void get (View view){
		new AlertDialog.Builder(getActivity.this)
		.setTitle("この単語でよろしいですか?")
		.setPositiveButton(
				"OK", 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {  
						text = editPs.getText().toString();

						/** add : start**/
						SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

						// キーワード番号を探す
						int newNumber = 0;
						while (pref.contains("keyword"+newNumber)) { // 既に存在してたらキーワード番号をずらす
							newNumber++;
						}	
						Editor editor = pref.edit();
						editor.putString("keyword"+newNumber, text);

						// Keyword の数を取得
						int num = pref.getInt("keyword_num", 0);
						editor.putInt("keyword_num", num+1);
						editor.commit();
						/** add : end **/

						Intent intent = new Intent(getActivity.this, MainActivity.class);
						startActivity(intent);
					}
				})
				.setNegativeButton(
						"Cancel", 
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {  
							}
						})
						.show();
	}

}

