package com.example.projetinfov1;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Affichage extends Main {
    private static final double RATIO = 16d / 9d; // Ratio de l'aspect ratio souhaité

    private static ImageView background; // ImageView pour l'arrière-plan

    public static void configBackground(ImageView iV, Scene s, Group g) {
        background = iV; // Définit l'ImageView de l'arrière-plan

        // Condition pour déterminer si la largeur de la scène est suffisante pour respecter le ratio souhaité
        When condition = Bindings.when((s.widthProperty().divide(s.heightProperty())).greaterThanOrEqualTo(RATIO));

        // Lie les propriétés de l'ImageView pour ajuster sa taille en fonction du ratio et de la taille de la scène
        iV.fitWidthProperty().bind(condition.then(iV.fitHeightProperty().multiply(RATIO)).otherwise(s.widthProperty()));
        iV.fitHeightProperty().bind(condition.then(s.heightProperty()).otherwise(iV.fitWidthProperty().divide(RATIO)));

        // Centre le groupe dans la scène en ajustant sa position en fonction de la taille de l'ImageView de l'arrière-plan
        g.layoutXProperty().bind((s.widthProperty().subtract(iV.fitWidthProperty())).divide(2));
        g.layoutYProperty().bind((s.heightProperty().subtract(iV.fitHeightProperty())).divide(2));
    }

    public static void configurer(ImageView iV, double LRatio, double HRatio, SimpleDoubleProperty hGX, SimpleDoubleProperty hGY) {
        // Lie les propriétés de l'ImageView pour ajuster sa taille et sa position en fonction de l'ImageView de l'arrière-plan
        iV.fitHeightProperty().bind(background.fitHeightProperty().multiply(HRatio));
        iV.fitWidthProperty().bind(background.fitWidthProperty().multiply(LRatio));

        iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGX).add(background.layoutXProperty()));
        iV.layoutYProperty().bind(background.fitHeightProperty().multiply(hGY).add(background.layoutYProperty()));
    }

    public static void configObstacle(ImageView iV, double LRatio, double HRatio, SimpleDoubleProperty hGXO, SimpleDoubleProperty hGYO) {
        // Lie les propriétés de l'ImageView pour ajuster la taille et la position en fonction de l'ImageView de l'arrière-plan
        iV.fitHeightProperty().bind(background.fitHeightProperty().multiply(HRatio));
        iV.fitWidthProperty().bind(background.fitWidthProperty().multiply(LRatio));

        iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGXO).add(background.layoutXProperty()));
        iV.layoutYProperty().bind(background.fitHeightProperty().multiply(hGYO).add(background.layoutYProperty()));
    }
}
