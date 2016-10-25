package com.jsonkuan.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void playGame(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
    }

    public void showAboutScreen(View view) {
        Intent intent = new Intent(this, AboutScreenActivity.class);
        startActivity(intent);
    }
}
