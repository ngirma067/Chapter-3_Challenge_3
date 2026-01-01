module BrandedWeatherWidget {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.aerodynamics to javafx.fxml;
    opens com.aerodynamics.controller to javafx.fxml;
    exports com.aerodynamics;
    exports com.aerodynamics.controller;
}
