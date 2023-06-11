package com.example.projetinfov1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

public class Obstacle extends ChooseHerroController
{



    public static Random nbrObst = new Random();
    public static String pathObstacle1 = new File("src/main/resources/Pictures/Obstalce1.png").getAbsolutePath();
    public static String pathObstacle2 = new File("src/main/resources/Pictures/Obstacle2.png").getAbsolutePath();
    public static String pathObstacle3 = new File("src/main/resources/Pictures/Obstacle3.png").getAbsolutePath();
    public static String pathObstacle4 = new File("src/main/resources/Pictures/Obstacle4.png").getAbsolutePath();

    public static ImageView obstacle = new ImageView();

    public static String[] listObst = new String[]{pathObstacle1, pathObstacle2, pathObstacle3, pathObstacle4};



    public static ImageView mObstacle()
    {
        ImageView Obstacle1 = new ImageView(new Image(pathObstacle1));
        ImageView Obstacle2 = new ImageView(new Image(pathObstacle2));
        ImageView Obstacle3 = new ImageView(new Image(pathObstacle3));
        ImageView Obstacle4 = new ImageView(new Image(pathObstacle4));

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
