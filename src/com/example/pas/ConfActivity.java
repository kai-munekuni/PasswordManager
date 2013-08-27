package com.example.pas;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

	public class ConfActivity extends Activity{
		
		SharedPreferences pref ;
		
		EditText editOld,editNew;
		
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_conf);
	        
	        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	        
	        editOld= (EditText)findViewById(R.id.editText_old);
	        editNew= (EditText)findViewById(R.id.editText_new);
	}
		
		public void enter(View v){
			
			String pass = editOld.getText().toString();
			String newpass = editNew.getText().toString();
			if (pass.equals(pref.getString("master", ""))){
				Editor editor = pref.edit();
		   		editor.putString("master",newpass);
		   		editor.commit();
			}
			else{
				//違いますみたいなのを表示
			}
		}
}
