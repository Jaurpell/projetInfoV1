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
    public static int GameOverScore; // Score de fin de partie

    @FXML
    private ListView<String> playerListView; // Vue de liste pour afficher les joueurs

    @FXML
    private TextField playerNameTextField; // Champ de texte pour le nom du joueur

    @FXML
    private Button okButton; // Bouton pour ajouter le joueur

    private ObservableList<String> players; // Liste observable des joueurs
    public static boolean selected; // Indicateur pour savoir si le niveau 1 est sélectionné


    @FXML
    public void initialize() {
        selected = ChooseHerroController.isLevel1Selected; // Récupère l'indicateur de sélection du niveau 1
        players = FXCollections.observableArrayList(); // Initialise la liste observable des joueurs
        playerListView.setItems(players); // Lie la liste observable à la vue de liste
        loadDataFromFile(); // Charge les données des joueurs à partir du fichier
        sortPlayerList(); // Trie les joueurs dans l'ordre décroissant des scores
        okButton.setOnAction(event -> addPlayerToList()); // Définit l'action du bouton OK pour ajouter un joueur
    }

    private void addPlayerToList() {
        String playerName = playerNameTextField.getText().trim(); // Récupère le nom du joueur entré dans le champ de texte
        if (!playerName.isEmpty()) {
            int score = generateRandomScore(); // Génère un score aléatoire pour le joueur
            String playerInfo = playerName + " - Score: " + score; // Informations du joueur (nom + score)

            boolean isHighScore = false; // Indicateur pour savoir si le score est plus élevé que les scores existants

            if (players.size() < 5) {
                // Si la liste des joueurs n'est pas encore pleine, ajoutez le joueur directement
                players.add(playerInfo);
                isHighScore = true;
            } else {
                int lowestScoreIndex = getIndexOfLowestScore(); // Récupère l'index du score le plus bas dans la liste
                if (score > getScoreFromPlayerInfo(players.get(lowestScoreIndex))) {
                    // Si le score est plus élevé que le score le plus bas, remplacez-le
                    players.set(lowestScoreIndex, playerInfo);
                    isHighScore = true;
                }
            }

            playerNameTextField.clear(); // Efface le champ de texte du nom du joueur
            saveDataToFile(); // Enregistre les données des joueurs dans le fichier

            if (isHighScore) {
                // Si c'est un nouveau meilleur score, affichez un message à l'utilisateur
                System.out.println("Nouveau meilleur score enregistré !");
            }

            // Trie de la liste des joueurs dans l'ordre décroissant des scores
            sortPlayerList();
        }
    }

    private int generateRandomScore() {
        GameOverScore = Score.getScore(); // Récupère le score final de la partie depuis une autre classe
        return GameOverScore;
    }

    private int getScoreFromPlayerInfo(String playerInfo) {
        if (playerInfo.isEmpty()) {
            return 0; // Retourne 0 si les informations du joueur sont vides
        }

        int startIndex = playerInfo.lastIndexOf("Score: ") + 7; // Récupère l'index du début du score
        String scoreString = playerInfo.substring(startIndex); // Extrait la chaîne du score à partir de l'index

        if (scoreString.isEmpty()) {
            return 0; // Retourne 0 si la chaîne du score est vide
        }

        return Integer.parseInt(scoreString); // Convertit la chaîne du score en entier et la retourne
    }

    private int getIndexOfLowestScore() {
        int lowestScore = Integer.MAX_VALUE; // Score le plus bas initialisé à une valeur maximale
        int lowestScoreIndex = -1; // Index du score le plus bas initialisé à -1

        for (int i = 0; i < players.size(); i++) {
            int score = getScoreFromPlayerInfo(players.get(i)); // Récupère le score du joueur à l'index i
            if (score < lowestScore) {
                lowestScore = score; // Met à jour le score le plus bas
                lowestScoreIndex = i; // Met à jour l'index du score le plus bas
            }
        }

        return lowestScoreIndex; // Retourne l'index du score le plus bas
    }

    private void sortPlayerList() {
        List<String> sortedPlayers = new ArrayList<>(players); // Copie la liste des joueurs dans une nouvelle liste
        sortedPlayers.sort(new PlayerComparator()); // Trie la nouvelle liste des joueurs à l'aide d'un comparateur
        players.setAll(sortedPlayers); // Met à jour la liste observable avec la nouvelle liste triée
    }

    private void saveDataToFile() {
        String fileName = selected ? "scores.txt" : "scores2.txt"; // Détermine le nom du fichier en fonction du niveau sélectionné

        try {
            File file = new File(fileName); // Crée un objet File avec le nom du fichier
            FileWriter writer = new FileWriter(file); // Crée un FileWriter pour écrire dans le fichier

            for (String player : players) {
                writer.write(player + System.lineSeparator()); // Écrit chaque joueur dans une ligne du fichier
            }

            writer.close(); // Ferme le FileWriter
        } catch (IOException e) {
            e.printStackTrace(); // Affiche les erreurs s'il y en a
        }
    }

    private void loadDataFromFile() {
        String fileName = selected ? "scores.txt" : "scores2.txt"; // Détermine le nom du fichier en fonction du niveau sélectionné

        try {
            File file = new File(fileName); // Crée un objet File avec le nom du fichier

            if (file.exists()) { // Vérifie si le fichier existe
                FileReader reader = new FileReader(file); // Crée un FileReader pour lire le fichier
                BufferedReader bufferedReader = new BufferedReader(reader); // Crée un BufferedReader pour lire ligne par ligne

                String line;
                List<String> playerList = new ArrayList<>(); // Liste temporaire pour stocker les joueurs

                while ((line = bufferedReader.readLine()) != null) {
                    playerList.add(line); // Ajoute chaque ligne du fichier à la liste des joueurs
                }

                bufferedReader.close(); // Ferme le BufferedReader
                reader.close(); // Ferme le FileReader

                if (playerList.size() <= 5) {
                    players.addAll(playerList); // Ajoute tous les joueurs à la liste observable
                } else {
                    playerList.sort(new PlayerComparator()); // Trie la liste des joueurs à l'aide d'un comparateur
                    players.addAll(playerList.subList(0, 5)); // Ajoute les 5 premiers joueurs à la liste observable
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Affiche les erreurs s'il y en a
        }
    }

    private class PlayerComparator implements Comparator<String> {
        @Override
        public int compare(String player1, String player2) {
            int score1 = getScoreFromPlayerInfo(player1); // Récupère le score du joueur 1
            int score2 = getScoreFromPlayerInfo(player2); // Récupère le score du joueur 2
            return Integer.compare(score2, score1); // Compare les scores dans l'ordre décroissant
        }
    }
}

