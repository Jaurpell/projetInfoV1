package com.example.projetinfov1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

public class Obstacle extends ChooseHerroController
{


    //Nombre aléatoire pour la génération d'obstacle
    public static Random nbrObst = new Random();

    // Les liens vers nos images d'obstacle
    public static String pathObstacle1 = new File("src/main/resources/Pictures/Obstalce1.png").getAbsolutePath();
    public static String pathObstacle2 = new File("src/main/resources/Pictures/Obstacle2.png").getAbsolutePath();
    public static String pathObstacle3 = new File("src/main/resources/Pictures/Obstacle3.png").getAbsolutePath();
    public static String pathObstacle4 = new File("src/main/resources/Pictures/Obstacle4.png").getAbsolutePath();

    //L'image Obstacle qui va être utiliser pour l'affichage
    public static ImageView obstacle = new ImageView();

    // Liste des liens pour les obstacle
    public static String[] listObst = new String[]{pathObstacle1, pathObstacle2, pathObstacle3, pathObstacle4};



    public static ImageView mObstacle()
    {
        //Charge les images de nos 4 Obstacles different
        ImageView Obstacle1 = new ImageView(new Image(pathObstacle1));
        ImageView Obstacle2 = new ImageView(new Image(pathObstacle2));
        ImageView Obstacle3 = new ImageView(new Image(pathObstacle3));
        ImageView Obstacle4 = new ImageView(new Image(pathObstacle4));

        // Un Switch qui permet de choisir entre un des 4 obstacle à l'aide de la génération d'un nombre aléatoire
        switch (nbrObst.nextInt(listObst.length)) {
            case 0 -> {
                obstacle.setImage(Obstacle1.getImage());
                return obstacle;
            }
            case 1 -> {
                obstacle.setImage(Obstacle2.getImage());
                return obstacle;
            }
            case 2 -> {
                obstacle.setImage(Obstacle3.getImage());
                return obstacle;
            }
            case 3 -> {
                obstacle.setImage(Obstacle4.getImage());
                return obstacle;
            }
        }
        return obstacle;
    }
}
