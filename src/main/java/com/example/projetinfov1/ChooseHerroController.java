package com.example.projetinfov1;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




import java.io.File;
import java.util.Random;

public class ChooseHerroController extends Main {


    // Ces variables servent à charger le lien vers nos images
    private static final String pathBackLev1 = new File("src/main/resources/Pictures/Jour.png").getAbsolutePath();
    private static final String pathBackLev2 = new File("src/main/resources/Pictures/Soir.png").getAbsolutePath();
    private static final String pathPersoJ = new File("src/main/resources/Pictures/PersonnageJ.png").getAbsolutePath();
    private static final String pathPersoT = new File("src/main/resources/Pictures/PersonnageT.png").getAbsolutePath();



    // Ce sont les variables des positions X et Y du personnage et des Obstacle, qui nous servent à l'affichage.
    // C'est des ratios en pourcents qui nous servent à gardé les proportions si on redimentionne notre fenêtre
    public static SimpleDoubleProperty HGX = new SimpleDoubleProperty(0.1d);
    public static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    public static SimpleDoubleProperty HGXO = new SimpleDoubleProperty(0.9d);
    public static SimpleDoubleProperty HGYO = new SimpleDoubleProperty(0.71d);



    // Notre obstacle qui va être  modifier à chaque passage d obstacle, Il va faire appel à la classe obstacle plus tard dans le code
    public static ImageView obstacle = new ImageView();




    // Des variables qui vont être utilisées pour l'affichage du score
    public static Label affScore;
    public static int score = 0;



    // Les 2 boutons pour séléctionner le Level 1 et le Level 2
    @FXML
    public static Button level1Button;
    @FXML
    public static Button level2Button;


    // Cette variable sert à dire si le niveau 1 est séléctionné,
    // Elle est utilisée pour charger le Leader Board du niveau 1 ou du niveau 2
    public static boolean isLevel1Selected = true;


    //méthode du level 1
    @FXML
    public boolean mLev1() {
        isLevel1Selected = true;
        startGame();
        return isLevel1Selected;
    }

    //méthode du level 2
    @FXML
    public boolean mLev2() {
        isLevel1Selected = false;
        startGame();
        return isLevel1Selected;
    }

    // La boucle de jeu
    private void startGame() {
        try {
            // permet de séléctionner le personnage et le background du level 1 ou du level 2
            String pathBack = isLevel1Selected ? pathBackLev1 : pathBackLev2;
            String pathPerso = isLevel1Selected ? pathPersoJ : pathPersoT;

            ImageView background = new ImageView(new Image(pathBack));
            ImageView personnage = new ImageView(new Image(pathPerso));

            // la position du personnage en Y, c est un ratio en haut a gauche de la fenêtre
            HGY.set(0.63d);
            // Appel de la méthode score pour le générer
            affScore = Score.mScore(score);
            //Appel de la méthode mObstacle pour générer un un Obstalcle
            obstacle.setImage(Obstacle.mObstacle().getImage());

            Group root = new Group();
            Scene scene = new Scene(root, 1280, 720);


            // des appels à la classe Affichage, cela  permet de garder les proportions en cas de redimensionnement de la fenêtre
            Affichage.configBackground(background, scene, root);
            // les valeures en bleu sont les ratios, c'est la taille des images en pourcent
            Affichage.configurer(personnage, 0.046875d, 0.166666d, HGX, HGY);
            Affichage.configObstacle(obstacle, 0.046875d, 0.08333333d, HGXO, HGYO);

            root.getChildren().addAll(background, personnage, obstacle, affScore);
            Stage stage = new Stage();

            stage.setScene(scene);

            scene.setFill(Color.BLACK);
            stage.show();
            AnimationTimer boucle = new AnimationTimer() {
                @Override
                public void handle(long arg0) {

                    //gravité :
                    if (personnage.getLayoutY() + personnage.getFitHeight() <= background.getFitHeight() * 0.8d) {
                        HGY.set(HGY.get() + vitesseY);
                        vitesseY += g;
                        YPix = YPix + vitesseYP;
                        vitesseYP += gP;
                    }
                    if (personnage.getLayoutY() + personnage.getFitHeight() > background.getFitHeight() * 0.8d) {
                        HGY.set((background.getFitHeight() * 0.8 - personnage.getFitHeight()) / background.getFitHeight());
                        vitesseY = 0;
                        YPix = 720 * 0.3;
                        vitesseYP = 0;
                    }
                    // cela nous permet de gérer le saut du personnage
                    if (saut && !collision) {
                        HGY.set(HGY.get() + vitesseY);
                        YPix = YPix + vitesseYP;

                        if (YPix == 0.3 * 720) {
                            saut = false;
                        }
                    }
                    //Cela nous permet de démarer le programme
                    if (Start && !collision) {
                        HGXO.set(HGXO.get() + vitesseX);
                        XOPix = XOPix + vitesseXP;
                        newval = String.valueOf(Score.getScore());
                        affScore.setText("SCORE :" + newval);
                        // Cela nous permet que lorsqu'un Obstacle c'est déplacé, d'augmenter le score, remise à zéro de la position de l'obstacle
                        //Génération d'un nouvel Obstacle aléatoire en faisant appel à la classe Obstacle
                        if (XOPix <= 0) {
                            Score.incrementScore();
                            HGXO.set(0.9d);
                            XOPix = 0.9 * 1280;

                            newval = String.valueOf(Score.getScore());
                            affScore.setText("SCORE :" + newval);

                            Random random = new Random();
                            random.nextInt(Obstacle.listObst.length);
                            obstacle.setImage(Obstacle.mObstacle().getImage());
                        }
                    }
                    // Une sécurité qui nous oblige a avoir une collision pour redémarer le programme
                    if (collision) {
                        XOPix = 0;
                        // Remet toutes nos variables à leurs valeures d'origine
                        if (restart) {
                            collision = false;
                            Start = false;
                            HGXO.set(0.9d);
                            restart = false;
                            XOPix = 0.9 * 1280;
                            Score.setScore(0);
                            newval = String.valueOf(Score.getScore());
                            affScore.setText("SCORE :" + newval);
                        }
                    }
                    // ça nous permet de gérer la collision, c'est les positions des Obstacles en pixels
                    if (((XOPix - 15) <= (XPix + 30)) && ((XOPix + 15) >= (XPix - 30)) && (YPix <= YOPix)) {
                        AffichageGameOver.afficherFenetreCollision();
                        collision = true;
                    }
                }
            };
            boucle.start();
            // Cela gère toutes les entrée clavier de l'utilisateur
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case SPACE:
                        if (!saut) {
                            saut = true;
                            timerSaut = TIMERSAUTVALUE;
                            vitesseY = -VITESSESAUT;
                            break;
                        }
                    case R:
                        if (!restart) {
                            restart = true;
                            break;
                        }
                    case S:
                        if (!Start) {
                            Start = true;
                            //Change la vitesse du personnage entre le level 1 et le level 2
                            vitesseX = isLevel1Selected ? -0.01d : -0.02d;
                            vitesseXP = isLevel1Selected ?-(0.01 * 1280):-(0.02 * 1280);
                            collision = false;
                            break;
                        }
                    default:
                        break;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
