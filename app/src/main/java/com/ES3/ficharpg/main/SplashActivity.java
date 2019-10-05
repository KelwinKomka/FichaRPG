package com.ES3.ficharpg.main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ES3.ficharpg.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try{
            DBManager dbManager = new DBManager(getApplicationContext());
            SQLiteDatabase db = dbManager.getWritableDatabase();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Erro: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, SheetPickActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
