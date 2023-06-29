package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GUIAppUtil {
    private GUIAppUtil(){}

    public static Shape convertToFXShape(PShape shape){
        if(shape instanceof PCircle)
            return new Circle(((PCircle) shape).getRadius());

        if(shape instanceof PRectangle)
            return new Rectangle(((PRectangle) shape).getWidth(), ((PRectangle) shape).getHeight());

        return new Circle(100);
    }
}
