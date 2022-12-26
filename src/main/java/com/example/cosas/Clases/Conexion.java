package com.example.cosas.Clases;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Conexion {
    private Node ini,fin;
    private Label letras;
    private ArrayList<Line> lineas;

    public Conexion(Node ini, Node fin, String letra,Pane panel){
        this.ini = ini;
        this.fin = fin;
        this.letras=new Label(letra);
        lineas = new ArrayList<>();
        Bounds ini1 = ini.localToScene(ini.getBoundsInLocal());
        Bounds fin1 = fin.localToScene(fin.getBoundsInLocal());
        CrearRecorrido(ini1.getMinX()+5, ini1.getMinY()-15, fin1.getMinX()+5, fin1.getMinY()-15, panel);
        ini.toFront();
        fin.toFront();
        
    }

    public boolean contains(Node node) {
        return (ini.equals(node) || fin.equals(node));
    }

    public boolean equal(Node ini,Node fin){
        if (this.ini.getId().equals(ini.getId()) && this.fin.getId().equals(fin.getId())){
            System.out.println("esta");
            return true;
        } else{
            return false;
        }
    }

    public void modifica(String l) {
        if(!letras.getText().contains(l)){
            letras.setText(letras.getText().concat(","+l));
        }
    }

    private void CrearRecorrido(double pos1x, double pos1y, double pos2x, double pos2y,Pane panel){
        if (pos1x != pos2x && pos1y !=pos2y){
            double posix = ((pos1x+pos2x)/2);
            double posiy = ((pos1y+pos2y)/2);

            Line conexion1 = new Line(pos1x, pos1y,posix,posiy);
            conexion1.setStrokeWidth(2);
            lineas.add(conexion1);
            Line conexion2 = new Line(posix,posiy,pos2x,pos2y);
            conexion2.setStrokeWidth(2);
            lineas.add(conexion2);

            letras.setLayoutX(posix+5);letras.setLayoutY(posiy-20);

            Circle circulo = new Circle(posix, posiy, 5,Color.RED);
            circulo.setStroke(Color.BLACK);
            circulo.setStrokeWidth(2);
            circulo.setOnMouseDragged(event -> {
                circulo.setCenterX(event.getX());
                circulo.setCenterY(event.getY());
                letras.setLayoutX(event.getX()+5);
                letras.setLayoutY(event.getY()-20);
                conexion1.setEndX(event.getX());
                conexion1.setEndY(event.getY());
                conexion2.setStartX(event.getX());
                conexion2.setStartY(event.getY());
            });
            panel.getChildren().add(conexion1);
            panel.getChildren().add(conexion2);
            panel.getChildren().add(circulo);
            panel.getChildren().add(letras);
            
        }
        else{
            Line conexionBIM = new Line(pos1x, pos1y,pos1x-30,pos1y-30);
            conexionBIM.setStrokeWidth(2);
            System.out.println(conexionBIM);
            lineas.add(conexionBIM);
            Line conexionMIA = new Line(pos1x-30, pos1y-30,pos1x,pos1y-60);
            conexionMIA.setStrokeWidth(2);
            lineas.add(conexionMIA);
            Line conexionBDM = new Line(pos1x, pos1y,pos1x+30,pos1y-30);
            conexionBDM.setStrokeWidth(2);
            lineas.add(conexionBDM);
            Line conexionMDA = new Line(pos1x+30,pos1y-30,pos1x,pos1y-60);
            conexionMDA.setStrokeWidth(2);
            lineas.add(conexionMDA);

            letras.setLayoutX(pos1x+5);letras.setLayoutY(pos1y-80);
             
            Circle circuloI = new Circle(pos1x-30, pos1y-30, 5,Color.RED);
            circuloI.setStroke(Color.BLACK);
            circuloI.setStrokeWidth(2);
            circuloI.setOnMouseDragged(event -> {
                circuloI.setCenterX(event.getX());
                circuloI.setCenterY(event.getY());
                conexionBIM.setEndX(event.getX());
                conexionBIM.setEndY(event.getY());
                conexionMIA.setStartX(event.getX());
                conexionMIA.setStartY(event.getY());
            });
            Circle circuloD = new Circle(pos1x+30, pos1y-30, 5,Color.RED);
            circuloD.setStroke(Color.BLACK);
            circuloD.setStrokeWidth(2);
            circuloD.setOnMouseDragged(event -> {
                circuloD.setCenterX(event.getX());
                circuloD.setCenterY(event.getY());
                conexionBDM.setEndX(event.getX());
                conexionBDM.setEndY(event.getY());
                conexionMDA.setStartX(event.getX());
                conexionMDA.setStartY(event.getY());
            });
            Circle circuloA = new Circle(pos1x, pos1y-60, 5,Color.RED);
            circuloA.setStroke(Color.BLACK);
            circuloA.setStrokeWidth(2);
            circuloA.setOnMouseDragged(event -> {
                circuloA.setCenterX(event.getX());
                circuloA.setCenterY(event.getY());
                letras.setLayoutX(event.getX()+5);
                letras.setLayoutY(event.getY()-20);
                conexionMIA.setEndX(event.getX());
                conexionMIA.setEndY(event.getY());
                conexionMDA.setEndX(event.getX());
                conexionMDA.setEndY(event.getY());
            });
            panel.getChildren().add(conexionBIM);
            panel.getChildren().add(conexionMIA);
            panel.getChildren().add(conexionBDM);
            panel.getChildren().add(conexionMDA);
            panel.getChildren().add(circuloA);
            panel.getChildren().add(circuloI);
            panel.getChildren().add(circuloD);
            panel.getChildren().add(letras);
        }
    }
}
