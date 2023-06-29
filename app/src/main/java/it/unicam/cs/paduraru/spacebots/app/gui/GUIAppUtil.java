package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.paduraru.engine.GameController;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GUIAppUtil {
    private GUIAppUtil(){}
    public static final Paint transparentFill = new Color(0.9,0.9,1,0.5);
    public static final Paint black = new Color(0,0,0,1);
    public static Shape generateFxShape(PEntity entity){
        PShape pShape = ((PCollider) GameController.getEnvironment(GameController.getCurrentEnvironment())
                .getComponentOf(entity, PCollider.class).get(0)).getShape();

        if(entity instanceof PRobot robot){

            if(pShape instanceof PCircle pCircle) {
                Circle circle = new Circle(pCircle.getRadius());
                circle.setCenterX(robot.getPosition().getX());
                circle.setCenterY(robot.getPosition().getY());
                return circle;
            }

            if(pShape instanceof PRectangle pRect){//JavaFX Rectangle origin in upper left corner
                Rectangle rect = new Rectangle(pRect.getWidth(), pRect.getHeight());
                rect.setLayoutX(robot.getPosition().getX() - pRect.getWidth()/2);
                rect.setLayoutY(robot.getPosition().getY() - pRect.getHeight()/2);
                return rect;
            }

        }

        if(entity instanceof PAreaLabel area){
            if(pShape instanceof PCircle pCircle) {
                Circle circle = new Circle(pCircle.getRadius());
                circle.setCenterX(area.getPosition().getX());
                circle.setCenterY(area.getPosition().getY());
                circle.setFill(transparentFill);
                circle.setStroke(black);
                return circle;
            }

            if(pShape instanceof PRectangle pRect){//JavaFX Rectangle origin in upper left corner
                Rectangle rect = new Rectangle(pRect.getWidth(), pRect.getHeight());
                rect.setLayoutX(area.getPosition().getX() - pRect.getWidth()/2);
                rect.setLayoutY(area.getPosition().getY() - pRect.getHeight()/2);
                rect.setFill(transparentFill);
                rect.setStroke(black);
                return rect;
            }
        }
        return null;
    }

}
