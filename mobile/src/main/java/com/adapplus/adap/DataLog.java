package com.adapplus.adap;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static com.adapplus.adap.R.id.text;
import static com.adapplus.adap.R.id.textView4;

public class DataLog extends AppCompatActivity {
    //creating array for storing command data
    ArrayList<String> commands;
    //creating viewing area for command data
    TextView Read;
    //creating button for loading data
    Button dataloader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log);
        //defining the array
        commands = new ArrayList<String>();
        //defining textview
        Read = (TextView) findViewById(textView4);
        //defining loading data button
        dataloader = (Button)findViewById(R.id.loadbut);
    }

    //this code brings the user back to the main menu when the back button is pressed
    public void dattomain(View view){
        Intent homeIntent = new Intent(DataLog.this, MainMenu.class);
        startActivity(homeIntent);
        finish();


    }

    public void loadData(){
        commands.clear();
        //finding log file
        File file = getApplicationContext().getFileStreamPath("Data.txt");
        String lineFromFile;
        //if Data.txt exists, read it line by line and store each line in the commands array
        if (file.exists()){
            try {
                //method to open and read Data.txt
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));

                //proceed the loop as long as next line has characters
                while ((lineFromFile = reader.readLine()) != null){
                    //read the current line the while loop is on
                    String writer = reader.readLine();
                    //add what was read to the commands array
                    commands.add(writer);
                }
                //close reader
                reader.close();
                //initialize the setTextToTextView method
                setTextToTextView();
            }
            //in case the above methods do not work, this outputs an error
            catch (IOException e){

            }
        }

    }

    public void dataload(View view){
        //initialize the loadData method
        loadData();
    }

    public void setTextToTextView(){
        //create an empty string for outputting to textview
        String text = "";
        //for each line of commands, add the contents to text
        for (int i = 0 ; i < commands.size() ; i++){
            //text is now the contents commands with a new line
            text = text + commands.get(i) + "\n";
        }
        //textview now displays the text in text
        Read.setText(text);
    }

    public void email(View view){
        //create string for body of text that contains command data
        String reader=Read.getText().toString();
        //call intent and chooser for later definition
        Intent intent=null, chooser=null;
        //intent is to sent data
        intent=new Intent(Intent.ACTION_SEND);
        //data will be sent via email, or any app that can handle mailto: method
        intent.setData(Uri.parse("mailto:"));
        //create empty string for recipients
        String[] to={};
        //fill recipients with to
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        //fill message subject
        intent.putExtra(Intent.EXTRA_SUBJECT,"Data Log");
        //fill message body with command data
        intent.putExtra(Intent.EXTRA_TEXT,reader);
        //set the type of intent
        intent.setType("message/rfc822");
        //chooser is to set the title of the application option menu
        chooser=Intent.createChooser(intent, "Send Data Log");
        //initialize the chooser
        startActivity(chooser);
    }
}
