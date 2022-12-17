package com.example.cosas.Clases;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Conexiones {
    private Node ini,fin;
    private ArrayList<Conexion> transiciones;

    public Conexiones(Node ini, Node fin, String letra,Pane panel){
        this.ini = ini;
        this.fin = fin;
        this.transiciones = new ArrayList<>();

        Bounds ini1 = ini.localToScene(ini.getBoundsInLocal());
        Bounds fin1 = fin.localToScene(fin.getBoundsInLocal());
        Conexion aux = new Conexion(letra, ini1.getMinX()+5, ini1.getMinY()-15,fin1.getMinX()+5, fin1.getMinY()-15, panel);
        transiciones.add(aux);
    }

    public Node getIni() {
        return ini;
    }

    public void setIni(Node ini) {
        this.ini = ini;
    }

    public Node getFin() {
        return fin;
    }

    public void setFin(Node fin) {
        this.fin = fin;
    }
    
}
