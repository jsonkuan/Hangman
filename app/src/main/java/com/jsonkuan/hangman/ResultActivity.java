package com.jsonkuan.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        String currentWord = intent.getExtras().getString("word");
        String win = intent.getExtras().getString("win");
        int remainingGuesses = intent.getExtras().getInt("guess");

        TextView currentWordTextView = (TextView) findViewById(R.id.resultWord);
        currentWordTextView.setText(String.format("Word: %s", currentWord));

        TextView remainingGuessTextView = (TextView) findViewById(R.id.resultGuesses);
        remainingGuessTextView.setText(String.format("Remaining: %s", remainingGuesses));

        TextView winStatusTextView = (TextView) findViewById(R.id.winStatus);
        winStatusTextView.setText(String.format("%s", win));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnToMain();
                return true;
            case R.id.playIcon:
                Intent intent = new Intent(this, HangmanActivity.class);
                startActivity(intent);
                return true;
            case R.id.infoIcon:
                Intent intent2 = new Intent(this, AboutScreenActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void returnToMain() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
