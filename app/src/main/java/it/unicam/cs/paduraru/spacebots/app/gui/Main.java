package it.unicam.cs.paduraru.spacebots.app.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args){
        class a{
            int i = 3;

            public int get(){
                return i;
            }
        }

        class b extends a{
            public int get(){
                return i+5;
            }
        }
        System.out.println("==== MAIN APP =====");
        System.out.println("====   START  =====");

        launch();

        System.out.println("====    END   =====");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("forms/spaceBotsGui.fxml")));
        primaryStage.setTitle("Paduraru Danut Razvan - Spacebots");
        primaryStage.setScene(new Scene(root, 550, 700));
        primaryStage.show();
    }
}
