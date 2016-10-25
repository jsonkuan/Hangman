package com.jsonkuan.hangman;

import java.util.Random;

/**
 * Created by jas0n on 2016-10-21.
 */

public class Hangman {

    String[] wordDictionary = {"Angry", "Bloomy", "Curious"};
    private String currentWord = wordDictionary[1];
    private String[] guessedCorrect = {"a", "b"};
    private String[] guessedWrong = {"c", "d"};
    private int numGuesses = 5;
    private String word;

    public int getNumGuesses() {
        return numGuesses;
    }

    public String getWord(){
        return word;
    }

    public void setNumGuesses(int numGuesses) {
        this.numGuesses = numGuesses;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void selectRandomWord() {
        Random rand = new Random();
        setWord(wordDictionary[rand.nextInt(wordDictionary.length)]);
    }
}
