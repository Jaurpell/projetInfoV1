package com.example.projetinfov1;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Affichage extends Main
{
    private static double RATIO = 16d/9d;
    private static ImageView background;
    /**Group root = new Group();
     Scene scene = new Scene(root,1280,720);**/




    public static void configBackground(ImageView iV, Scene s, Group g)
    {
        background = iV;


        When condition = Bindings.when((s.widthProperty().divide(s.heightProperty())).greaterThanOrEqualTo(RATIO));
        iV.fitWidthProperty().bind(condition.then(iV.fitHeightProperty().multiply(RATIO)).otherwise(s.widthProperty()));
        iV.fitHeightProperty().bind(condition.then(s.heightProperty()).otherwise(iV.fitWidthProperty().divide(RATIO)));

        g.layoutXProperty().bind((s.widthProperty().subtract(iV.fitWidthProperty())).divide(2));
        g.layoutXProperty().bind((s.heightProperty().subtract(iV.fitHeightProperty())).divide(2));

    }



    public static void configurer(ImageView iV, double LRatio, double HRatio, SimpleDoubleProperty hGX, SimpleDoubleProperty hGY)
    {
        iV.fitHeightProperty().bind(background.fitHeightProperty().multiply(HRatio));
        iV.fitWidthProperty().bind(background.fitWidthProperty().multiply(LRatio));

        iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGX));
        iV.layoutYProperty().bind(background.fitHeightProperty().multiply(hGY));

    }
    public static void configObstacle(ImageView iV, double LRatio, double HRatio, SimpleDoubleProperty hGXO, SimpleDoubleProperty hGYO)
    {
        iV.fitHeightProperty().bind(background.fitHeightProperty().multiply(HRatio));
        iV.fitWidthProperty().bind(background.fitWidthProperty().multiply(LRatio));

        iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGXO));
        iV.layoutYProperty().bind(background.fitHeightProperty().multiply(hGYO));

    }
}
