package com.jsonkuan.hangman;

import java.util.ArrayList;
import java.util.Random;

/**
 * Hangman
 * Created by jas0n on 2016-10-21.
 */

class Hangman {

    private ArrayList<String> guessedCharacters = new ArrayList<>();
    private String[] wordDictionary = {"pig", "bird", "sits"};
    private String currentWord;
    private int guessRemaining = 10;

    private void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    void setGuessedCharacters(String guess) {
       guessedCharacters.add(guess);
    }

    void setGuessRemaining(int guessRemaining) {
        this.guessRemaining = guessRemaining;
    }

    ArrayList<String> getGuessedCharacters() {
        return guessedCharacters;
    }

    String getCurrentWord() {
        return currentWord;
    }

    int getRemainingGuesses() {
        return guessRemaining;
    }

   void selectRandomWord() {
        Random rand = new Random();
        setCurrentWord(wordDictionary[rand.nextInt(wordDictionary.length)]);
    }

   boolean[] checkIfMatches(ArrayList<String> guessedCharacters, String currentWord){
        boolean[] visible = new boolean[currentWord.length()];
        for (int i = 0; i < guessedCharacters.size(); i++) {
            for (int j = 0; j < currentWord.length(); j++) {
               if (guessedCharacters.get(i).equals(currentWord.substring(j, j+1))) {
                   visible[j] = true;
               }
            }
        }
        return visible;
    }

    String hideWord() {
        String hiddenWord = "";
        for (int i = 0; i < currentWord.length(); i++) hiddenWord += "_ ";
        return hiddenWord;
    }


    String printHiddenWord(boolean[] b) {
        String hiddenWord = "";
        for (int i = 0; i < currentWord.length(); i++) {
            if (b[i]) {
                hiddenWord += currentWord.charAt(i);
            } else {
                hiddenWord += "_ ";
            }
        }
        return hiddenWord;
    }

}
