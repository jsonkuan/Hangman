package com.jsonkuan.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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

    public void returnToMain(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
