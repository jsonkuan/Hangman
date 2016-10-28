package com.jsonkuan.hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        hangman.selectRandomWord();

        TextView textview = (TextView) findViewById(R.id.wordToGuess);
        textview.setText(hangman.hideWord());

        TextView remainingGuessTextView = (TextView) findViewById(R.id.remainingGuesses);
        remainingGuessTextView.setText(String.format("Remaining: %s", hangman.getRemainingGuesses()));

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(images[currentImage]);
    }

    public void guess(View view) {

        validateInputToast();

        TextView guessedLetterTextView = (TextView) findViewById(R.id.previousGuess);
        guessedLetterTextView.setText(String.format("You Guessed: %s", hangman.getGuessedLetters()));

        TextView textView = (TextView) findViewById(R.id.wordToGuess);
        boolean[] b = hangman.checkIfMatches(hangman.getGuessedLetters(), hangman.getCurrentWord());
        textView.setText(hangman.printHiddenWord(b));

        hangman.setGuessRemaining(hangman.getRemainingGuesses() - 1);
        TextView remainingGuessTextView = (TextView) findViewById(R.id.remainingGuesses);
        remainingGuessTextView.setText(String.format("Remaining: %s", hangman.getRemainingGuesses()));

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
        if(!hangman.getIsGuessCorrect()) {
            currentImage -= 1;
            imageView.setImageResource(images[currentImage]);
        }
    }

    private void clearEditText() {
        EditText editText = (EditText) findViewById(R.id.guessEditText);
        editText.setText("");
    }

    private void validateInputToast(){
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.already_guessed_toast);
        CharSequence text2 = getString(R.string.only_one_letter);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        Toast toast2 = Toast.makeText(context, text2, duration);

        EditText editText = (EditText) findViewById(R.id.guessEditText);
        String playerGuess = editText.getText().toString();
        hangman.addGuessedLetter(playerGuess);

        if(hangman.checkIfGuessed()) {
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
