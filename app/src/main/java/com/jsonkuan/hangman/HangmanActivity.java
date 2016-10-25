package com.jsonkuan.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HangmanActivity extends AppCompatActivity {

    Hangman hangman = new Hangman();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        hangman.selectRandomWord();
    }


}
