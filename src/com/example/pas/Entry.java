package com.example.pas;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
/**
 * Created by munekunikai on 13/08/27.
 */
public class Entry {


    private boolean selected;
    private int id;
    private String serviceName;
    private String password;
    public Entry(int id){
        this(id,"","");
    }

    public Entry(int id, String serviceName, String password) {
        this.id = id;
        this.serviceName = serviceName;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void save(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("title" + getId(), getServiceName());
        editor.putString("password" + getId(), EncryptionHelper.encrypt(getPassword(), R.raw.id_rsa_pub, ""));
        editor.commit();
    }

    public static Entry load(Context context, int id) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String title = pref.getString("title" + id, "no title");
        String password = EncryptionHelper.decrypt(pref.getString("password" + id, "no password"), R.raw.id_rsa, "");
        return new Entry(id, title, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (id != entry.id) return false;
        if (password != null ? !password.equals(entry.password) : entry.password != null) return false;
        if (serviceName != null ? !serviceName.equals(entry.serviceName) : entry.serviceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
    public void remove(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().remove("password"+id).commit();
        pref.edit().remove("title"+id).commit();


    }
}










