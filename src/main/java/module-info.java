module com.example.planetsimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.planetsimulation to javafx.fxml;
    exports com.example.planetsimulation;
}