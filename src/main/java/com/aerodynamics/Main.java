package com.aerodynamics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/weather_widget.fxml"));
        Parent root = loader.load();

        // Increased size for full text visibility
        Scene scene = new Scene(root, 550, 550);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        // Configure stage
        primaryStage.setTitle("Aero Dynamics - Aviation Weather Widget");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(550);
        primaryStage.setMinHeight(550);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}