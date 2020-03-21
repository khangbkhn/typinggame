package com.hon.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hon.game.model.AjaxResponseBody;
import com.hon.game.model.AnswerRequest;
import com.hon.game.model.GameData;
import com.hon.game.service.GameService;
import com.hon.game.service.impl.GameServiceImpl;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    private GameData gameData;

    @RequestMapping("/start")
    public ModelAndView startGame() throws Exception {
        gameData = new GameData();
        getNextWord();

        ModelAndView model = new ModelAndView();
        model.addObject("data", gameData);
        model.setViewName("home");
        return model;
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkAnswer(@RequestBody AnswerRequest request) throws Exception {
        AjaxResponseBody result = new AjaxResponseBody();
        String answer = request.getAnswer();
        boolean isCorrect = gameData.getCurrentWord().equalsIgnoreCase(answer.trim());
        result.setCorrect(isCorrect);
        if (!isCorrect) {
            return ResponseEntity.ok(result);
        }
        getNextWord();
        result.setGameLevel(gameData.getGameLevel());
        result.setNextWord(gameData.getCurrentShuffledWord());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/surrender")
    public ResponseEntity<?> surrenderGame(@RequestBody AnswerRequest search) {
        AjaxResponseBody result = new AjaxResponseBody();
        result.setCorrect(false);
        result.setNextWord(gameData.getCurrentWord());
        return ResponseEntity.ok(result);
    }

    private void getNextWord() throws Exception {
        Map<String, String> map = null;
        if (CollectionUtils.isEmpty(gameData.getWordMap())) {
            gameData.setGameLevel(gameData.getGameLevel() + 1);
            List<String> words = gameService.retrieveWords(gameData.getGameLevel());
            map = words.stream().collect(Collectors.toMap(x -> shuffle(x), x -> x));
        } else {
            map = gameData.getWordMap();
        }
        Map.Entry<String, String> entry = map.entrySet().iterator().next();
        String key = entry.getKey();
        gameData.setCurrentShuffledWord(key);
        gameData.setCurrentWord(entry.getValue());
        map.remove(key);
        gameData.setWordMap(map);
    }

    private String shuffle(String string) {
        List<String> letters = Arrays.asList(string.split(Strings.EMPTY));
        Collections.shuffle(letters);
        return letters.stream().collect(Collectors.joining(Strings.EMPTY));
    }
}
