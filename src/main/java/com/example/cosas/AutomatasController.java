package com.example.cosas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import com.example.cosas.Clases.Conexiones;

public class AutomatasController {

    @FXML
    private Pane PaneM,PanelPrincipal;
    @FXML
    private Button ButtonClose,ButtonMinimize;
    @FXML
    private Label LabelConexion;
    private double x,y;
    private Node nodo1,nodo2;
    private ArrayList<Conexiones> conexiones;

    public void init(Stage stage){
        PaneM.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        PaneM.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
        PanelPrincipal.setOnMouseClicked(event -> {
            administrarnodos(event);
        });
        nodo1=null;nodo2=null;
        conexiones = new ArrayList<>();
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
                    if(nodo1==null)
                        nodo1 = node;
                    else 
                        nodo2 = node;
                    encontrado=true;
                    if(event.getButton() == MouseButton.SECONDARY){
                        Circle aux = new Circle();
                        if (node.getClass() == aux.getClass())
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
                Circle circle = new Circle();
                if (nodo1.getClass() == circle.getClass()){
                    if (nodo2==null){
                        LabelConexion.setVisible(true);
                    } else if (nodo2 != null){
                        if (nodo2.getClass() == circle.getClass()){
                            Conexiones aux = new Conexiones(nodo1, nodo2, "a", PanelPrincipal);
                            conexiones.add(aux);
                            nodo1=null;
                            nodo2=null;
                            LabelConexion.setVisible(false);
                        } else {
                            nodo2=null;
                        }
                    }
                } else {
                    nodo1=null;
                }
            }
        } else {
            nodo1=null;
            nodo2=null;
            LabelConexion.setVisible(false);
        }
    }
}
