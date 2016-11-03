package com.jsonkuan.hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HangmanActivity extends AppCompatActivity {

    Hangman hangman = new Hangman();
    int currentImage = 10;
    int[] images = {R.drawable.hang0, R.drawable.hang1, R.drawable.hang2, R.drawable.hang3, R.drawable.hang4, R.drawable.hang5, R.drawable.hang6, R.drawable.hang7, R.drawable.hang8, R.drawable.hang9, R.drawable.hang10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        hangman.selectRandomWord();

        TextView textview = (TextView) findViewById(R.id.wordToGuess);
        textview.setText(hangman.hideWord());

        TextView remainingGuessTextView = (TextView) findViewById(R.id.remainingGuesses);
        remainingGuessTextView.setText(String.format("Tries Left: %s", hangman.getRemainingGuesses()));

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(images[currentImage]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return super.onOptionsItemSelected(item);
            case R.id.infoIcon:
                Intent intent2 = new Intent(this, AboutScreenActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void guess(View view) {

        validateInputToast();

        hangman.setGuessRemaining(hangman.getRemainingGuesses() - 1);
        TextView remainingGuessTextView = (TextView) findViewById(R.id.remainingGuesses);
        remainingGuessTextView.setText(String.format("Tries Left: %s", hangman.getRemainingGuesses()));

        TextView guessedLetterTextView = (TextView) findViewById(R.id.previousGuess);
        guessedLetterTextView.setText(String.format("%s", hangman.getGuessedLetters()));

        TextView textView = (TextView) findViewById(R.id.wordToGuess);
        boolean[] b = hangman.checkIfMatches(hangman.getGuessedLetters(), hangman.getCurrentWord());
        textView.setText(hangman.printHiddenWord(b));

        calculateGameResult();
        clearEditText();
    }

    private void calculateGameResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        Intent intent2 = new Intent(getBaseContext(), ResultActivity.class);

        if ((hangman.getRemainingGuesses() <= 0) || (hangman.isWordComplete())) {

            intent2.putExtra("win", hangman.showWinStatus());
            intent2.putExtra("word", hangman.getCurrentWord());
            intent2.putExtra("guess", hangman.getRemainingGuesses());

            startActivity(intent);
            startActivity(intent2);
        }
    }

    private void changeImageSource() {
        hangman.checkGuess();
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if (!hangman.getIsGuessCorrect()) {
            currentImage -= 1;
            imageView.setImageResource(images[currentImage]);
        }
    }

    private void clearEditText() {
        EditText editText = (EditText) findViewById(R.id.guessEditText);
        editText.setText("");
    }

    private void validateInputToast() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = getString(R.string.already_guessed_toast);
        CharSequence text2 = getString(R.string.only_one_letter);
        CharSequence text3 = getString(R.string.enter_a_letter_message);

        Toast toast = Toast.makeText(context, text, duration);
        Toast toast2 = Toast.makeText(context, text2, duration);
        Toast toast3 = Toast.makeText(context, text3, duration);

        EditText editText = (EditText) findViewById(R.id.guessEditText);
        String playerGuess = editText.getText().toString();
        hangman.addGuessedLetter(playerGuess);


        if (playerGuess.equals("")) {
            hangman.resetUI();
            toast3.show();
        } else if (hangman.checkIfGuessed()) {
            hangman.resetUI();
            toast.show();
        } else if (editText.length() > 1) {
            hangman.resetUI();
            toast2.show();

        } else {
            changeImageSource();
        }
    }
}
