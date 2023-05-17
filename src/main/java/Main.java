
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
            ImageView background = new ImageView(new Image("Soir.png"));
            Affichage.configBackground(background, scene, root);



            //personnage:
            //résolution par défaut : 60*120 (soit 4.6875 % * 16.666666 %)
            personnage = new ImageView(new Image(""));

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
