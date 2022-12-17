package com.example.cosas.Clases;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Conexion {
    private String letra;
    private ArrayList<Line> lineas;

    public Conexion(String l,double pos1x,double pos1y,double pos2x,double pos2y,Pane panel ){
        this.letra=l;
        this.lineas = new ArrayList<>();
        CrearRecorrido(pos1x,pos1y,pos2x,pos2y,panel);
    }

    public String getLetra() {
        return letra;
    }
    public void setLetra(String letra) {
        this.letra = letra;
    }

    public ArrayList<Line> getLineas() {
        return lineas;
    }
    public void setLineas(ArrayList<Line> lineas) {
        this.lineas = lineas;
    }

    private void CrearRecorrido(double pos1x, double pos1y, double pos2x, double pos2y,Pane panel){
        double posix = ((pos1x+pos2x)/2);
        double posiy = ((pos1y+pos2y)/2);

        Line conexion1 = new Line(pos1x, pos1y,posix,posiy);
        conexion1.setStrokeWidth(2);
        lineas.add(conexion1);
        Line conexion2 = new Line(posix,posiy,pos2x,pos2y);
        conexion2.setStrokeWidth(2);
        lineas.add(conexion2);

        Label l = new Label(letra);
        l.setLayoutX(posix+5);l.setLayoutY(posiy-20);

        Circle circulo = new Circle(posix, posiy, 5,Color.RED);
        circulo.setOnMouseDragged(event -> {
            circulo.setCenterX(event.getX());
            circulo.setCenterY(event.getY());
            l.setLayoutX(event.getX()+5);
            l.setLayoutY(event.getY()-20);
            conexion1.setEndX(event.getX());
            conexion1.setEndY(event.getY());
            conexion2.setStartX(event.getX());
            conexion2.setStartY(event.getY());
        });
        panel.getChildren().add(conexion1);
        panel.getChildren().add(conexion2);
        panel.getChildren().add(circulo);
        panel.getChildren().add(l);
    }
}
