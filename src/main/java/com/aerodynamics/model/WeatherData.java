package com.aerodynamics.model;

public class WeatherData {
    private String city;
    private double temperature;
    private String condition;
    private double windSpeed;
    private String windDirection;
    private double visibility;
    private String flightStatus;

    // Constructor
    public WeatherData(String city, double temperature, String condition,
                       double windSpeed, String windDirection, double visibility) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.visibility = visibility;
        this.flightStatus = calculateFlightStatus();
    }

    // Calculate flight status based on weather conditions
    private String calculateFlightStatus() {
        if (visibility >= 8 && windSpeed <= 20 && !condition.toLowerCase().contains("storm")) {
            return "GOOD";
        } else if (visibility >= 3 && windSpeed <= 35) {
            return "MARGINAL";
        } else {
            return "POOR";
        }
    }

    // Getters and Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        this.flightStatus = calculateFlightStatus();
    }

    public String getCondition() { return condition; }
    public void setCondition(String condition) {
        this.condition = condition;
        this.flightStatus = calculateFlightStatus();
    }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        this.flightStatus = calculateFlightStatus();
    }

    public String getWindDirection() { return windDirection; }
    public void setWindDirection(String windDirection) { this.windDirection = windDirection; }

    public double getVisibility() { return visibility; }
    public void setVisibility(double visibility) {
        this.visibility = visibility;
        this.flightStatus = calculateFlightStatus();
    }

    public String getFlightStatus() { return flightStatus; }

    @Override
    public String toString() {
        return String.format("WeatherData{city='%s', temp=%.1f°C, condition='%s', " +
                        "wind=%.1f km/h %s, visibility=%.1f km, flightStatus='%s'}",
                city, temperature, condition, windSpeed, windDirection,
                visibility, flightStatus);
    }
}