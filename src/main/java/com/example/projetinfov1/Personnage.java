package com.example.projetinfov1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Personnage extends Main
{
    private static String[] listPerso;
    private static ImageView personnage;
    private static ImageView personnage1;
    private static Random nbrPerso = new Random();
    public static SimpleDoubleProperty HGX = new SimpleDoubleProperty();
    public static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    public static ImageView mPersonnage(ImageView iv0, ImageView iv1, String[] list, Random nbr, SimpleDoubleProperty hGX, SimpleDoubleProperty hGY)
    {
        HGX = hGX;
        HGY = hGY;
        personnage = iv0;
        personnage1 = iv1;
        nbrPerso = nbr;
        listPerso = list;
        switch (nbrPerso.nextInt(listPerso.length))
        {
            case 0:
                personnage.setImage(personnage1.getImage());

                HGX.set(0.1d);
                HGY.set(0.63d);


                Affichage.configurer(personnage, 0.046875d, 0.166666d, HGX, HGY);
                return personnage;
        }


        return iv0;


    }


}
