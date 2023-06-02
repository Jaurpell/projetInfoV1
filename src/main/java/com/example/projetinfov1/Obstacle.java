package com.example.projetinfov1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Obstacle extends Main
{


    private static String[] listObst;
    private static Random nbrObst = new Random();
    private static ImageView Obstacle1;
    private static ImageView Obstacle2;
    private static ImageView Obstacle3;
    private static ImageView Obstacle4;
    private static ImageView obstacle;

    public static SimpleDoubleProperty HGXO = new SimpleDoubleProperty();
    public static SimpleDoubleProperty HGYO = new SimpleDoubleProperty();
    public static ImageView mObstacle(ImageView iv0, ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, String[] list, Random nbr, SimpleDoubleProperty hGXO, SimpleDoubleProperty hGYO )
    {
        HGXO = hGXO;
        HGYO = hGYO;
        obstacle = iv0;
        Obstacle1 = iv1;
        Obstacle2 = iv2;
        Obstacle3 = iv3;
        Obstacle4 = iv4;
        nbrObst = nbr;
        listObst = list;
        switch (nbrObst.nextInt(listObst.length))
        {
            case 0:
                obstacle.setImage(Obstacle1.getImage());

                HGXO.set(0.9d);
                HGYO.set(0.71d);

                Affichage.configObstacle(obstacle, 0.046875d, 0.08333333d, HGXO,HGYO);
                return obstacle;



            case 1:
                obstacle.setImage(Obstacle2.getImage());

                HGXO.set(0.9d);
                HGYO.set(0.71d);

                Affichage.configObstacle(obstacle, 0.046875d, 0.08333333d, HGXO,HGYO);
                return obstacle;

            case 2:
                obstacle.setImage(Obstacle3.getImage());

                HGXO.set(0.9d);
                HGYO.set(0.71d);

                Affichage.configObstacle(obstacle, 0.046875d, 0.08333333d, HGXO,HGYO);
                return obstacle;

            case 3:
                obstacle.setImage(Obstacle4.getImage());

                HGXO.set(0.9d);
                HGYO.set(0.71d);

                Affichage.configObstacle(obstacle, 0.046875d, 0.08333333d, HGXO,HGYO);
                return obstacle;
        }


        return iv0;
    }
}