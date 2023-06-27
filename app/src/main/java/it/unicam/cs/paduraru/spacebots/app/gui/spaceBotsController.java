package it.unicam.cs.paduraru.spacebots.app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class spaceBotsController {

    Shape swarmTool = new Circle(50), circleTool = new Circle(50), squareTool = new Rectangle(50,50);
    Shape selectedTool = swarmTool;
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
        testAlert("Add Circle");
    }

    @FXML
    void onClick_SelectAddSquareTool(MouseEvent event) {
        testAlert("Add Square");
    }

    @FXML
    void onClick_SelectAddSwarmTool(MouseEvent event) {
        testAlert("Add Swarm");
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
        if(selectedTool.equals(swarmTool)){

        }
    }
}
