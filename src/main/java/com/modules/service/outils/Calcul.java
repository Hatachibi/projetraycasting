package com.modules.service.outils;

public class Calcul {

    public final static Double PI = Math.PI;

    public final static Double P2 = PI/2;

    public final static Double P3 = P2*3;

    public final static Double RADIAN = Math.PI/180;

    public static float rotate(float angle, float rotation){
        return Math.abs(angle + rotation) % 360;
    }

    public static Vector2 rotateVector(float angle, Vector2 position){
        return new Vector2(Math.cos(angle)*position.getX(),Math.sin(angle)*position.getY());
    }

}
