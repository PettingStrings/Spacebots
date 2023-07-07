package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.paduraru.engine.GameController;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.*;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
import it.unicam.cs.paduraru.spacebots.app.environmentsParsers.SpaceBotsEnvironmentHandler;
import it.unicam.cs.paduraru.spacebots.app.environmentsParsers.SpaceBotsEnvironmentParser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class SpaceBotsGUIController {

    //region Injected GUI
    @FXML
    AnchorPane simPane, mainPane;
    @FXML
    public Circle swarmTool, circleTool;
    @FXML
    public Rectangle rectTool;

    @FXML
    Tab entitiesTab;
    @FXML
    Label lblSteps;
    @FXML
    TextField txtSteps;
    //endregion

    int simulationStep = 0;
    int stepNum = 1;
    private final File defaultPath = new File(getClass().getClassLoader().getResource("environments").getPath());
    Shape selectedTool;
    SpaceBotsEnvironmentBuilder envBuilder;
    SpaceBotsEnvironmentParser envParser;

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
        envParser = new SpaceBotsEnvironmentParser(new SpaceBotsEnvironmentHandler());
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
        selectedTool.toFront();
    }

    @FXML
    void onClick_SelectAddSquareTool(MouseEvent event) {
        swarmTool.setVisible(false);
        circleTool.setVisible(false);
        rectTool.setVisible(true);
        selectedTool = rectTool;
        selectedTool.toFront();
    }

    @FXML
    void onClick_SelectAddSwarmTool(MouseEvent event) {
        swarmTool.setVisible(true);
        circleTool.setVisible(false);
        rectTool.setVisible(false);
        selectedTool = swarmTool;
        selectedTool.toFront();
    }

    @FXML
    void onClick_SelectDeleteTool(MouseEvent event) {
        testAlert("Delete");
    }

    @FXML
    void onClick_StepBack(MouseEvent event) {
        if(simulationStep == 0) return;

        if(simulationStep < stepNum){
            GameController.stepBackCurrentEnvironment(simulationStep);
            simulationStep = 0;
        }else {
            GameController.stepBackCurrentEnvironment(stepNum);
            simulationStep -= stepNum;
        }

        envBuilder.setEnvironment(GameController.getEnvironment(GameController.getCurrentEnvironment()));

        updateSimArea();
    }

    @FXML
    void onClick_StepForward(MouseEvent event) {
        simulationStep+=stepNum;
        try {
            GameController.stepForwardCurrentEnvironment(stepNum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        updateSimArea();
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
        selectedTool.setVisible(entitiesTab.isSelected());
    }

    public void onClick_CreateCircle(MouseEvent mouseEvent) throws Exception {
        PVector origin = new PVector(circleTool.getLayoutX(), circleTool.getLayoutY());
        envBuilder.addLabelledArea(new PAreaLabel(origin, new PLabel("prova")), new PCircle(20));
        updateSimArea();
        selectedTool.toFront();
    }

    public void onClick_CreateSwarm(MouseEvent mouseEvent) {
        Pair<Double,Double>
            rangeX =
                new Pair<>(swarmTool.getLayoutX() - swarmTool.getRadius(), swarmTool.getLayoutX() + swarmTool.getRadius()),
            rangeY =
                new Pair<>(swarmTool.getLayoutY() - swarmTool.getRadius(), swarmTool.getLayoutY() + swarmTool.getRadius());
        Until loop = new Until(new PLabel("prova"));
        loop.setDoneIp(2);
        try {
            envBuilder.createSwarm(rangeX, rangeY, 5, envParser.getProgram());
        } catch (Exception e) {
            testAlert("Error Creating Swarm :( :");
            System.out.println(e.getMessage());
        }
        updateSimArea();
        selectedTool.toFront();
    }

    private void updateSimArea() {
        lblSteps.setText(String.valueOf(simulationStep));
        simPane.getChildren().removeAll(simPane.getChildren().stream().filter(node -> node.getId() == null).toList());
        simPane.getChildren().addAll(GameController.getEnvironment(GameController.getCurrentEnvironment()).getEntities().stream()
                .map(GUIAppUtil::generateFxShape)
                .toList());
    }

    public void onClick_CreateRect(MouseEvent mouseEvent) {
        PVector origin = new PVector(rectTool.getLayoutX() + rectTool.getWidth()/2,
                rectTool.getLayoutY() + rectTool.getHeight()/2);
        envBuilder.addLabelledArea(new PAreaLabel(origin, new PLabel("prova")), new PRectangle(rectTool.getWidth(), rectTool.getHeight()));
        updateSimArea();
        selectedTool.toFront();
    }

    public void onClick_Shapes(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(defaultPath);
        fileChooser.setTitle("Open GOL File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Shape File", "*.txt"),
                new FileChooser.ExtensionFilter("Shape File", "*.sbs"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(((Node)mouseEvent.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                envParser.parseEnvironment(selectedFile);
                envBuilder.setEnvironment(envParser.getEnvironment());
                GameController.setCurrentEnvironment(envBuilder.getEnvironment());
                updateSimArea();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error...");
                alert.setHeaderText(e.getMessage());
            } catch (FollowMeParserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error...");
                alert.setHeaderText(e.getMessage());
            }
        }
    }

    public void onCLick_LoadCommands(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(defaultPath);
        fileChooser.setTitle("Open GOL File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Program File", "*.txt"),
                new FileChooser.ExtensionFilter("Program File", "*.sbp"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(((Node)mouseEvent.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                envParser.parseProgram(selectedFile);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error...");
                alert.setHeaderText(e.getMessage());
            } catch (FollowMeParserException e) {
                System.out.println("Error loading program");
            }
        }
    }

    public void onTyped_txtSteps(KeyEvent keyEvent) {
        try {
             stepNum = Integer.parseInt(txtSteps.getCharacters().toString());
        }catch (NumberFormatException e){
            testAlert("Not a number");
        }
    }
}
