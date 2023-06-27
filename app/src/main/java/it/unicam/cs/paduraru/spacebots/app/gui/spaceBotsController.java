package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Done;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Forever;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Move;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

public class spaceBotsController {

    //region Injected GUI
    @FXML
    AnchorPane simPane, mainPane;
    @FXML
    public Circle swarmTool, circleTool;
    @FXML
    public Rectangle rectTool;

    @FXML
    Tab entitiesTab;
    //endregion

    Shape selectedTool;

    GUISpaceBotsEnvBuilder guiEnvBuilder = new GUISpaceBotsEnvBuilder();

    @FXML
    void initialize() {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(simPane.widthProperty());
        clip.heightProperty().bind(simPane.heightProperty());
        simPane.setClip(clip);

        selectedTool = swarmTool;
        swarmTool.setVisible(false);
        rectTool.setVisible(false);
        circleTool.setVisible(false);
    }

    @FXML
    void onClick_AddSwarm(MouseEvent event) {
        testAlert("AddSwarm");
    }

    @FXML
    void onClick_ClearSimulation(MouseEvent event) {
        testAlert("Clear");
    }

    @FXML
    void onClick_LoadFile(MouseEvent event) {
        testAlert("Load File");
    }

    @FXML
    void onClick_MoveDown(MouseEvent event) {
        testAlert("Move Down");
    }

    @FXML
    void onClick_MoveLeft(MouseEvent event) {
        testAlert("Move Left");
    }

    @FXML
    void onClick_MoveRight(MouseEvent event) {
        testAlert("Move Right");
    }

    @FXML
    void onClick_MoveUp(MouseEvent event) {
        testAlert("Move Up");
    }

    @FXML
    void onClick_SaveFile(MouseEvent event) {
        testAlert("Save File");
    }

    @FXML
    void onClick_SelectAddCircleTool(MouseEvent event) {
        swarmTool.setVisible(false);
        circleTool.setVisible(true);
        rectTool.setVisible(false);
        selectedTool = circleTool;
    }

    @FXML
    void onClick_SelectAddSquareTool(MouseEvent event) {
        swarmTool.setVisible(false);
        circleTool.setVisible(false);
        rectTool.setVisible(true);
        selectedTool = rectTool;
    }

    @FXML
    void onClick_SelectAddSwarmTool(MouseEvent event) {
        swarmTool.setVisible(true);
        circleTool.setVisible(false);
        rectTool.setVisible(false);
        selectedTool = swarmTool;
    }

    @FXML
    void onClick_SelectDeleteTool(MouseEvent event) {
        testAlert("Delete");
    }

    @FXML
    void onClick_StepBack(MouseEvent event) {
        testAlert("Step Back");
    }

    @FXML
    void onClick_StepForward(MouseEvent event) {
        testAlert("Step Forward");
    }

    @FXML
    void onClick_ZoomIn(MouseEvent event) {
        testAlert("Zoom In");
    }

    @FXML
    void onClick_ZoomOut(MouseEvent event) {
        testAlert("Zoom Out");
    }

    @FXML
    void onInput_Check(InputMethodEvent event) {
        testAlert("Check Input");
    }

    void testAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("This is a test");
        alert.setHeaderText("It's working");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onMove_HoverTool(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();

        swarmTool.setLayoutX(x);
        swarmTool.setLayoutY(y);

        circleTool.setLayoutX(x);
        circleTool.setLayoutY(y);

        rectTool.setLayoutX(x-rectTool.getWidth()/2);
        rectTool.setLayoutY(y-rectTool.getHeight()/2);
    }

    public void onSelectionChanged_EntitiesTab(Event event) {
        if(entitiesTab.isSelected())
            selectedTool.setVisible(true);
        else
            selectedTool.setVisible(false);
    }

    public void onClick_CreateCircle(MouseEvent mouseEvent) {
        testAlert("Create Circle");
    }

    public void onClick_CreateSwarm(MouseEvent mouseEvent) {
        Pair<Double,Double> rangeX = new Pair<>(swarmTool.getLayoutX() - swarmTool.getRadius(),
                swarmTool.getLayoutX() + swarmTool.getRadius()),

                rangeY = new Pair<>(swarmTool.getLayoutY() - swarmTool.getRadius(),
                        swarmTool.getLayoutY() + swarmTool.getRadius());

        try {
            guiEnvBuilder.createSwarm(rangeX, rangeY, 5,
                    Arrays.stream(new BotCommand[]{new Forever(), new Move(new PVector(1,1), 5), new Done(0)}).toList());
        } catch (Exception e) {
            testAlert("Error Creating Swarm :( :");
            System.out.println(e.getMessage());
        }
        guiEnvBuilder.getGuiEntities().forEach(guiEntity ->
        {simPane.getChildren().add(guiEntity.shape);});
        showEntities();
    }

    private void showEntities() {

    }

    public void onClick_CreateSquare(MouseEvent mouseEvent) {
        testAlert("Create Square");
    }
}
