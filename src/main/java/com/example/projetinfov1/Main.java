package com.example.projetinfov1;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;


public class Main extends Application
{

    //Background
    public static String pathSoir = new File("src/main/resources/Pictures/Soir.png").getAbsolutePath();
    /** public static String pathJour = new File("src/main/resources/Images/Jour.png").getAbsolutePath();
     public static String[] listBack = new String[] {pathSoir, pathJour};
     public static int nbrBack = 0;
     public static ImageView background = new ImageView();
     public static int changeBack = 0;**/


    //Obstacles:
    public static String pathObstacle1 = new File("src/main/resources/Pictures/Obstalce1.png").getAbsolutePath();
    public static String pathObstacle2 = new File("src/main/resources/Pictures/Obstacle2.png").getAbsolutePath();
    public static String pathObstacle3 = new File("src/main/resources/Pictures/Obstacle3.png").getAbsolutePath();
    public static String pathObstacle4 = new File("src/main/resources/Pictures/Obstacle4.png").getAbsolutePath();
    public static String[] listObst = new String[]{pathObstacle1, pathObstacle2, pathObstacle3, pathObstacle4};
    public static ImageView obstacle = new ImageView();
    public static Random nbrObst = new Random();
    //Variable des obstacles:
    public static SimpleDoubleProperty HGXO = new SimpleDoubleProperty();
    public static SimpleDoubleProperty HGYO = new SimpleDoubleProperty();
    public static double vitesseX; //vitesse de l'obstacle
    private static int TimerObstacle = 100; // le temps avant qu un autre obstacle se génère
    // position en x et en y en pixel et la vitesse en pixel
    private static double XOPix = 0.9 * 1280;
    private static double YOPix = (0.3 * 720) + 30;
    private static double vitesseXP;




    //Personnage:
    //coordonnées personnage (coin haut gauche)
    // attention ce sont des ratios !
    public static ImageView personnage1;
    public static String pathPerso = new File("src/main/resources/Pictures/PersonnageT.png").getAbsolutePath();
    public static String[] listPerso = new String[]{pathPerso};
    public static ImageView personnage = new ImageView();
    public static Random nbrPerso = new Random();
    //Coordonnée du personnage en ratio
    //Variable du personnage:
    public static SimpleDoubleProperty HGX = new SimpleDoubleProperty();
    public static SimpleDoubleProperty HGY = new SimpleDoubleProperty();//position en Y de notre perso
    private static double g = 0.00175d;//Variable qui fait office de gravité
    private static double gP = 0.00175 * 720;// Variable qui fait office de gravité en pixel
    public static double vitesseY;//Vitesse du personnage en Y Ratio
    private static int timerSaut;
    private static int TIMERSAUTVALUE = 24;// le temps d'attente avant qu on ne puisse resauter
    private static double VITESSESAUT = 0.022d;// la vitesse à la quelle notre personnage va lors d'un saut
    // position en x et en y en pixel et la vitesse en pixel
    private static double XPix = 0.1 * 1280;
    private static double YPix = 0.3 * 720;
    private static double vitesseYP = 0;




    //Variable pour le score:
    private static int Score;
    private static String newval;


    //Action:
    public static boolean Start = false;
    public static boolean restart = false;
    public static boolean saut = false;
    public static boolean collision = false;






    @Override
    public void start(Stage primaryStage)
    {
        try
        {

            //groupe principal:

            Group root = new Group();
            Scene scene = new Scene(root,1280,720);



            //décor:
            // --> "sol" à 20% de la hauteur de la fenêtre (en partant du bas)
            ImageView background = new ImageView(new Image(pathSoir));
            Affichage.configBackground(background, scene, root);
            /**Background.mBackground(background, Jour, Soir, listBack, nbrBack, scene, root);
             ImageView Jour = new ImageView(new Image(pathJour));
             ImageView Soir = new ImageView(new Image(pathSoir));**/



            //Cela permet de garder le bon format pour notre personnage
            ImageView personnage1 = new ImageView(new Image(pathPerso));
            Personnage.mPersonnage(personnage, personnage1, listPerso, nbrPerso, HGX, HGY);

            //Obstacle:
            ImageView Obstacle1 = new ImageView(new Image(pathObstacle1));
            ImageView Obstacle2 = new ImageView(new Image(pathObstacle2));
            ImageView Obstacle3 = new ImageView(new Image(pathObstacle3));
            ImageView Obstacle4 = new ImageView(new Image(pathObstacle4));
            Obstacle.mObstacle(obstacle, Obstacle1, Obstacle2, Obstacle3, Obstacle4, listObst, nbrObst, HGXO, HGYO);


            //Score:
            // ça gère l'affichage du score:
            Label l = new Label("SCORE : " + Score );
            l.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20; -fx-text-fill: #ffffff;");
            l.setAlignment(Pos.CENTER);
            l.setMinHeight(40);
            l.setMinWidth(300);
            l.setFont(new Font(STYLESHEET_CASPIAN, 20));
            // CETTE VARIABLE SI DESSOUS NE SERT A RIEN, IL FAUDRA RECONTROLER POUR ETRE SUR
            IntegerProperty Score = new SimpleIntegerProperty(0);








            //boucle de jeu:

            AnimationTimer boucle = new AnimationTimer()
            {
                @Override
                public void handle(long arg0)
                {
                    //gravité :
                    if (personnage.getLayoutY() + personnage.getFitHeight() <= background.getFitHeight() * 0.8d)
                    {
                        HGY.set(HGY.get() + vitesseY);
                        vitesseY += g;
                        YPix = YPix + vitesseYP;
                        vitesseYP += gP;
                    }
                    if(personnage.getLayoutY() + personnage.getFitHeight() > background.getFitHeight() * 0.8d)
                    {
                        HGY.set((background.getFitHeight() * 0.8 - personnage.getFitHeight()) / background.getFitHeight());
                        vitesseY = 0;
                        YPix = 720*0.3;
                        vitesseYP = 0;
                    }



                    if(saut == true && collision == false)
                    {
                        HGY.set(HGY.get() + vitesseY);
                        YPix = YPix + vitesseYP;
                        timerSaut --;
                        if(timerSaut <= 0)
                        {
                            saut = false;
                        }
                    }

                    if(Start == true && collision == false)
                    {
                        HGXO.set(HGXO.get() + vitesseX);
                        XOPix = XOPix + vitesseXP;


                        String newval = Score.getValue().toString();
                        l.setText("SCORE :" + newval);




                        if (XOPix <= 0)
                        {
                            vitesseX = vitesseX - 0.0002d;
                            vitesseXP = vitesseXP - (0.0002d * 1280);
                            Score.set(Score.get() + 1);
                            HGXO.set(0.9d);

                            XOPix = 0.9 * 1280;

                            newval = Score.getValue().toString();
                            l.setText("SCORE :" + newval);

                            nbrObst.nextInt(listObst.length);
                            Obstacle.mObstacle(obstacle, Obstacle1, Obstacle2, Obstacle3, Obstacle4, listObst, nbrObst, HGXO, HGYO);
/**
 changeBack = changeBack + 1;
 if (changeBack >= 3)
 {

 nbrBack = nbrBack + 1;

 if (nbrBack >= 2)
 {
 nbrBack = 0;
 Background.mBackground(background, Jour, Soir, listBack, nbrBack, scene, root);
 }
 changeBack = 0;
 }
 **/
                        }
                    }
                    if(restart)
                    {
                        collision = false;
                        Start = false;
                        HGXO.set(0.9d);

                        restart = false;
                        XOPix = 0.9 * 1280;
                        Score.set(0);
                        newval = Score.getValue().toString();
                        l.setText("SCORE :" + newval);



                    }
                    if (((XOPix-15) <= (XPix+30)) && ((XOPix+15) >=(XPix-30)) && (YPix <= YOPix))
                    {
                        collision = true;

                    }


                }
            };
            boucle.start();
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()){
                    case R:
                        if(restart == false)
                        {
                            restart = true;
                        }
                    case S:
                        if(Start == false)
                        {
                            Start = true;
                            vitesseX = -0.01d;
                            vitesseXP = -0.01 * 1280;
                        }

                    default:
                        break;

                }
            });
            //action clavier :
            scene.setOnKeyReleased(e -> {
                switch (e.getCode()){
                    case SPACE :
                        if(saut == false)
                        {
                            saut = true;
                            timerSaut = TIMERSAUTVALUE;
                            vitesseY = - VITESSESAUT;
                        }


                    default:
                        break;
                }

            });
            //gestion fenêtre
            root.getChildren().addAll(background, personnage, obstacle, l);

            scene.setFill(Color.BLACK);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}