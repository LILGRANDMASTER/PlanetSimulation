package com.example.planetsimulation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuController {

    public static Planet planet;
    @FXML
    public ChoiceBox<Color> planetColor;
    @FXML
    public Slider planetRadius;
    @FXML
    public TextField planetMass;


    @FXML
    protected void onAcceptButtonClicked() {
        System.out.println(planetMass.getText());
        System.out.println(planetColor);
        System.out.println(planetRadius);
        System.out.println(planet);
        planet.mass = Double.parseDouble(planetMass.getText());
    }
}
