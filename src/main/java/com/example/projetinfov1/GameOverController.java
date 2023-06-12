package com.example.projetinfov1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class GameOverController extends Main {
    public static int GameOverScore;
    @FXML
    private ListView<String> playerListView;

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Button okButton;

    private ObservableList<String> players;
    public static boolean selected;

    @FXML
    public void initialize() {
        selected = ChooseHerroController.isLevel1Selected;
        players = FXCollections.observableArrayList();
        playerListView.setItems(players);
        loadDataFromFile();
        sortPlayerList(); // Tri des joueurs dans l'ordre décroissant des scores
        okButton.setOnAction(event -> addPlayerToList());
    }

    private void addPlayerToList() {
        String playerName = playerNameTextField.getText().trim();
        if (!playerName.isEmpty()) {
            int score = generateRandomScore(); // Remplacez cette ligne avec votre propre logique de score
            String playerInfo = playerName + " - Score: " + score;

            boolean isHighScore = false; // Indicateur pour savoir si le score est plus élevé que les scores existants

            if (players.size() < 5) {
                // Si la liste des joueurs n'est pas encore pleine, ajoutez le joueur directement
                players.add(playerInfo);
                isHighScore = true;
            } else {
                int lowestScoreIndex = getIndexOfLowestScore();
                if (score > getScoreFromPlayerInfo(players.get(lowestScoreIndex))) {
                    // Si le score est plus élevé que le score le plus bas, remplacez-le
                    players.set(lowestScoreIndex, playerInfo);
                    isHighScore = true;
                }
            }

            playerNameTextField.clear();
            saveDataToFile();

            if (isHighScore) {
                // Si c'est un nouveau meilleur score, affichez un message à l'utilisateur
                System.out.println("Nouveau meilleur score enregistré !");
            }

            // Tri de la liste des joueurs dans l'ordre décroissant des scores
            sortPlayerList();
        }
    }

    private int generateRandomScore() {
        GameOverScore = Score.getScore();
        // Remplacez cette méthode avec votre propre logique de génération de score
        return GameOverScore;
    }

    private int getScoreFromPlayerInfo(String playerInfo) {
        if (playerInfo.isEmpty()) {
            return 0; // ou une valeur par défaut appropriée
        }

        int startIndex = playerInfo.lastIndexOf("Score: ") + 7;
        String scoreString = playerInfo.substring(startIndex);

        if (scoreString.isEmpty()) {
            return 0; // ou une valeur par défaut appropriée
        }

        return Integer.parseInt(scoreString);
    }

    private int getIndexOfLowestScore() {
        int lowestScore = Integer.MAX_VALUE;
        int lowestScoreIndex = -1;

        for (int i = 0; i < players.size(); i++) {
            int score = getScoreFromPlayerInfo(players.get(i));
            if (score < lowestScore) {
                lowestScore = score;
                lowestScoreIndex = i;
            }
        }

        return lowestScoreIndex;
    }

    private void sortPlayerList() {
        List<String> sortedPlayers = new ArrayList<>(players);
        sortedPlayers.sort(new PlayerComparator());
        players.setAll(sortedPlayers);
    }

    private void saveDataToFile() {
        String fileName = selected ? "scores.txt" : "scores2.txt";

        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            for (String player : players) {
                writer.write(player + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {
        String fileName = selected ? "scores.txt" : "scores2.txt";

        try {
            File file = new File(fileName);

            if (file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line;
                List<String> playerList = new ArrayList<>();

                while ((line = bufferedReader.readLine()) != null) {
                    playerList.add(line);
                }

                bufferedReader.close();
                reader.close();

                if (playerList.size() <= 5) {
                    players.addAll(playerList);
                } else {
                    playerList.sort(new PlayerComparator());
                    players.addAll(playerList.subList(0, 5));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class PlayerComparator implements Comparator<String> {
        @Override
        public int compare(String player1, String player2) {
            int score1 = getScoreFromPlayerInfo(player1);
            int score2 = getScoreFromPlayerInfo(player2);
            return Integer.compare(score2, score1);
        }
    }
}

