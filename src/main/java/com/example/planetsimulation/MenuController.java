package com.example.planetsimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuController {
    ObservableList<String> planetColorList = FXCollections
            .observableArrayList("RED", "YELLOW", "GREEN", "BLUE");

    public static Planet planet;
    @FXML
    public ChoiceBox<String> planetColor;
    @FXML
    public Slider planetRadius;
    @FXML
    public TextField planetMass;

    @FXML
    private void initialize() {
        planetColor.setValue("RED");
        planetColor.setItems(planetColorList);


    }

    @FXML
    protected void onAcceptButtonClicked() {
        System.out.println(planetMass.getText());
        System.out.println(planetColor);
        System.out.println(planetRadius);
        System.out.println(planet);
        planet.mass = Double.parseDouble(planetMass.getText());

        planet.setColor(planetColor.getValue());
        planet.setRadius(planetRadius.getValue());
    }
}
