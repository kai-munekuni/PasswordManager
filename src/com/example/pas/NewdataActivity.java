package com.example.pas;

//import com.example.golf.topActivity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class NewdataActivity extends Activity {



    private ListView list;
    ArrayList<Entry> array;
    EntryAdapter adapter;

    Button btn;

    EditText longET;
    EditText longEF;
    EditText plusET;
    EditText plusEF;

    AlertDialog longClickAD = null;
    AlertDialog serviceNameDialog = null;
    AlertDialog passwordDialog = null;

    SharedPreferences pref;

    String currentKey;
    int currentId;
    int offset=0;
    int num;

    private String currentServiceName;

    // 今後やりたいこと
    // 生成方法の変更
    // 単語のパスワードと同じような管理
    // パスワードの削除、単語も同じように
    // パスワードをタッチしたときに更に詳細なデータの表示
    // newdataactivityでのパスワードの変更、作成
    // パスワードと情報を入力する画面を分割し
    // 単語を入力した後は
    // ワンクリックで生成出来るようにする
    // 生成の条件を決められるようにする、　例：数字だけ、英語だけ



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdata);
        list=(ListView)findViewById(R.id.list);

        btn = (Button)findViewById(R.id.buttondelete);
        // btn.setOnLongClickListener(View v);

        longET = new EditText(this);
        longET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        longEF = new EditText(this);
        longEF.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        plusET = new EditText(this);
        plusET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        plusEF = new EditText(this);
        plusEF.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);


        // 設定保存用クラス
        pref = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        int passNum = pref.getInt("password_num", 0); // 現在のパスワード数
        array = new ArrayList<Entry>(); // パfスワード数分の配列を作成



        // パスワードを配列に格納
        for (int i = 1; i <= passNum; i ++) {
            Entry entry = Entry.load(this,i);
            array.add(entry);

        }

        initializeDialogs();

        // 画面のリストに表示する
        adapter=new EntryAdapter(this,R.layout.row,array);

        list.setAdapter(adapter);

//
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           final int position, long id) {
//                if (offset == 1) {
//                    //削除モード
//                    int values;
//
//                    if (position % 2 == 1){
//                        new AlertDialog.Builder(NewdataActivity.this)
//                                .setTitle("削除してよろしい？")
//                                .setPositiveButton("許可",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                                num=position/2+1;
//                                                pref.edit().remove("password"+String.valueOf(num)).commit();
//                                                pref.edit().remove("title"+String.valueOf(num)).commit();
//
//                                            }
//                                        })
//                                .setNegativeButton("拒否",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                            }
//                                        }).create().show();
//                        //num=position/2+1;
////                        SharedPreferences.Editor remove (String password+"position")
////                        SharedPreferences.Editor remove (String title+"position")
//                        //pref.edit().remove("password"+String.valueOf(num)).commit();
//                        //pref.edit().remove("title"+String.valueOf(num)).commit();
//
//
//                        //削除
//                    } else {
//                        num = position/2;
//                        pref.edit().remove("password"+String.valueOf(num)).commit();
//                        pref.edit().remove("title"+String.valueOf(num)).commit();
//                        adapter.notifyDataSetChanged();
////                        SharedPreferences.Editor remove (String password+"position")
////                        SharedPreferences.Editor remove (String title+"position")
//
//                    }
//
//
//                } else if(offset==0) {
//                    //通常モーード
//
//                    if (position % 2 == 0) {
//                        currentKey = "title" + (position / 2);
//                    } else {
//                        currentKey = "password" + (position / 2);
//                    }
//                    currentId = position;
//                    longET.setText(pref.getString(currentKey, "No Password"));
//
//                    if (longClickAD == null) {
//                        longClickAD = new AlertDialog.Builder(NewdataActivity.this)
//                                .setTitle("何をしますか")
//                                .setView(longET)
//                                .setPositiveButton("再生成",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//
//                                            }
//                                        })
//
//                                .setNeutralButton("変更",
//                                        new DialogInterface.OnClickListener() {
//
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                                String newPassword = longET.getText()
//                                                        .toString();
//                                                editPref(newPassword);
//                                            }
//                                        })
//                                .setNegativeButton("コピー",
//                                        new DialogInterface.OnClickListener() {
//
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                                ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//
//                                                cm.setText(longET.getText());
//
//                                            }
//                                        }).create();
//                    }
//                }
//                longClickAD.show();
//
//                return false;
//            }
//        });
    }

    private void initializeDialogs() {
        serviceNameDialog = new AlertDialog.Builder(NewdataActivity.this)
                .setTitle("サービス名を入力してください")
                .setView(plusET)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // 処理を書く
                                currentServiceName = plusET.getText().toString();
                                passwordDialog.show();
                            }
                        }).create();

        passwordDialog = new AlertDialog.Builder(NewdataActivity.this)
                .setTitle("パスワードを入力してください")
                .setView(longEF)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // 処理を書く
                                String password = longEF.getText().toString();
                                add(currentServiceName, password);
                            }

                        }).create();
    }


    private void editPref(String s) {
        pref.edit().putString(currentKey, s).commit();
        //array.set(currentId, s);
        adapter.notifyDataSetChanged();
    }

    public void set(View view) {
        Intent intent = new Intent(NewdataActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void add(String serviceName, String password) {
        Entry entry = new Entry(array.size()+1,serviceName,password);
        array.add(entry);
        entry.save(this);
        adapter.notifyDataSetChanged();
        pref.edit().putInt("password_num", array.size()).commit();
    }

    public void alldelete(){
        //さよならいおん
        // Editor editor = pref.edit();

        //editor.clear();
        //editor.commit();
    }
    //栄光の個別削除
    public void delete(View view) {
        if(offset==0){
            offset=1;
            Toast.makeText(this, "削除もぉぉぉぉぉど", Toast.LENGTH_LONG).show();




        }
        else{
            offset=0;
            Toast.makeText(this, "通常モード", Toast.LENGTH_LONG).show();
        }
    }

    public void removeItem() {



    }

    public void plus(View view) {
        serviceNameDialog.show();
    }

    public void onLongClick(View v){}
}
