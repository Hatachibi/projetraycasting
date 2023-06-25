package com.modules.jeu;

import com.modules.vue.Fenetre;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Fenetre.getInstance().init();
        Fenetre.getInstance().create();
        Fenetre.getInstance().run();
    }

}