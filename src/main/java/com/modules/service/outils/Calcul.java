package com.modules.service.outils;

import com.modules.vue.Fenetre;

public class Calcul {

    public final static Double PI = Math.PI;

    public final static Double P2 = PI/2;

    public final static Double P3 = P2*3;

    public final static Double RADIAN = Math.PI/180;

    public static float rotateDegree(float angle, double rotation){
        return Math.abs(angle + (float)rotation) % 360;
    }

    public static float normalizeAngle(float angle){
        if(angle<0) angle+=2*Calcul.PI;
        if(angle>2*Calcul.PI) angle-=2*Calcul.PI;
        return angle;
    }

    public static Vector2 rotateVector(float angle, Vector2 position) {
        angle = (float) Math.toRadians(angle);
        float x = (float) position.getX();
        float y = (float) position.getY();
        float newX = x * (float) Math.cos(angle) - y * (float) Math.sin(angle);
        float newY = x * (float) Math.sin(angle) + y * (float) Math.cos(angle);
        return new Vector2(newX, newY);
    }

    public static int[][] integrate(int[] tab){
        int length = (int) Math.sqrt(tab.length);
        int[][] res = new int[length][length];
        int decalage = 0;
        for(int i = 0; i<tab.length; i++) {
            if(tab[i] == 1) {
                res[i % length][decalage] = tab[i];
            }
            if(i != 0 && i % length == 0) {
                decalage++;
            }
        }
        return res;
    }


}
