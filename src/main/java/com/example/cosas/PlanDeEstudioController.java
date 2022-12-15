package com.example.cosas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlanDeEstudioController {

    @FXML
    private Pane PaneM;
    @FXML
    private Button ButtonClose,ButtonMinimize,Programacion1,Matematica1,Algebra1,Quimica,Computacion1,Programacion2,Lineal,Fisica,Discreta,
    Computacion2,AyDA1,Arqui0,Matematica2,EyM,AyDA2,Comunicacion1,Probabilidad,Electronica,Ingles,Objetos,Estructura,Metodologia,Arqui1,Exploratoria,
    Datos,Lenguajes,Sistemas,Operativa,Arqui2,Informacion,Comunicacion2,Diferencial,Software,Compiladores,Ingenieria,Practicas,Tesis;
    private double x,y;
    Map<String,String> Correlativas;
    ArrayList<Button> buttons;
    public void init(Stage stage) {
        PaneM.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        PaneM.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        cargar();
        
        buttons = new ArrayList<>();
        buttons.add(Programacion1);buttons.add(Matematica1);buttons.add(Algebra1);buttons.add(Quimica);//1-1
        buttons.add(Computacion1);buttons.add(Programacion2);buttons.add(Lineal);buttons.add(Fisica);buttons.add(Discreta);//1-2
        buttons.add(Computacion2);buttons.add(AyDA1);buttons.add(Arqui0);buttons.add(Matematica2);buttons.add(EyM);//2-1
        buttons.add(AyDA2);buttons.add(Comunicacion1);buttons.add(Probabilidad);buttons.add(Electronica);buttons.add(Ingles);//2-2
        buttons.add(Objetos);buttons.add(Estructura);buttons.add(Metodologia);buttons.add(Arqui1);//3-1
        buttons.add(Exploratoria);buttons.add(Datos);buttons.add(Lenguajes);buttons.add(Sistemas);buttons.add(Operativa);//3-2
        buttons.add(Arqui2);buttons.add(Informacion);buttons.add(Comunicacion2);buttons.add(Diferencial);//4-1
        buttons.add(Software);buttons.add(Compiladores);//4-2
        buttons.add(Ingenieria);//5-1
        buttons.add(Practicas);buttons.add(Tesis);//5-2

        Correlativas = new HashMap<>();
        Correlativas.put("Programacion1","0 5 9 10 11 14 15 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Matematica1","1 7 12 13 16 17 20 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Algebra1","2 6 8 9 10 14 16 19 20 21 23 24 25 26 27 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Quimica","3 23 24 25 26 27 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Computacion1","4 9 10 14 19 20 21 23 24 25 26 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Programacion2","0 5 9 10 11 14 15 19 20 21 22 23 24 25 26 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Lineal","2 6 16 20 24 26 27 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Fisica","1 7 13 17 22 26 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Discreta","2 8 9 10 14 16 19 20 21 23 24 25 26 27 28 29 30 31 32 33 34 35 36");
        Correlativas.put("Computacion2","0 2 4 5 8 9 14 19 20 21 23 24 25 26 30 32 33 34 35 36");
        Correlativas.put("AyDA1","0 2 4 5 8 10 14 19 20 21 23 24 25 26 29 30 32 33 34 35 36");
        Correlativas.put("Arqui0","0 5 11 15 22 26 28 29 30 32 33 34 35 36");
        Correlativas.put("Matematica2","1 12 16 20 24 26 27 29 30 31 32 33 34 35 36");
        Correlativas.put("EyM","1 7 13 17 22 26 28 30 32 33 34 35 36");
        Correlativas.put("AyDA2","0 1 4 5 8 9 10 14 19 20 21 23 24 25 26 30 32 33 34 35 36");
        Correlativas.put("Comunicacion1","0 5 11 15 29 30 34 35 36");
        Correlativas.put("Probabilidad","1 2 6 8 12 16 20 24 26 27 29 30 32 34 35 36");
        Correlativas.put("Electronica","1 7 13 17 22 26 28 30 32 34 35 36");
        Correlativas.put("Ingles","18 34 35 36");
        Correlativas.put("Objetos","0 2 4 5 8 9 10 14 19 25 32 33 34 35 36");
        Correlativas.put("Estructura","0 1 2 4 5 6 8 9 10 12 14 16 20 24 26 30 32 34 35 36");
        Correlativas.put("Metodologia","0 2 4 5 8 9 10 14 21 24 32 34 35 36");
        Correlativas.put("Arqui1","0 1 5 7 11 13 17 22 26 28 30 32 34 35 36");
        Correlativas.put("Exploratoria","0 1 2 3 4 5 8 9 10 14 23");
        Correlativas.put("Datos","0 1 2 3 4 5 6 8 9 10 12 14 16 20 21 24 32 34");
        Correlativas.put("Lenguajes","0 1 2 3 4 5 8 9 10 14 19 25 33");
        Correlativas.put("Sistemas","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 16 17 20 22 26 30 32 34");
        Correlativas.put("Operativa","0 1 2 3 6 8 12 16 27");
        Correlativas.put("Arqui2","0 1 2 3 4 5 6 7 8 11 13 17 22 28");
        Correlativas.put("Informacion","0 1 2 3 4 5 6 7 8 10 11 12 15 16 29");
        Correlativas.put("Comunicacion2","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 20 22 26 30");
        Correlativas.put("Diferencial","0 1 2 3 4 5 6 7 8 12 31");
        Correlativas.put("Software","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 16 17 19 20 21 22 24 26 32 34");
        Correlativas.put("Compiladores","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 19 25 33");
        Correlativas.put("Ingenieria","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 24 26 32 34");
        Correlativas.put("Practicas","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 35");
        Correlativas.put("Tesis","0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 36");

    }

    public void SwitchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setY(stage.getScene().getWindow().getY()+150);
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
    @FXML
    public void Saber(MouseEvent event){
        String[] correlativa = Correlativas.get(((Button) event.getSource()).getId()).split(" ");
        int pos = buttons.indexOf(((Button) event.getSource()));
        for(String aux: correlativa){
            int i = Integer.parseInt(aux);
            if(buttons.get(i).getStyle().matches("(.*)#e34545(.*)|(.*)#00ffc8(.*)|(.*)#46fc46(.*)")){
                buttons.get(i).setStyle("-fx-background-color: #3B9FB1; ");
            } else {
                if (i < pos){
                    buttons.get(i).setStyle("-fx-background-color: #46fc46; ");
                }else if (i==pos){
                    buttons.get(i).setStyle("-fx-background-color: #00ffc8; ");
                }else
                    buttons.get(i).setStyle("-fx-background-color: #e34545; ");
            }
        }
    }

    public void cargar(){
        
    }

}
