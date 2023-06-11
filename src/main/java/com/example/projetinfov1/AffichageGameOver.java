package com.example.projetinfov1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AffichageGameOver extends Main
{
    public static void afficherFenetreCollision() {
        try {
            FXMLLoader loader = new FXMLLoader(AffichageGameOver.class.getResource("GameOver.fxml"));
            Parent root;
            root = loader.load();


            Scene scene = new Scene(root, 1280, 720);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
