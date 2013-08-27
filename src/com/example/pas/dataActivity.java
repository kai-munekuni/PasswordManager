package com.example.pas;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class dataActivity extends Activity {

	String[] passwords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);

		// 設定保存用クラス
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int passNum = pref.getInt("password_num", 0); // 現在のパスワード数
		passwords = new String[passNum]; // パスワード数分の配列を作成
		
		// パスワードを配列に格納
		for (int i = 0; i < passNum; i++) {
			passwords[i] = pref.getString("password"+i, "No Password"); 
		}

		// 画面のリストに表示する
		ListView listView = (ListView) findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, passwords);
		listView.setAdapter(adapter);
	}

}
