package com.example.cosas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.IOException;


public class AlgoController {

    @FXML
    private Pane PaneM,PanelPrincipal;
    @FXML
    private Button ButtonClose,ButtonMinimize;
    @FXML
    private Label LabelConexion;
    private double x,y,p1x,p1y;
    private boolean p1;
    private Node link1;
    
    public void init(Stage stage){
        PaneM.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        PaneM.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
        p1=false;
    }

    public void SwitchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setY(stage.getScene().getWindow().getY());
        stage.setResizable(false);
        stage.show();
    }

    public void Close() {
        Stage stage = (Stage) ButtonClose.getScene().getWindow();
        stage.close();
    }

    public void Minimize() {
        Stage stage = (Stage) ButtonMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void administrarnodos(MouseEvent event){
        boolean encontrado = false;
        for (Node node : PanelPrincipal.getChildren()){
            if (node.contains(event.getX(),event.getY())){
                link1 = node;
                encontrado=true;
                if(event.getButton() == MouseButton.SECONDARY){
                    PanelPrincipal.getChildren().remove(node);
                }
                break;
            }
        }   
        if(event.getButton() == MouseButton.PRIMARY){
            if (!encontrado){
                Circle circle = new Circle(event.getX(), event.getY(), 15, Color.ORANGE);
                PanelPrincipal.getChildren().add(circle);
            } else {
                Bounds lugar = link1.localToScene(link1.getBoundsInLocal());
                if (!p1){
                    p1x=lugar.getMinX()+5;
                    p1y=lugar.getMinY()-15;
                    p1=true;
                    LabelConexion.setVisible(true);
                } else {
                    Line conexion = new Line(p1x, p1y,lugar.getMinX()+5,lugar.getMinY()-15);
                    PanelPrincipal.getChildren().add(conexion);
                    p1=false;
                    link1=null;
                    LabelConexion.setVisible(false);
                }
            }
        } else {
            if (encontrado)
                PanelPrincipal.getChildren().remove(link1);
            p1=false;
            LabelConexion.setVisible(false);
        }
    }
}
