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
    private static final String pathBackLev1 = new File("src/main/resources/Pictures/Jour.png").getAbsolutePath();
    private static final String pathBackLev2 = new File("src/main/resources/Pictures/Soir.png").getAbsolutePath();
    private static final String pathPersoJ = new File("src/main/resources/Pictures/PersonnageJ.png").getAbsolutePath();
    private static final String pathPersoT = new File("src/main/resources/Pictures/PersonnageT.png").getAbsolutePath();

    public static SimpleDoubleProperty HGX = new SimpleDoubleProperty(0.1d);
    public static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    public static SimpleDoubleProperty HGXO = new SimpleDoubleProperty(0.9d);
    public static SimpleDoubleProperty HGYO = new SimpleDoubleProperty(0.71d);

    public static ImageView obstacle = new ImageView();
    public static Label affScore;

    @FXML
    public static Button level1Button;
    @FXML
    public static Button level2Button;
    public static int score = 0;
    public static boolean isLevel1Selected = true;

    @FXML
    public boolean mLev1() {
        isLevel1Selected = true;
        startGame();
        return isLevel1Selected;
    }

    @FXML
    public boolean mLev2() {
        isLevel1Selected = false;
        startGame();
        return isLevel1Selected;
    }

    private void startGame() {
        try {
            String pathBack = isLevel1Selected ? pathBackLev1 : pathBackLev2;
            String pathPerso = isLevel1Selected ? pathPersoJ : pathPersoT;

            ImageView background = new ImageView(new Image(pathBack));
            ImageView personnage = new ImageView(new Image(pathPerso));

            HGY.set(0.63d);

            affScore = Score.mScore(score);

            obstacle.setImage(Obstacle.mObstacle().getImage());

            Group root = new Group();
            Scene scene = new Scene(root, 1280, 720);

            Affichage.configBackground(background, scene, root);
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
                    if (saut && !collision) {
                        HGY.set(HGY.get() + vitesseY);
                        YPix = YPix + vitesseYP;

                        if (YPix == 0.3 * 720) {
                            saut = false;
                        }
                    }
                    if (Start && !collision) {
                        HGXO.set(HGXO.get() + vitesseX);
                        XOPix = XOPix + vitesseXP;
                        newval = String.valueOf(Score.getScore());
                        affScore.setText("SCORE :" + newval);
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
                    if (collision) {
                        XOPix = 0;

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
                    if (((XOPix - 15) <= (XPix + 30)) && ((XOPix + 15) >= (XPix - 30)) && (YPix <= YOPix)) {
                        AffichageGameOver.afficherFenetreCollision();
                        collision = true;
                    }
                }
            };
            boucle.start();

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
