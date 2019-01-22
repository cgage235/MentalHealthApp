package me.tmanti.mentalhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class QuotesActivity extends AppCompatActivity {

    private String[] quotes = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        Button backBtn = (Button) findViewById(R.id.backHomeBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(backToMain);
            }
        });

        final TextView quoteText = findViewById(R.id.quoteText);

        InputStream is = this.getResources().openRawResource(R.raw.quotes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if(is!=null){
            try{
                for(int x = 0; x < 100; x++){
                    quotes[x] = reader.readLine();
                }
                quoteText.setText(getQuote());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        Button newQuote = (Button) findViewById(R.id.newQuoteBtn);
        newQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quoteText.setText(getQuote());
            }
        });
    }

    public String getQuote(){
        Random rand = new Random();

        return quotes[rand.nextInt(100)];
    }
}
