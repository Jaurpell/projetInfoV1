package com.example.projetinfov1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.text.Font;


public class Score extends Main {
    private static final IntegerProperty score = new SimpleIntegerProperty(0);

    public static int getScore() {
        return score.get();
    }

    public static void setScore(int value) {
        score.set(value);
    }

    public static void incrementScore() {
        score.set(score.get() + 1);
    }

    public static Label mScore(int scoreValue) {
        Label scoreLabel = new Label("SCORE: " + scoreValue);
        scoreLabel.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20; -fx-text-fill: #ffffff;");
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setMinHeight(40);
        scoreLabel.setMinWidth(300);
        scoreLabel.setFont(new Font(20));
        return scoreLabel;
    }
}
