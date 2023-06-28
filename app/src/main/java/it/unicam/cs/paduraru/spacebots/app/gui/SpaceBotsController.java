package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.paduraru.engine.GameController;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Done;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Forever;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Move;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SpaceBotsController {

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

    SpaceBotsEnvironmentBuilder envBuilder;

    @FXML
    void initialize() {
        envBuilder = new SpaceBotsEnvironmentBuilder();

        GameController.addEnvironment(envBuilder.getEnvironment());
        GameController.setCurrentEnvironment(0);

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
        try {
            GameController.stepBackCurrentEnvironment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UpdateSimArea();
    }

    @FXML
    void onClick_StepForward(MouseEvent event) {
        try {
            GameController.stepForwardCurrentEnvironment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UpdateSimArea();
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
        Pair<Double,Double>
            rangeX =
                new Pair<>(swarmTool.getLayoutX() - swarmTool.getRadius(), swarmTool.getLayoutX() + swarmTool.getRadius()),
            rangeY =
                new Pair<>(swarmTool.getLayoutY() - swarmTool.getRadius(), swarmTool.getLayoutY() + swarmTool.getRadius());

        try {
            envBuilder.createSwarm(rangeX, rangeY, 5,
                    Arrays.stream(new BotCommand[]{new Forever(), new Move(new PVector(1,1), 5), new Done(0)}).toList());
        } catch (Exception e) {
            testAlert("Error Creating Swarm :( :");
            System.out.println(e.getMessage());
        }

        UpdateSimArea();

    }

    private void UpdateSimArea() {
        //I primi 3 sono i tool
        simPane.getChildren().remove(3, simPane.getChildren().size());
        simPane.getChildren().addAll(GameController.getEnvironment(GameController.getCurrentEnvironment()).getEntities().stream()
                .map(ent -> {
                    try {
                        return new Pair<PVector, Shape>(
                                ent.GetPosition(), GUIAppUtil.convertToFXShape (
                                ((cCollider)GameController.getEnvironment(GameController.getCurrentEnvironment())
                                .getComponentOf(ent, cCollider.class).get(0)).getShape())
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).map(pair ->{ pair.getSecond().setLayoutX(pair.getFirst().getX());
                    pair.getSecond().setLayoutY(pair.getFirst().getY());
                    return pair.getSecond();
                }).collect(Collectors.toList()) );
    }

    private void showEntities() {

    }

    public void onClick_CreateSquare(MouseEvent mouseEvent) {
        testAlert("Create Square");
    }
}
