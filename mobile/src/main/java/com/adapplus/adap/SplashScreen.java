package com.adapplus.adap;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    //amount of time splash screen is on
    public static int SPLASH_TIME_OUT = 5000;
    //variable for requesting bluetooth
    public static int REQUEST_BLUETOOTH = 1;
    //enable bluetooth method
    private BluetoothAdapter Bluestatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set bluetooth method to phone's bluetooth adapter
        Bluestatus = BluetoothAdapter.getDefaultAdapter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //when splash screen time is done, go to main menu
                Intent homeIntent = new Intent(SplashScreen.this, MainMenu.class);
                startActivity(homeIntent);
                finish();
                //check if bluetooth is not on
                if (!Bluestatus.isEnabled()){
                    //if not on, request user to turn on bluetooth with dialog box
                    Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    //enable bluetooth if user presses yes
                    startActivityForResult(enableBT, REQUEST_BLUETOOTH);
                }
        //splash screen finishes
            }
        },SPLASH_TIME_OUT);




    }
}
