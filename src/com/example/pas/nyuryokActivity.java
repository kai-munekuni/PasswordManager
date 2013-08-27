package com.example.pas;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class nyuryokActivity extends Activity {
	EditText editPs, editTitle;
	DatePicker date;
	TextView tv, tv2;
	String kigou = new String();
	String saiyo = new String();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nyuryok);
		tv = (TextView) findViewById(R.id.tv_display);
		tv2 = (TextView) findViewById(R.id.textname);
		date = (DatePicker) findViewById(R.id.datepicker_birthday);
		editPs = (EditText) findViewById(R.id.edittext_nickname);
		editTitle = (EditText) findViewById(R.id.edittext_title);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_regist:
			StringBuilder builder = new StringBuilder();

			builder.append(date.getYear() + "年 ")
					.append((date.getMonth() + 1) + "月 ")
					.append(date.getDayOfMonth() + "日 ");
			int day = date.getDayOfMonth();
			int month = date.getMonth();
			month = month + 1;
			// TextViewの更新
			tv.setText(builder.toString());
			Random rnd = new Random();

			int ran = rnd.nextInt(10);
			switch (ran) {
			case 0:
				kigou = "!";
				break;
			case 1:
				kigou = "#";
				break;
			case 2:
				kigou = "$";
				break;
			case 3:
				kigou = "%";
				break;
			case 4:
				kigou = "&";
				break;
			case 5:
				kigou = "(";
				break;
			case 6:
				kigou = ")";
				break;
			case 7:
				kigou = "+";
				break;
			case 8:
				kigou = "@";
				break;
			case 9:
				kigou = "?";
				break;
			}
			EditText editText = (EditText) findViewById(R.id.edittext_nickname);
			String text = editText.getText().toString();
			tv2.setText(String.format("名前" + text));
			text = text.substring(0, 1).toUpperCase()
					+ text.substring(1).toLowerCase();
			tv2.setText(String.format("名前" + text));
			tv2.setText(String.format(kigou + month + text + day));

			saiyo = (kigou + month + text + day);
			tv2.setText(String.format(saiyo));

			break;
		case R.id.button1:
			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			SharedPreferences title = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			EditText editText1 = (EditText) findViewById(R.id.edittext_title);
			String title1 = editText1.getText().toString();
			int newNumber = 0;// キーワード番号
			while (pref.contains("password" + newNumber)) { // 既に存在してたらキーワード番号をずらす
				newNumber++;
			}
			Editor editor = pref.edit();
			editor.putString("password" + newNumber, saiyo);
			int passNum = pref.getInt("password_num", 0);
			editor.putInt("password_num", passNum + 1);
			editor.commit();

			Editor editortitle = title.edit();
			editortitle.putString("title" + newNumber, title1);
			int titleNum = pref.getInt("title_num", 0);
			editortitle.putInt("title_num", titleNum + 1);
			editortitle.commit();

			break;

		case R.id.btango:
			int day1 = date.getDayOfMonth();
			int month1 = date.getMonth();
			month1 = month1 + 1;
			String text1 = editPs.getText().toString();
			SharedPreferences tango = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			int tangoNumber = 0;// キーワード番号
			while (tango.contains("tango" + tangoNumber)) { // 既に存在してたらキーワード番号をずらすh
				tangoNumber++;
			}
			Editor editor1 = tango.edit();
			editor1.putString("tango" + tangoNumber, text1);
			int tangoNum = tango.getInt("tango_num", 0);
			editor1.putInt("tango_num", tangoNum + 1);
			editor1.commit();
			break;

		}

	}

}
