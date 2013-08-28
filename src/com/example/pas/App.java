package com.example.pas;

import android.app.Application;
import android.content.Context;

/**
 * Created by munekunikai on 13/08/27.
 */
public class App extends Application {
    private static Context context;

    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;

    }
}
