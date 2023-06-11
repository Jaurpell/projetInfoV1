package com.example.projetinfov1;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Comparator;
import java.util.List;

import java.io.*;



import java.util.ArrayList;


public class GameOverController extends Main {
    public static int GameOverScore;
    @FXML
    private ListView<String> playerListView;

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Button okButton;


    private ObservableList<String> players;


    @FXML
    public void initialize() {
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
        try {
            // Créez un objet File avec le chemin du fichier de sauvegarde
            File file = new File("scores.txt");

            // Créez un objet FileWriter pour écrire dans le fichier
            FileWriter writer = new FileWriter(file);

            // Parcourez la liste des joueurs et écrivez les valeurs dans le fichier
            for (String player : players) {
                writer.write(player + System.lineSeparator());
            }

            // Fermez le FileWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {
        try {
            // Créez un objet File avec le chemin du fichier de sauvegarde
            File file = new File("scores.txt");

            // Vérifiez si le fichier existe
            if (file.exists()) {
                // Créez un objet FileReader pour lire à partir du fichier
                FileReader reader = new FileReader(file);

                // Créez un objet BufferedReader pour lire les lignes du fichier
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line;
                List<String> playerList = new ArrayList<>();

                // Lisez chaque ligne du fichier
                while ((line = bufferedReader.readLine()) != null) {
                    playerList.add(line);
                }

                // Fermez le FileReader et BufferedReader
                bufferedReader.close();
                reader.close();

                // Vérifiez si le nombre de joueurs est inférieur à 5
                if (playerList.size() <= 5) {
                    players.addAll(playerList);
                } else {
                    // Triez les joueurs en fonction de leur score
                    playerList.sort(new PlayerComparator());

                    // Ajoutez les 5 meilleurs joueurs à la liste
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

