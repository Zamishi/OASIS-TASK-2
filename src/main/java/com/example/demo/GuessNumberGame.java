package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class GuessNumberGame {

    private int targetNumber;
    private int attemptsLeft;
    private int score;

    public GuessNumberGame() {
        // Default constructor
    }

    public void startGame(int maxAttempts) {
        this.attemptsLeft = maxAttempts;
        this.targetNumber = (int) (Math.random() * 100) + 1;
        this.score = 0;
    }

    public String checkGuess(int guess) {
        attemptsLeft--;
        String result;
        if (guess == targetNumber) {
            score += 1; // Increment score by 1 for a correct guess
            result = "Correct! You've guessed the number!";
        } else if (guess < targetNumber) {
            result = "Too low! Try again.";
        } else {
            result = "Too high! Try again.";
        }
        return result;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0;
    }

    public int getScore() {
        return score;
    }

    public String getHint() {
        if (attemptsLeft <= 2) {
            return "The number is " + (targetNumber % 2 == 0 ? "even" : "odd") + " and ";
        }
        return "";
    }

    public String getProximityHint(int guess) {
        if (attemptsLeft <= 2) {
            int difference = Math.abs(targetNumber - guess);
            if (difference <= 10) {
                return "You're very close!";
            } else if (difference <= 20) {
                return "You're close.";
            } else {
                return "You're far away.";
            }
        }
        return "";
    }

    public int getTargetNumber() {
        return targetNumber; // Provide a method to access the target number
    }
    
    public void setScore(int score) {
        this.score = score;
    }

}
