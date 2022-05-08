package com.example.planetsimulation;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;


public class Planet extends Pane {
    private Circle object;
    public double mass;
    private Vec2 pos; //текущая позиция
    private Vec2 velocity;
    private Vec2 acceleration; //ускорение
    private static Stage menuWindow = new Stage();
    private Scene menu;
    private Planet pl = this;


    public Planet(double R, double mass, Vec2 pos){
        object = new Circle(R, Color.RED);
        this.mass = mass;

        velocity = new Vec2(0.d, 0.d);
        acceleration = new Vec2(0.d, 0.d);

        //устанавливаем начальную позицию
        this.pos = pos;
        setTranslateX(pos.x());
        setTranslateY(pos.y());

        getChildren().addAll(object);

        setOnMouseClicked(openMenu());
    }


    public Planet(double R, double mass, Vec2 velocity, Vec2 pos){
        object = new Circle(R, Color.RED);
        this.mass = mass;

        this.velocity = velocity;
        acceleration = new Vec2(0.d, 0.d);

        //устанавливаем начальную позицию
        this.pos = pos;
        setTranslateX(pos.x());
        setTranslateY(pos.y());

        getChildren().addAll(object);

        setOnMouseClicked(openMenu());
    }

    private void makeMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(Planet.class.getResource("planet-menu.fxml"));
        menu = new Scene(loader.load());

    }

    public void move(){
        velocity.add(acceleration);//прибавляем к вектору скорости ускорение

        //устанавливаем позицию
        pos.add(velocity);
        setTranslateX(pos.x());
        setTranslateY(pos.y());
    }

    public void setParameters(double R, double mass, Color color){
        object.setRadius(R);
        this.mass = mass;
        object.setFill(color);
    }

    public double getMass(){
        return mass;
    }

    public Vec2 getPos(){
        return pos;
    }

    public double getRadius(){
        return object.getRadius();
    }

    public void setAcceleration(Vec2 acceleration){
        this.acceleration = acceleration;
    }

    //Расстояние до планеты p
    public double calculateDistance(Planet p){
        Vec2 pos1 = this.getPos();
        Vec2 pos2 = p.getPos();

        return Math.sqrt(Math.pow((pos1.x()-pos2.x()),2) + Math.pow((pos1.y()-pos2.y()),2));

    }

    public double calculateDistance(Vec2 pos2){
        Vec2 pos1 = this.getPos();

        return Math.sqrt(Math.pow((pos1.x()-pos2.x()),2) + Math.pow((pos1.y()-pos2.y()),2));
    }


    public EventHandler<MouseEvent> openMenu(){
        try{
            makeMenu();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }



        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){

                MenuController.planet = pl;
                menuWindow.setScene(menu);
                menuWindow.show();
            }
        };
    }



    /*//================FXML MENU CONTROLLS=====================
    @FXML
    private ChoiceBox<Color> planetColor;
    @FXML
    private Slider planetRadius;
    @FXML
    private TextField planetMass;


    @FXML
    protected void onAcceptButtonClicked() {
        object.setFill(planetColor.getValue());
        mass = Double.parseDouble(planetMass.getText());
        object.setRadius(planetRadius.getValue());
    }
    */
}
