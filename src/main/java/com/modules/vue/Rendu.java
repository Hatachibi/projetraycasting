package com.modules.vue;

import com.modules.controller.PersonnageController;
import com.modules.entity.Monde;
import com.modules.service.outils.ConstantesInfos;
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
        glOrtho(0, Fenetre.WidthFenetre, Fenetre.HeigthFenetre,0, -1, 1);
    }

    public void drawPoint(float x, float y, int size) {
        //glColor4f(1f, 1f, 1f, 1f);
        glPointSize(size);
        glBegin(GL_POINTS);
        glVertex2f(x, y);
        glEnd();
    }

    public void setColor(float r,float g,float b){
        glColor4f(r,g,b,1f);
    }

    public void resetColor(){
        glColor4f(1f,1f,1f,1f);
    }

    public void drawTrait(float x1, float y1, float x2, float y2, float width) {
        glLineWidth(width);
        glBegin(GL_LINES);
        glVertex2f(x1, y1);
        glVertex2f(x2, y2);
        glEnd();
    }

    public void drawWorld(int[] monde) {
        float largeur = ConstantesInfos.TAILLE_CARRE;
        float hauteur = ConstantesInfos.TAILLE_CARRE;
        float x0 = 0f;
        float y0 = 0f;
        for(int i = 0; i< Monde.mapX; i++) {
            for(int j = 0; j<Monde.mapY; j++) {
                if(monde[i*Monde.mapX+j]==1){
                    x0 = j*largeur;
                    y0 = i*hauteur;
                    drawSquare(x0,y0,x0,y0+hauteur,x0+largeur,y0+hauteur,x0+largeur,y0);
                }
            }
            /*if(monde[i] == 1) {
                drawSquareMap(x + (largeur * (i % 8)), y + (hauteur * decalage), largeur, hauteur);
            }*/
        }
    }

    public void drawSquareMap(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x, y + height);
        glVertex2f(x + width, y + height);
        glVertex2f(x + width, y);
        glEnd();
    }

    public void drawSquare(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        //glColor4f(1f, 1f, 1f, 1f);
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
