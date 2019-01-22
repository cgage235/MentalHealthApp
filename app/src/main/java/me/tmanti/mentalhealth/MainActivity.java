package me.tmanti.mentalhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button meditationBtn = (Button) findViewById(R.id.meditationSwitchButton);
        meditationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeActivity = new Intent(getApplicationContext(), MeditateActivity.class);
                //show how to pass information
                //changeActivity.putExtra("me.tmanti.mentalheath.test", "this is a test");

                startActivity(changeActivity);
            }
        });

        Button quoteBtn = (Button) findViewById(R.id.quoteSwitchButton);
        quoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeActivity = new Intent(getApplicationContext(), QuotesActivity.class);

                startActivity(changeActivity);
            }
        });
    }
}
