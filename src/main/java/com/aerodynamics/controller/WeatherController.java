package com.aerodynamics.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class WeatherController implements Initializable {

    // FXML Injection for all UI components
    @FXML private Label companyTitle;
    @FXML private TextField cityField;
    @FXML private Button refreshButton;
    @FXML private Label temperatureLabel;
    @FXML private Label conditionLabel;
    @FXML private Polygon aircraftShape;
    @FXML private Label windLabel;
    @FXML private Label visibilityLabel;
    @FXML private Label flightStatusLabel;
    @FXML private HBox forecastContainer;

    // Forecast day labels
    @FXML private Label day1Label, day2Label, day3Label;
    @FXML private Label temp1Label, temp2Label, temp3Label;
    @FXML private Label cond1Label, cond2Label, cond3Label;

    private static final String API_KEY = "4d8fb5b93d4af21d66a2948710284366";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupBindings();
        setupEventHandlers();
        startAnimations();
        initializeData();
    }

    private void setupBindings() {
        // Disable refresh button if city field is empty
        refreshButton.disableProperty().bind(
                cityField.textProperty().isEmpty()
        );
    }

    private void setupEventHandlers() {
        // Refresh button action
        refreshButton.setOnAction(event -> {
            refreshWeatherData();
        });

        // City field enter key action
        cityField.setOnAction(event -> {
            if (!cityField.getText().trim().isEmpty()) {
                refreshWeatherData();
            }
        });
    }

    private void startAnimations() {
        // Aircraft rotation animation
        RotateTransition rotate = new RotateTransition(Duration.seconds(4), aircraftShape);
        rotate.setByAngle(5);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setAutoReverse(true);
        rotate.play();
    }

    private void initializeData() {
        // Set initial data
        companyTitle.setText("AERO DYNAMICS");
        temperatureLabel.setText("24°C");
        conditionLabel.setText("Clear Sky");
        windLabel.setText("15 km/h NW");
        visibilityLabel.setText("10 km");
        flightStatusLabel.setText("GOOD");
        flightStatusLabel.getStyleClass().add("flight-status-good");

        // Initialize forecast
        day1Label.setText("MON");
        day2Label.setText("TUE");
        day3Label.setText("WED");

        temp1Label.setText("22°C");
        temp2Label.setText("20°C");
        temp3Label.setText("19°C");

        cond1Label.setText("Sunny");
        cond2Label.setText("Cloudy");
        cond3Label.setText("Rain");
    }

    private void refreshWeatherData() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) return;

        new Thread(() -> {
            try {
                String urlString = String.format(API_URL, city, API_KEY);
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JsonNode jsonResponse = objectMapper.readTree(response.toString());
                    Platform.runLater(() -> updateUI(jsonResponse));
                } else {
                    Platform.runLater(() -> {
                        conditionLabel.setText("Error: City not found");
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    conditionLabel.setText("Error: Connection failed");
                });
            }
        }).start();
    }

    private void updateUI(JsonNode jsonResponse) {
        double temp = jsonResponse.get("main").get("temp").asDouble();
        String condition = jsonResponse.get("weather").get(0).get("main").asText();
        double windSpeed = jsonResponse.get("wind").get("speed").asDouble();
        int visibility = jsonResponse.has("visibility") ? jsonResponse.get("visibility").asInt() / 1000 : 10;

        temperatureLabel.setText(String.format("%.1f°C", temp));
        conditionLabel.setText(condition);
        windLabel.setText(String.format("%.1f km/h", windSpeed * 3.6)); // Convert m/s to km/h
        visibilityLabel.setText(String.format("%d km", visibility));

        updateFlightStatus(visibility, windSpeed * 3.6);
    }

    private void updateFlightStatus(int visibility, double windSpeedKmH) {
        flightStatusLabel.getStyleClass().removeAll(
                "flight-status-good",
                "flight-status-marginal",
                "flight-status-poor"
        );

        if (visibility > 8 && windSpeedKmH < 20) {
            flightStatusLabel.setText("GOOD");
            flightStatusLabel.getStyleClass().add("flight-status-good");
        } else if (visibility > 3 && windSpeedKmH < 40) {
            flightStatusLabel.setText("MARGINAL");
            flightStatusLabel.getStyleClass().add("flight-status-marginal");
        } else {
            flightStatusLabel.setText("POOR");
            flightStatusLabel.getStyleClass().add("flight-status-poor");
        }
    }
}
