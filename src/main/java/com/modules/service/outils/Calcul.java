package com.modules.service.outils;

public class Calcul {

    public final static Double PI = Math.PI;

    public final static Double P2 = PI/2;

    public final static Double P3 = P2*3;

    public final static Double RADIAN = Math.PI/180;

    public static float rotate(float angle, float rotation){
        return Math.abs(angle + rotation) % 360;
    }

    public static Vector2 rotateVector(float angle, Vector2 position) {
        angle = (float) Math.toRadians(angle);
        float x = (float) position.getX();
        float y = (float) position.getY();
        float newX = x * (float) Math.cos(angle) - y * (float) Math.sin(angle);
        float newY = x * (float) Math.sin(angle) + y * (float) Math.cos(angle);
        return new Vector2(newX, newY);
    }

}
