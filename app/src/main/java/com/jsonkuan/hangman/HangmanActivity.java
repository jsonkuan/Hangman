package com.jsonkuan.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HangmanActivity extends AppCompatActivity {

    Hangman hangman = new Hangman();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        hangman.selectRandomWord();

        TextView textview = (TextView) findViewById(R.id.wordToGuess);
        textview.setText(hangman.hideWord());

        TextView remainingGuessTextView = (TextView) findViewById(R.id.remainingGuesses);
        remainingGuessTextView.setText(String.format("Remaining: %s", hangman.getRemainingGuesses()));
    }

    public void guess(View view) {
        EditText editText = (EditText) findViewById(R.id.guessEditText);
        String playerGuess = editText.getText().toString();
        hangman.setGuessedCharacters(playerGuess);

        TextView guessedCharTextView = (TextView) findViewById(R.id.previousGuess);
        guessedCharTextView.setText(String.format("You Guessed: %s", hangman.getGuessedCharacters()));

        TextView textView = (TextView) findViewById(R.id.wordToGuess);
        boolean[] b = hangman.checkIfMatches(hangman.getGuessedCharacters(), hangman.getCurrentWord());
        textView.setText(hangman.printHiddenWord(b));

        hangman.setGuessRemaining(hangman.getRemainingGuesses() - 1);
        TextView remainingGuessTextView = (TextView) findViewById(R.id.remainingGuesses);
        remainingGuessTextView.setText(String.format("Remaining: %s", hangman.getRemainingGuesses()));

        gameResult(view);
    }

    public void gameResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        if (hangman.getRemainingGuesses() <= 0) startActivity(intent);
    }
}
