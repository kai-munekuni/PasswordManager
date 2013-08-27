package com.example.pas;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class tangoActivity extends Activity {

	String[] tangos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tango);

		// 設定保存用クラス
		SharedPreferences tango = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int tangoNum = tango.getInt("tango_num", 0); // 現在のパスワード数
		tangos = new String[tangoNum]; // パスワード数分の配列を作成
		
		// パスワードを配列に格納
		for (int i = 0; i < tangoNum; i++) {
			tangos[i] = tango.getString("tango"+i, "No Password"); 
		}

		// 画面のリストに表示する
		ListView listView = (ListView) findViewById(R.id.tango);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tangos);
		listView.setAdapter(adapter);
		
		

        		
	}
}