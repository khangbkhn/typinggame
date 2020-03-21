package com.hon.game.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.hon.game.service.GameService;

@Service
public class GameServiceImpl implements GameService{

    private static final String API_URL = "https://random-word-api.herokuapp.com/word?number=";
    private static final Integer WORDS_NUMBER_DEFAULT = 200;

    public List<String> retrieveWords(int gameLevel) throws Exception {
        if (gameLevel <= 2) {
            return Arrays.asList(choose(ResourceUtils.getFile(String.format("classpath:%s.txt", gameLevel == 1 ? "twoWord" : "threeWord"))));
        }
        return getWordFromApi(gameLevel);
    }

    /**
     * For level 4, 5,... need to find out level +1 words
     * 
     * @return
     * @throws Exception 
     */
    private List<String> getWordFromApi(int gameLevel) throws Exception {
        boolean isEnoughWord = false;
        int sizeStep = 1;
        int wordLength = gameLevel + 1;
        int wordNumber = gameLevel == 3 ? 1 : wordLength;
        List<String> words = new ArrayList<>();
        while (!isEnoughWord) {
            words = sendGet(String.format("%s%s", API_URL, WORDS_NUMBER_DEFAULT * sizeStep));
            words = words.stream().filter(x -> x.length() == wordLength).limit(wordNumber).collect(Collectors.toList());
            sizeStep++;
            isEnoughWord = words.size() == wordNumber;
        }
        return words;
    }

    private List<String> sendGet(String url) throws Exception {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return new ArrayList<>(Arrays.asList(response.toString().replaceAll("\\[|\"|\\]", Strings.EMPTY).split(",")));
        }
    }

    private String choose(File f) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(f); sc.hasNext();) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0)
                result = line;
        }
        return result;
    }

}
