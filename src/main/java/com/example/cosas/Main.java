package com.example.cosas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setTitle("Cosas Menu");
            ((MainController)loader.getController()).init(stage);
            stage.show();
    }

    public static void main(String[] args) {launch(args);}
}
