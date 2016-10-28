package com.jsonkuan.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.playIcon:
                playGame();
                return true;
            case R.id.infoIcon:
                showAboutScreen();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void playGame() {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
    }
    public void playGame(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
    }

    public void showAboutScreen() {
        Intent intent = new Intent(this, AboutScreenActivity.class);
        startActivity(intent);
    }

    public void showAboutScreen(View view) {
        Intent intent = new Intent(this, AboutScreenActivity.class);
        startActivity(intent);
    }
}
