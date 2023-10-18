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

    public void drawTrait(float x1, float y1, float x2, float y2, float width) {
        glLineWidth(width);
        glBegin(GL_LINES);
        glVertex2f(x1, y1);
        glVertex2f(x2, y2);
        glEnd();
    }

    public void drawWorld(int[] monde) {
        float largeur = (float) Fenetre.WidthFenetre /10;
        float hauteur = (float) Fenetre.HeigthFenetre /10;
        float x = 0f;
        float y = 0f;
        int decalage = 0;
        for(int i = 0; i<monde.length; i++) {
            if(monde[i] == 1) {
                drawSquareMap(x+(largeur*(i%10)),y+(hauteur*decalage),largeur, hauteur);
                if(i != 0 && i%10 == 0) {
                    decalage++;
                }
            }
        }
    }

    public void drawSquareMap(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
        glEnd();
    }

    public void drawSquare(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        glColor4f(1f, 1f, 1f, 1f);
        glBegin(GL_QUADS);
        glVertex2f(x1, y1);
        glVertex2f(x2, y2);
        glVertex2f(x3, y3);
        glVertex2f(x4, y4);
        glEnd();
    }


    public void drawPlayer() {
        PersonnageController.getPersonnageController().drawPlayer();
    }

}
