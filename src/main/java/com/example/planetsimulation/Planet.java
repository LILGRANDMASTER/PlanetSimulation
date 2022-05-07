package com.example.planetsimulation;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Planet extends Pane {
    private Circle object;
    private double mass;
    private Vec2 pos; //текущая позиция
    private Vec2 velocity;
    public Vec2 acceleration; //ускорение
    public static Stage menuWindow = new Stage();


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

    public void move(){
        velocity.add(acceleration);//прибавляем к вектору скорости ускорение

        //устанавливаем позицию
        pos.add(velocity);
        setTranslateX(pos.x());
        setTranslateY(pos.y());
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
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
               menuWindow.show();
            }
        };
    }
}
