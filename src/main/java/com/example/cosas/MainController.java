package com.example.cosas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;


public class MainController {
    @FXML
    private Button ButtonClose,ButtonMinimize;
    @FXML
    private Pane PaneM;

    private double x,y;


    public void init(Stage stage) {
        PaneM.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        PaneM.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

    }
    public void Close(ActionEvent event) {
        Stage stage = (Stage) ButtonClose.getScene().getWindow();
        stage.close();
    }

    public void Minimize(ActionEvent event) {
        Stage stage = (Stage) ButtonMinimize.getScene().getWindow();
        stage.setIconified(true);
    }



    public void SwitchToHourCalculator(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HourCalculator.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        ((HourCalculatorController)loader.getController()).init(stage);
        stage.show();

    }

    public void SwitchToPlanEstudio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Algo.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setY(stage.getScene().getWindow().getY());
        ((AlgoController)loader.getController()).init(stage);
        stage.show();

    }
}
