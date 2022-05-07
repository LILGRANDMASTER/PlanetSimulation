package com.example.planetsimulation;

public class Vec2 {
    private double x;
    private double y;

    public Vec2(double x, double y){
        this.x = x;
        this.y = y;
    }

    //Разность векторов
    static Vec2 vectorDifference(Vec2 p1, Vec2 p2){
        return new Vec2(p1.x() - p2.x(), p1.y() - p2.y());
    }

    //Нормирование вектора
    static Vec2 normolize(Vec2 p){
        return new Vec2(p.x()/p.module(), p.y()/p.module());
    }

    //длина вектора
    public double module(){
        return Math.sqrt(x*x + y*y);
    }

    //умножение вектора на число
    public Vec2 mul(double k){
        x *= k;
        y *= k;
        return this;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double y(){
        return y;
    }

    public double x(){
        return x;
    }

    //сложение с вектором b
    public void add(Vec2 b){
        x += b.x;
        y += b.y;
    }

}
