package com.example.pas;



import java.util.ArrayList;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.*;

public class NewdataActivity extends Activity {



    private ListView list;
    ArrayList<Entry> array;
    EntryAdapter adapter;

    Button btremove,btedit,btcopy;

    SharedPreferences pref;

    String currentKey;



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

        btremove = (Button)findViewById(R.id.buttondelete);
        btedit = (Button)findViewById(R.id.buttonedit);
        btcopy = (Button)findViewById(R.id.buttoncopy);


        // 設定保存用クラス
        pref = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        int passNum = pref.getInt("password_num", 0); // 現在のパスワード数
        array = new ArrayList<Entry>(); // パfスワード数分の配列を作成



        // パスワードを配列に格納
        for (int i = 0; i < passNum; i ++) {
            Entry entry = Entry.load(this,i);
            array.add(entry);

        }

        // 画面のリストに表示する
        adapter=new EntryAdapter(this,R.layout.row,array);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                toggleBt();
            }
        });
        list.setAdapter(adapter);


    }

    private void showButtons() {
        btedit.setVisibility(View.VISIBLE);
        btremove.setVisibility(View.VISIBLE);
        btcopy.setVisibility(View.VISIBLE);
    }

    private void hideButtons() {
        btedit.setVisibility(View.INVISIBLE);
        btremove.setVisibility(View.INVISIBLE);
        btcopy.setVisibility(View.INVISIBLE);
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

    public void add(Entry entry) {
        array.add(entry);
        pref.edit().putInt("password_num", array.size()).commit();
    }

    public void alldelete(){
        //全消去
        // Editor editor = pref.edit();

        //editor.clear();
        //editor.commit();
    }
    //栄光の個別削除
    public void delete(View view) {
        Entry entry = getSelected();
        if(entry == null) {
            return;
        }
        array.remove(entry);
        adapter.notifyDataSetChanged();
        entry.remove(this);
        pref.edit().putInt("password_num", array.size()).commit();
    }
    private void toggleBt (){
        if(getSelected()==null){
            hideButtons();

        }
        else{
            showButtons();
        }

    }
    private Entry getSelected(){
        for(int i=0; i<array.size();i++){
            if(array.get(i).isSelected()){
                return array.get(i);

            }

        }
        return null;

    }

    public void removeItem() {



    }


    public void plus(View view) {
        Entry entry= new Entry(array.size());
        EntryCreationDialog dialog=new EntryCreationDialog(this,"addentry" ,entry );
        dialog.setOnDismissListener(new DialogDissmissListener(entry, true));
        dialog.show();
    }

    public void onLongClick(View v){}
    public void copy (){
        ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        //cm.setText(longET.getText());


    }
    private class DialogDissmissListener implements DialogInterface.OnDismissListener{
        private Entry entry;
        private boolean isNew;
        public DialogDissmissListener(Entry entry,boolean isNew){
            this.entry=entry;
            this.isNew=isNew;
        }

        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            if(isNew){
                add(entry);

            }
            adapter.notifyDataSetChanged();

        }
    }
}