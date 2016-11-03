package com.jsonkuan.hangman;

import java.util.ArrayList;
import java.util.Random;

/**
 * hangman
 * Created by jas0n on 2016-10-21.
 */

class Hangman {

    private ArrayList<String> guessedLetters = new ArrayList<>();
    private String[] wordDictionary = {"paranoid", "android", "xxx", "shoe"};
    private String currentWord;
    private boolean displayWinResult = false;
    private boolean guessWasCorrect = false;
    private int guessRemaining = 10;

    private void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    void addGuessedLetter(String guess) {
       guessedLetters.add(guess);
    }

    void setGuessRemaining(int guessRemaining) {
        this.guessRemaining = guessRemaining;
    }

    ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    String getCurrentWord() {
        return currentWord;
    }

    boolean getIsGuessCorrect() {
        return guessWasCorrect;
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
        if(allAreTrue(visible)) displayWinResult = true;
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

    private boolean allAreTrue(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    boolean isWordComplete() {
        return displayWinResult;
    }

    String showWinStatus() {
        if (displayWinResult) {
            return "You Win!";
        } else {
            return "You Lose!";
        }
    }

    void checkGuess() {
        String lastGuess = guessedLetters.get(guessedLetters.size() - 1);
        for (int i = 0; i < currentWord.length() ; i++) {
            if (lastGuess.equals(currentWord.substring(i, i+1))) {
                guessWasCorrect = true;
                setGuessRemaining(getRemainingGuesses() + 1);
                break;
            } else {
                guessWasCorrect = false;
            }
        }
    }

    boolean checkIfGuessed() {
        String lastGuess = guessedLetters.get(guessedLetters.size() - 1);
        boolean flag = false;

        for (int i = 0; i < guessedLetters.size() - 1; i++) {
            if (lastGuess.equals(guessedLetters.get(i))) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    void resetUI() {
        guessedLetters.remove(guessedLetters.size() - 1);
        guessRemaining += 1;
    }
}
