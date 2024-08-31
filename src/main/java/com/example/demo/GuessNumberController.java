package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/game")
public class GuessNumberController {

    private final GuessNumberGame game;

    public GuessNumberController(GuessNumberGame game) {
        this.game = game;
    }

    @GetMapping("/continue")
    public String continueGame(Model model) {
        int currentScore = game.getScore(); // Keep the current score
        game.startGame(5); // Start a new game with 5 attempts
        game.setScore(currentScore); // Restore the current score

        model.addAttribute("message", "Game continued! Guess the number between 1 and 100.");
        model.addAttribute("attemptsLeft", game.getAttemptsLeft());
        model.addAttribute("score", game.getScore());
        return "game";
    }
    
    @GetMapping("/start")
    public String startGame(@RequestParam(defaultValue = "5") int maxAttempts, Model model) {
        game.startGame(maxAttempts);
        model.addAttribute("message", "Game started! Guess the number between 1 and 100.");
        model.addAttribute("attemptsLeft", game.getAttemptsLeft());
        model.addAttribute("score", game.getScore());
        model.addAttribute("targetNumber", null); // No target number to show at the start
        return "game";
    }

    @GetMapping("/guess")
    public String guessNumber(@RequestParam int number, Model model) {
        if (game.isGameOver()) {
            model.addAttribute("message", "Game over. The number was " + game.getTargetNumber() + ". Your final score is " + game.getScore() + ".");
            model.addAttribute("attemptsLeft", 0);
            model.addAttribute("score", game.getScore());
            model.addAttribute("targetNumber", game.getTargetNumber());
            return "game";
        }
        

        String result = game.checkGuess(number);

        if (game.isGameOver()) {
            model.addAttribute("message", result + " Game over. The number was " + game.getTargetNumber() + ". Your final score is " + game.getScore() + ".");
            model.addAttribute("score", game.getScore()); // Display final score
        } else {
            model.addAttribute("message", result);
            model.addAttribute("attemptsLeft", game.getAttemptsLeft());
        }
        model.addAttribute("score", game.getScore()); // Update score in the model
        model.addAttribute("targetNumber", game.getTargetNumber()); // Show target number on failure
        return "game";
    }
}
