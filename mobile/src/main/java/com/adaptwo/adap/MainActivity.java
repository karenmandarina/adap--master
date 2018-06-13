package com.adaptwo.adap;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //declaring the editText for the custom command
    //These are my change comments. Melissa sucks!!!
    private EditText editText;
    //declaring the Button for custom command
    private Button actionButton;
    //declaring the audiomanager for silencing
    private AudioManager myAudioManager;
    AudioManager mode = null;
    //declaring the arraylist for storing commands
    ArrayList<String> commands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining custom command button
        actionButton = (Button) findViewById(R.id.actionButton);
        //defining editable field for custom commands
        editText = (EditText) findViewById(R.id.editText);
        //listener to see when the editable field is empty or has text. also references buttontextWatcher method
        editText.addTextChangedListener(buttontextWatcher);
        //make custom text button not usable at first
        actionButton.setEnabled(false);
        //silence the smartphone from vibrating when commands are sent
        mode = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //create an array to store command data in
        commands = new ArrayList<String>();
    }

    //method for tracking when there is text in the editable field
    private final TextWatcher buttontextWatcher = new TextWatcher() {
        //store what the state of field is before any text input. can add actions here as well
        public void beforeTextChanged(CharSequence s, int start, int count, int after){

        }
        //store what the state of field is during any text input. can add actions here as well
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        //store what the state of field is after any text input. can add actions here as well
        public void afterTextChanged(Editable s) {
            //if there are no characters in field, custom command button cannot be used
            if (s.length() == 0) {
                actionButton.setEnabled(false);
            }
            //if there are characters in the field, custom command button can be used
            else{
                actionButton.setEnabled(true);
            }
        }


    };

    //this code brings the user back to the main menu when the back button is pressed
    public void comtomain (View view){
        Intent homeIntent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(homeIntent);
        finish();


    }

    public void goodjobnotify(View view) {
        //Notification code for positive
        //make phone silent mode
        mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //building the notification
        Notification notification = new NotificationCompat.Builder(getApplication())
                //Title
                .setContentTitle("ADAP+")
                //Message
                .setContentText("Good Job!")
                //create default background
                .extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true))
                //priority of notification on watch is HIGHEST
                .setPriority(NotificationCompat.PRIORITY_MAX)
                //putting the small icon in notification
                .setSmallIcon(R.drawable.ic_stat_name)
                //putting the main, large icon in the notification
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.smile)) //smiling bitmap image
                //vibration pattern, format is {pause, vibrate, pause, vibrate...} number values in ms
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                //finish building notification
                .build();

        //get default notification style
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
        //notification id which is specific to application
        int notificationId = 1;
        //send built notification over
        notificationManager.notify(notificationId, notification);

        //Toast notification for message confirmation
        Context context = getApplicationContext();
        //Toast message content
        CharSequence text = "Message sent!";
        //Toast message length
        int duration = Toast.LENGTH_SHORT;
        //build toast message
        Toast toast = Toast.makeText(context, text, duration);
        //show toast message
        toast.show();

        //Data Logging
        //create string with current date and time
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //create string that contains currentDateTimeString with type of command appended on it
        String good = currentDateTimeString + " Good_Command";
        //add strings to commands array
        commands.add(currentDateTimeString);
        commands.add(good);

        try{
            //open file Data.txt or create one if does not exist
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);
            //for each line of commands, write to Data.txt plus a new line
            for (int i = 0 ; i < commands.size() ; i++){
                outputFile.write(commands.get(i) + "\n");
            }
            //clean file
            outputFile.flush();
            //close file
            outputFile.close();
        }
        catch (IOException e){

        }
    }

    //same comments as goodjobnotify method, except for negative commands
    public void badjobnotify(View view) {
        mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //Notification code for negative reinforcement
        Notification notification = new NotificationCompat.Builder(getApplication())
                .setContentTitle("ADAP+")
                .setContentText("Don't do that!")
                .extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.frown)) //frowning bitmap image
                .setVibrate(new long[]{100, 300, 100, 300, 100, 300, 100,300,100,300,100})
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
        int notificationId = 1;
        notificationManager.notify(notificationId, notification);

        //Toast notification for message confirmation
        Context context = getApplicationContext();
        CharSequence text = "Message sent!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Data Logging
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String bad = currentDateTimeString + " Bad_Command";
        commands.add(currentDateTimeString);
        commands.add(bad);

        try{
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for (int i = 0 ; i < commands.size() ; i++){
                outputFile.write(commands.get(i) + "\n");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch (IOException e){

        }
    }
    //same comments as goodjobnotify method, except for custom commands
    public void sendNotification(View view) {
        mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //Notification code for custom text
        String toSend = editText.getText().toString();
        Notification notification = new NotificationCompat.Builder(getApplication())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("ADAP+")
                .setContentText(toSend)
                .extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{100, 300, 100, 500, 100, 300, 100, 500, 100, 300, 100, 500, 100, 300, 100, 500, 100})
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
        int notificationId = 1;
        notificationManager.notify(notificationId, notification);

        //Toast notification for message confirmation
        Context context = getApplicationContext();
        CharSequence text = "Message sent!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Data Logging
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String custom = currentDateTimeString + " Custom_Command";
        commands.add(currentDateTimeString);
        commands.add(custom);

        try{
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for (int i = 0 ; i < commands.size() ; i++){
                outputFile.write(commands.get(i) + "\n");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch (IOException e){

        }
    }
}
