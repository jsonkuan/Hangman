package com.jsonkuan.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Hangman hangman = new Hangman();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView currentWordTextView = (TextView) findViewById(R.id.resultWord);
        currentWordTextView.setText(String.format("Word: %s", hangman.getCurrentWord()));

        TextView remainingGuessTextView = (TextView) findViewById(R.id.resultGuesses);
        remainingGuessTextView.setText(String.format("Word: %s", hangman.getRemainingGuesses()));
    }

    public void returnToMain(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
