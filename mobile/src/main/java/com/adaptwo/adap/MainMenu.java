package com.adaptwo.adap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    //this code brings the user to the tutorial section
    public void maintotut(View view){
        Intent homeIntent = new Intent(MainMenu.this, Tutorial.class);
        startActivity(homeIntent);
        finish();
    }
    //this code brings the user to the commands section
    public void maintocom(View view){
        Intent homeIntent = new Intent(MainMenu.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }
    //this code brings the user to the data logging section
    public void maintodata(View view){
        Intent homeIntent = new Intent(MainMenu.this, DataLog.class);
        startActivity(homeIntent);
        finish();
    }
    //this code brings the user to the settings section
    public void maintoset(View view){
        Intent homeIntent = new Intent(MainMenu.this, Settings.class);
        startActivity(homeIntent);
        finish();
    }
}
