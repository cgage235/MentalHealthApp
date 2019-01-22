package me.tmanti.mentalhealth;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MeditateActivity extends AppCompatActivity {

    private SeekBar seekBarMed;
    private TextView seekBarProg;
    private Timer timer = new Timer();
    private Button startButton;
    private Button endButton;

    private HashMap<String, Integer> seekToTime = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);
        //if(getIntent().hasExtra("me.tmanti.mentalheath.test")){ String text = getIntent().getExtras().getString("me.tmanti.mentalheath.test"); }

        seekToTime.put("0", 30);
        seekToTime.put("1", 60);
        seekToTime.put("2", 120);
        seekToTime.put("3", 180);
        seekToTime.put("4", 240);
        seekToTime.put("5", 300);


        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(backToMain);
            }
        });

        seekBarProg = (TextView) findViewById(R.id.seekBarProgress);
        seekBarMed = (SeekBar) findViewById(R.id.meditationLen);
        seekBarProg.setText(String.valueOf(seekToTime.get(""+seekBarMed.getProgress()))+" Seconds");

        seekBarMed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProg.setText(String.valueOf(seekToTime.get(""+progress))+" Seconds");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startButton = (Button) findViewById(R.id.startMeditationBtn);
        endButton = (Button) findViewById(R.id.stopMeditationBtn);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startButton.setVisibility(View.GONE);
                startButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stop();
                    }
                }, seekToTime.get(""+seekBarMed.getProgress())*1000);
                endButton.setVisibility(View.VISIBLE);

                seekBarMed.setEnabled(true);

                timer.scheduleAtFixedRate(new TimerTask() {

                    int count = 0;



                    @Override
                        public void run() {
                        //Called at every 1000 milliseconds (1 second)
                            Vibrate();
                            count++;

                            if(count == seekToTime.get(""+seekBarMed.getProgress())/5){
                                timer.cancel();

                            }
                        }
                    },
                //set the amount of time in milliseconds before first execution
                0,
                //Set the amount of time between each execution (in milliseconds)
                5000);
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

    }

    private void stop(){
        timer.purge();
        startButton.setVisibility(View.VISIBLE);
        endButton.setVisibility(View.GONE);
        seekBarMed.setEnabled(true);
    }

    private void Vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}
