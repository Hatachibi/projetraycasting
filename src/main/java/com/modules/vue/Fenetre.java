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

    public void run() {
        while(!Fenetre.getInstance().isClosed()) {
            glfwPollEvents();
        }
        glfwTerminate();
    }

}
