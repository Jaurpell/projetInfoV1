package com.example.projetinfov1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Main extends Application {



    public static String newval;


    public static double vitesseY;
    public static double g = 0.00175d;
    public static double gP = 0.00175 * 720;// Variable qui fait office de gravité en pixel

    public static int timerSaut;
    public static int TIMERSAUTVALUE = 24;// le temps d'attente avant qu on ne puisse resauter
    public static double VITESSESAUT = 0.022d;// la vitesse à la quelle notre personnage va lors d'un saut
    public static boolean saut = false;
    public static boolean collision = false;
    public static boolean Start = false;
    public static boolean restart = false;
    public static double vitesseX;

    public static double XPix = 0.1 * 1280;
    public static double YPix = 0.3 * 720;
    public static double vitesseYP = 0;
    public static double vitesseXP;

    public static double XOPix = 0.9 * 1280;
    public static double YOPix = (0.3 * 720) + 30;




    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ChooseHerro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);



        stage.setScene(scene);
        stage.setOnCloseRequest(event -> saveGameData());
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
    private static void saveGameData() {
        try {
            // Créez un objet File avec le chemin du fichier de sauvegarde
            File file = new File("gamedata.txt");

            // Créez un objet FileWriter pour écrire dans le fichier
            FileWriter writer = new FileWriter(file);

            // Écrivez les données de jeu dans le fichier
            writer.write(GameOverController.GameOverScore + System.lineSeparator());
            writer.write(vitesseY + System.lineSeparator());
            writer.write(timerSaut + System.lineSeparator());
            writer.write(saut + System.lineSeparator());

            for (boolean b : new boolean[]{collision, Start, restart})
            {
                writer.write(b + System.lineSeparator());
            }
            writer.write(vitesseX + System.lineSeparator());
            writer.write(XPix + System.lineSeparator());
            writer.write(YPix + System.lineSeparator());
            writer.write(vitesseYP + System.lineSeparator());
            writer.write(vitesseXP + System.lineSeparator());
            writer.write(XOPix + System.lineSeparator());
            writer.write(YOPix + System.lineSeparator());

            // Fermez le FileWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
