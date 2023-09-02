package com.modules.vue;

import com.modules.controller.PersonnageController;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.*;

public class Rendu {

    public final static Rendu INSTANCE = new Rendu();

    public static Rendu getInstance() {
        return INSTANCE;
    }

    public void init(long window) {
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glLoadIdentity();
        glOrtho(0, Fenetre.WidthFenetre, 0, Fenetre.HeigthFenetre, -1, 1);
    }

    public void drawPoint(float x, float y, int size) {
        glColor4f(1f, 1f, 1f, 1f);
        glPointSize(size);
        glBegin(GL_POINTS);
        glVertex2f(x, y);
        glEnd();
    }


    public void drawPlayer() {
        PersonnageController.getPersonnageController().drawPlayer();
    }

}
