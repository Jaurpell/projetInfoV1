package com.example.projetinfov1;
/**import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application
{
    //coordonnées personnage (coin haut gauche)
    // attention ce sont des ratios !
    private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();
    private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    private static double vitesseY = 0;

    private static ImageView personnage;



    //Touches :
    private static boolean saut = false;
    private static int TIMERSAUTVALUE = 23 ;
    private static double VITESSESAUT = 0.022d;
    private static int timerSaut;

    private static double g = 0.002d;
    public String pathPerso = new File("src/main/resources/Pictures/PersonnageT.png").getAbsolutePath();
    public String pathSoir = new File("src/main/resources/Pictures/Soir.png").getAbsolutePath();



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
            //ImageView background = new ImageView();
            //background.setImage(new Image(pathSoir));
            Affichage.configBackground(background, scene, root);



            //personnage:
            //résolution par défaut : 60*120 (soit 4.6875 % * 16.666666 %)
            personnage = new ImageView(new Image(pathPerso));
            //ImageView personnage = new ImageView();
            //personnage.setImage(new Image(pathPerso));

            HGX.set(0.1d);
            HGY.set(0.3d);
            //Cela permet de garder le bon format pour notre personnage
            Affichage.configurer(personnage, 0.046875d, 0.166666d, HGX, HGY);
            timerSaut = TIMERSAUTVALUE;


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
                    }
                    if(personnage.getLayoutY() + personnage.getFitHeight() > background.getFitHeight() * 0.8d)
                    {
                        HGY.set((background.getFitHeight() * 0.8 - personnage.getFitHeight()) / background.getFitHeight());
                        vitesseY = 0;
                    }

                    if(saut)
                    {
                        HGY.set(HGY.get() + vitesseY);

                        timerSaut --;
                        if(timerSaut <= 0)
                        {
                            saut = false;
                        }
                    }
                }
            };
            boucle.start();

            //action clavier :
            scene.setOnKeyReleased(e -> {
                switch (e.getCode()){
                    case SPACE :
                        if(!saut)
                        {
                            saut = true;
                            timerSaut = TIMERSAUTVALUE;
                            vitesseY = - VITESSESAUT;
                        }
                        break;

                    default:
                        break;
                }

            });

            //gestion fenêtre
            root.getChildren().addAll(background, personnage);

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
    public String pathPerso = new File("src/main/resources/Pictures/PersonnageT.png").getAbsolutePath();
    public String pathSoir = new File("src/main/resources/Pictures/Soir.png").getAbsolutePath();
    //décor:
    // --> "sol" à 20% de la hauteur de la fenêtre (en partant du bas)

    ImageView background = new ImageView(new Image(pathSoir));
//ImageView background = new ImageView();
//background.setImage(new Image(pathSoir));
            Affichage.configBackground(background, scene, root);



                    //personnage:
                    //résolution par défaut : 60*120 (soit 4.6875 % * 16.666666 %)
                    personnage = new ImageView(new Image(pathPerso));
                    //ImageView personnage = new ImageView();
                    //personnage.setImage(new Image(pathPerso));

                    HGX.set(0.1d);
                    HGY.set(0.3d);
                    //Cela permet de garder le bon format pour notre personnage
                    Affichage.configurer(personnage, 0.046875d, 0.166666d, HGX, HGY);
                    timerSaut = TIMERSAUTVALUE;**/

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Label;


public class Main extends Application
{


    //coordonnées personnage (coin haut gauche)
    // attention ce sont des ratios !
    private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();
    private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();


    // Vitesse pour le saut du personnage:
    private static double vitesseY = 0;
    private static ImageView personnage;



    //Valeur de x et de y en pixel du personnage
    private static double XPix = 0.1 * 1280;
    private static double YPix = 0.3 * 720;
    private static double vitesseYP = 0;




    //Coordonnées Obstacle
    private static SimpleDoubleProperty HGXO = new SimpleDoubleProperty();
    private static SimpleDoubleProperty HGYO = new SimpleDoubleProperty();
    private static double vitesseX;
    private static double vitesseXP;
    private static int TimerObstacle = 100;
    private static ImageView Obstacle;
    //valeur de x et y en pixel de l obstacle
    private static double XOPix = 0.9 * 1280;
    private static double YOPix = (0.3 * 720) + 30;

    private static boolean collision = false;




    //Touches :
    private static boolean saut = false;
    private static int TIMERSAUTVALUE = 23 ;
    private static double VITESSESAUT = 0.022d;
    private static int timerSaut;
    private static boolean Start = false;
    private static boolean restart = false;
    private static double g = 0.00175d;
    private static double gP = 0.00175 * 720;

    private static int Score;
    private static String newval;




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
            ImageView background = new ImageView(new Image("C:\\Users\\theoz\\OneDrive - HESSO\\Documents\\HES-SO\\S2fb\\informatique\\Projet sans git\\Jeu_Theo\\src\\main\\java\\com\\example\\jeu_theo\\Soir.png"));
            Affichage.configBackground(background, scene, root);



            //personnage:
            //résolution par défaut : 60*120 (soit 4.6875 % * 16.666666 %)
            personnage = new ImageView(new Image("C:\\Users\\theoz\\OneDrive - HESSO\\Documents\\HES-SO\\S2fb\\informatique\\Projet sans git\\Jeu_Theo\\src\\main\\java\\com\\example\\jeu_theo\\PersonnageT.png"));

            HGX.set(0.1d);
            HGY.set(0.3d);

            //Cela permet de garder le bon format pour notre personnage
            Affichage.configurer(personnage, 0.046875d, 0.166666d, HGX, HGY);
            timerSaut = TIMERSAUTVALUE;

            //Obstacle:
            //résolution par défault :60*60 (soit 4.6875 % * 8.333333 %)
            Obstacle = new ImageView(new Image("C:\\Users\\theoz\\OneDrive - HESSO\\Documents\\HES-SO\\S2fb\\informatique\\Projet sans git\\Jeu_Theo\\target\\classes\\com\\example\\jeu_theo\\Obstalce.png"));
            HGXO.set(0.9d);
            HGYO.set(0.71d);


            Affichage.configObstacle(Obstacle, 0.046875d, 0.08333333d, HGXO,HGYO);


            // ça gère l'affichage du score:
            Label l = new Label("SCORE : " + Score );
            l.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20; -fx-text-fill: #ffffff;");
            l.setAlignment(Pos.CENTER);
            l.setMinHeight(40);
            l.setMinWidth(300);
            l.setFont(new Font(STYLESHEET_CASPIAN, 20));

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

                        Score.set(Score.get() + 1);
                        String newval = Score.getValue().toString();
                        l.setText("SCORE :" + newval);


                        TimerObstacle--;
                        if (TimerObstacle <= 0)
                        {
                            HGXO.set(0.9d);
                            TimerObstacle = 100;
                            XOPix = 0.9 * 1280;
                            Score.set(Score.get() + 1);
                            newval = Score.getValue().toString();
                            l.setText("SCORE :" + newval);



                        }
                    }
                    if(restart)
                    {
                        collision = false;
                        Start = false;
                        HGXO.set(0.9d);
                        TimerObstacle = 100;
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
            root.getChildren().addAll(background, personnage, Obstacle, l);

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