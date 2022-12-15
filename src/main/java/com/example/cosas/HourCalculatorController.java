package com.example.cosas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HourCalculatorController {

    @FXML
    private TextField TextFieldHora;
    @FXML
    private Label LabelError;
    @FXML
    private Label LaberTotalTime;
    @FXML
    private Pane PaneM;
    @FXML
    private Long TotalTime = 0L;
    @FXML
    private ListView ListTimes;
    @FXML
    private Button ButtonClose,ButtonMinimize;
    @FXML
    private ToggleButton ButtonV4,ButtonV3,ButtonV2,ButtonV1,ButtonV0;

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

    public void SwitchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void AddHours(){
        Pattern pattern1 = Pattern.compile("^(((\\d\\d|\\d):(\\d|0\\d|[0-5]\\d):(\\d|0\\d|[0-5]\\d))|((\\d|0\\d|[0-5]\\d):(\\d|0\\d|[0-5]\\d)))"); // (1:1:20 or 1:0)
        Matcher matcher1 = pattern1.matcher(TextFieldHora.getText());

        Pattern pattern2 = Pattern.compile("^(\\d*[hms]( |))*"); // (1:1:20 or 1:0)
        Matcher matcher2 = pattern2.matcher(TextFieldHora.getText());

        if (matcher1.matches() || matcher2.matches()) {

            Duration t = Duration.ZERO;
            if (matcher1.matches()){
                LabelError.setVisible(false);
                String[] formato = TextFieldHora.getText().split(":");

                if (formato.length == 3){
                    t=Duration.ofHours(Integer.parseInt(formato[0])).plusMinutes(Integer.parseInt(formato[1])).plusSeconds(Integer.parseInt(formato[2]));
                } else{
                    t=Duration.ofHours(0).plusMinutes(Integer.parseInt(formato[0])).plusSeconds(Integer.parseInt(formato[1]));
                }
            } else if (matcher2.matches()){
                String[] formato = TextFieldHora.getText().split(" ");
                for(String i: formato) {
                    if (i.matches("(.*)h(.*)")) {
                        i = i.substring(0,i.length()-1);
                        long hora = Long.parseLong(i);
                        t=t.plusHours(hora);
                    } else if (i.matches("(.*)m(.*)")) {

                        i = i.substring(0,i.length()-1);
                        long hora = (Long.parseLong(i) / 60);
                        long minuto = Long.parseLong(i) % 60;
                        t=t.plusHours(hora).plusMinutes(minuto);
                    } else {
                        i = i.substring(0,i.length()-1);
                        long hora = (Long.parseLong(i))/3600;
                        long minuto = ((Long.parseLong(i)) % 3600)/ 60;
                        long segundos = Long.parseLong(i) % 60;
                        t=t.plusHours(hora).plusMinutes(minuto).plusSeconds(segundos);
                    }
                }
            }
            long time = t.toSeconds();
            String hora = String.valueOf((Integer.parseInt(String.valueOf(time))/3600));
            String minuto= String.valueOf((Integer.parseInt(String.valueOf(time)) % 3600) / 60);
            String segundos= String.valueOf(Integer.parseInt(String.valueOf(time)) % 60);

            if (hora.length() == 1){hora= "0"+hora;}
            if (minuto.length() == 1){minuto= "0"+minuto;}
            if (segundos.length() == 1){segundos= "0"+segundos;}

            ObservableList<Object> horas = FXCollections.observableArrayList();
            horas.addAll(ListTimes.getItems());
            horas.add(hora+":"+minuto+":"+segundos);

            ListTimes.setItems(horas);

            TotalTime = TotalTime + t.toSeconds();
            TextFieldHora.setText("");
            LaberTotalTime.setText( String.format("%d:%02d:%02d", TotalTime / 3600, (TotalTime % 3600) / 60, (TotalTime % 60)));
        } else
            LabelError.setVisible(true);
    }

    public void RemoveHours(){
        int i = ListTimes.getSelectionModel().getSelectedIndex(); //obtengo la posicion del item a borrar

        String r = (String) ListTimes.getSelectionModel().getSelectedItem(); //obtengo el item a borrar
        String[] Hora = r.split(":"); //creo el objeto duracion para despues restarlo del total

        Duration t = Duration.ofHours(Integer.parseInt(Hora[0])).plusMinutes(Integer.parseInt(Hora[1])).plusSeconds(Integer.parseInt(Hora[2]));

        ListTimes.getItems().remove(i); //borro el elemento

        TotalTime = TotalTime - t.toSeconds(); //lo resto del total

        LaberTotalTime.setText( String.format("%d:%02d:%02d", TotalTime / 3600, (TotalTime % 3600) / 60, (TotalTime % 60)));
    }

    public void Close() {
        Stage stage = (Stage) ButtonClose.getScene().getWindow();
        stage.close();
    }

    public void Minimize() {
        Stage stage = (Stage) ButtonMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void VelocityChanger(ActionEvent event){
        float i = Float.parseFloat(((ToggleButton) event.getSource()).getText());
        long aux = (long) (TotalTime/i);
        LaberTotalTime.setText( String.format("%d:%02d:%02d", aux / 3600, (aux % 3600) / 60, (aux % 60)));
        ButtonV0.setSelected(false);
        ButtonV2.setSelected(false);
        ButtonV3.setSelected(false);
        ButtonV4.setSelected(false);
    }


}
