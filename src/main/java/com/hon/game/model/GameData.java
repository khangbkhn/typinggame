package com.hon.game.model;

import java.util.Map;

public class GameData {

    private int gameLevel;
    private Map<String, String> wordMap;
    private String currentShuffledWord;
    private String currentWord;

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public Map<String, String> getWordMap() {
        return wordMap;
    }

    public void setWordMap(Map<String, String> wordMap) {
        this.wordMap = wordMap;
    }

    public String getCurrentShuffledWord() {
        return currentShuffledWord;
    }

    public void setCurrentShuffledWord(String currentShuffledWord) {
        this.currentShuffledWord = currentShuffledWord;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

}
