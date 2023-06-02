/**package com.example.projetinfov1;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;


public class Background extends Main
{
    private static String[] listBack;
    private static int nbrBack;
    private static ImageView Soir;
    private static ImageView Jour;
    private static ImageView background;
    Group root = new Group();
    Scene scene = new Scene(root,1280,720);




    public static ImageView mBackground(ImageView iv0, ImageView iv1, ImageView iv2, String[] list, int nbr, Scene scene, Group root)
    {

        background = iv0;
        Jour = iv1;
        Soir = iv2;
        listBack = list;
        nbrBack = nbr;
        switch (nbrBack)
        {
            case 1:
                background.setImage(Soir.getImage());
                Affichage.configBackground(background, scene, root);



            case 2:
                background.setImage(Jour.getImage());
                Affichage.configBackground(background, scene, root);

        }

        return iv0;
    }
}
**/