package com.example.projetinfov1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Main extends Application {


    // C est une variable qui sert à l'affichage du score
    public static String newval;



    // Ces variables sont les composant qui servent au saut du personnage
    public static double vitesseY;
    public static double g = 0.00175d;
    public static double gP = 0.00175 * 720;// Variable qui fait office de gravité en pixel
    public static int timerSaut;
    public static int TIMERSAUTVALUE = 24;// le temps d'attente avant qu on ne puisse resauter
    public static double VITESSESAUT = 0.022d;// la vitesse à la quelle notre personnage va lors d'un saut



    // Ce sont les variable qui vont être modifiée par l entrée du clavier
    public static boolean saut = false;
    public static boolean collision = false;
    public static boolean Start = false;
    public static boolean restart = false;



    //Variable pour la position des personnages
    public static double XPix = 0.1 * 1280;
    public static double YPix = 0.3 * 720;
    public static double vitesseYP = 0;
    public static double vitesseXP;



    //Variable pour la position des Obstacles XOPix et YOPix sont les position en pixel, c est variables sont utilisée pour les collisions
    public static double vitesseX;
    public static double XOPix = 0.9 * 1280;
    public static double YOPix = (0.3 * 720) + 30;




    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ChooseHerro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);



        stage.setScene(scene);
        // Cela nous permet de recharger les scores qu on a eu lors de l'utilisation du programme
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

            // Fermez le FileWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
