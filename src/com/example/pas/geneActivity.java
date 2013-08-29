package com.example.pas;

import java.util.Random;
//import org.apache.commons.*;
import java.security.SecureRandom;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class GeneActivity extends Activity {
	private TextView textView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gene);

		textView = (TextView) findViewById(R.id.textView);
	}

	public void gene(View view){
		Random rnd = new Random();

		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int keyNum = pref.getInt("keyword_num", 0); // キーワードの数を取得
		if (keyNum == 0) {// キーワードがまだ登録されてなかったら
			new AlertDialog.Builder(this)
			.setTitle("タイトル")
			.setMessage("キーワードが１つもありません")
			.setPositiveButton("OK", null)
			.create()
			.show();
			return;
		}

		String keyword = "";
		while (keyword.equals("")) {
			keyword = pref.getString("keyword"+rnd.nextInt(keyNum), ""); // キーワードをランダムに取得
		}
		
		// キーワード＋４けたの乱数
		String password = keyword + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10);

		int newNumber = 0;// キーワード番号
		while (pref.contains("password"+newNumber)) { // 既に存在してたらキーワード番号をずらす
			newNumber++;
		}

		// 保存
		Editor editor = pref.edit();
		editor.putString("password"+newNumber, password);
		int passNum = pref.getInt("password_num", 0);
		editor.putInt("password_num", passNum+1);
		editor.commit();

		textView.setText(password);
	}
    public void gene2(){
        int kazu = 0;
        Random rnd2 = new Random();
        int password;


        switch (kazu){
            case 4:
                password= rnd2.nextInt(10)+ rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10);

                break;
            case 8:
                password=rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10) +rnd2.nextInt(10) + rnd2.nextInt(10);
            break;
            case 12:
                password=rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10) + rnd2.nextInt(10);
            break;
            case 17:
               password= rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10)+rnd2.nextInt(10) + rnd2.nextInt(10);
                break;
        }

        }

    public static String getRandomString(int cnt) {
       //String s=RandomStringUtils.randomAlphabetic(10);
        //cntの数の文字数の数を生成
        final String chars ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd=new Random();
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<cnt;i++){
            int val=rnd.nextInt(chars.length());
            buf.append(chars.charAt(val));
        }
        return buf.toString();
    }
    }






