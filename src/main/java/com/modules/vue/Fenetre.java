package com.modules.vue;

import com.modules.service.outils.ConstantesInfos;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Fenetre {

    public final static Fenetre INSTANCE = new Fenetre();
    public final static Integer HeigthFenetre = ConstantesInfos.NB_WIDTH_TILES*65;
    public final static Integer	WidthFenetre = ConstantesInfos.NB_HEIGHT_TILES*65;

    public final static Integer FPS = 60;

    private long window;

    public static Fenetre getInstance() {
        return INSTANCE;
    }

    public void init() {
        if(!glfwInit())
        {
            throw new IllegalStateException("Failed to initialize GLFW");
        }
        this.window = glfwCreateWindow(WidthFenetre, HeigthFenetre, "Harry Potter et la Chambre des secrets", 0, 0);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        Rendu.getInstance().init(window);
    }

    public long getWindow()
    {
        return window;
    }

    public void create() {
        if(window == 0)
        {
            throw new IllegalStateException("Failed to initialize window");

        }
        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - Fenetre.WidthFenetre) / 2, (videoMode.height() - Fenetre.HeigthFenetre) /2);
        glfwShowWindow(window);
    }

    public boolean isClosed() {
        return glfwWindowShouldClose(window);
    }

    public static double getTime() {
        return (double)System.nanoTime() / (double)1000000000L;
    }

    public void run() {

        double timer = getTime(); //Initialisation du timer
        double unprocessed = 0; //Initialisation de l'accumulateur
        double frames = 0; //Initialisation du nombre de frames
        double frameTime = 0; //Initialisation pour affichage des FPS
        boolean updateAndRender = false;

        while(!Fenetre.getInstance().isClosed()) {

            /**
             * Partie qui cap le jeu à un nombre de FPS constant (par défaut 60 FPS)
             */
            double timerLoop = getTime();

            unprocessed += timerLoop - timer;
            frameTime += timerLoop - timer;
            timer = timerLoop;

            if(unprocessed >= 1.0/FPS){
                unprocessed = 0;
                updateAndRender = true;
                glfwPollEvents();
                if(frameTime >= 1.0){
                    System.out.println("FPS : "+frames);
                    frames = 0;
                    frameTime = 0;
                    //TODO reset le nombre de tick ici
                }
            }

            /**
             * Partie qui update et affiche le jeu toutes les X 1/FPS (par défaut 60 FPS)
             */
            if(updateAndRender){

                //TODO update

                //TODO render
                
                //TODO tick++

                frames++;
                updateAndRender = false;
                glfwSwapBuffers(window);
            }

        }
        glfwTerminate();
    }

}
