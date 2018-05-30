package com.adapplus.adap;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    //create positive vibration dropdown
    Spinner viblist;
    //string for vibration pattern choices
    String intensitylist[] = {"Pattern 1","Pattern 2", "Pattern 3"};
    //arrayadapter for putting the string into dropdown
    ArrayAdapter<String> adapterviblist;
    //create negative vibration dropdown
    Spinner viblistneg;
    //arrayadapter for putting the string into dropdown
    ArrayAdapter<String> adapterviblistneg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //define pos vibration dropdown
        viblist = (Spinner) findViewById(R.id.spinner4);
        //define arrayadapter for pos
        adapterviblist = new ArrayAdapter<String>(this,
                //what the dropdown looks like when collapsed
                android.R.layout.simple_spinner_item, intensitylist);
        //what the dropdown looks like when expanded
        adapterviblist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set pos arrayadapter with pos spinner
        viblist.setAdapter(adapterviblist);

        //same as above except for negative vibration
        viblistneg = (Spinner) findViewById(R.id.spinner3);
        adapterviblistneg = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, intensitylist);
        adapterviblistneg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viblistneg.setAdapter(adapterviblistneg);


    }

    public void posmesclick(View view){
        //create a Toast message when save button pressed
        Context context = getApplicationContext();
        //toast message contents
        CharSequence text = "Default Message Saved!";
        //length of toast message
        int duration = Toast.LENGTH_SHORT;
        //actual setup of toast message
        Toast toast = Toast.makeText(context, text, duration);
        //initialize toast message
        toast.show();
    }

    public void negmesclick(View view){
        //see toast message comments in posmesclick method
        Context context = getApplicationContext();
        CharSequence text = "Default Message Saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void posvibclick(View view){
        //see toast message comments in posmesclick method
        Context context = getApplicationContext();
        CharSequence text = "Default Vibration Saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void negvibclick(View view){
        //see toast message comments in posmesclick method
        Context context = getApplicationContext();
        CharSequence text = "Default Vibration Saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //this code brings the user back to the main menu when the back button is pressed
    public void settomain(View view){
        Intent homeIntent = new Intent(Settings.this, MainMenu.class);
        startActivity(homeIntent);
        finish();
    }
}
