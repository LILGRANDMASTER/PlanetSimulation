package com.example.planetsimulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.planetsimulation.Vec2.vectorDifference;

public class Simulation extends Application {
    private static Pane gameRoot = new Pane();
    private static Pane applicationRoot = new Pane();
    private static ArrayList<Planet> planets = new ArrayList<>();

    public Parent createStartSystem(){
        Planet planet = new Planet(10, 100, new Vec2(400,400));
        planets.add(planet);
        gameRoot.getChildren().addAll(planet);
        gameRoot.setPrefSize(800,800);
        applicationRoot.getChildren().addAll(gameRoot);
        return applicationRoot;
    }

    //Обработчик добавляющий новую планету
    public EventHandler<MouseEvent> addPlanet(){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Не добавляем, если слишком близко к другой планете
                Vec2 pos = new Vec2(event.getX(), event.getY());
                for(Planet p: planets){
                    if(p.calculateDistance(pos) <= p.getRadius())
                        return;
                }

                Planet planet = new Planet(10, 1, new Vec2(2,0), new Vec2(event.getX(), event.getY()));

                planets.add(planet);
                gameRoot.getChildren().addAll(planet);
            }
        };
    }

    public void update(){

        //обнавляем ускорения планет
        for(Planet p1: planets){
            Vec2 newAcceleration = new Vec2(0.d,0.d);

            for(Planet p2: planets){
                if(p1 == p2) continue;

                //вычисляем направление вектора ускорения
               Vec2 duration = Vec2.normolize(Vec2.vectorDifference(p2.getPos(), p1.getPos()));

               //вычисляем модуль вектора ускорения
                double module = 10*p2.getMass()/Math.pow(p1.calculateDistance(p2),2);

                //Прибавляем к результирующему вектору ускорения
                if(p1.calculateDistance(p2) <= 20)
                    newAcceleration.add(duration.mul(-module));
                else
                    newAcceleration.add(duration.mul(module));

            }

            p1.setAcceleration(newAcceleration);
        }

        //Двигаем планеты
        for(Planet p : planets){
            p.move();
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createStartSystem());
        scene.setOnMouseClicked(addPlanet());


        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}