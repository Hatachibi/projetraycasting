package com.modules.controller;

import com.modules.controller.api.PersonnageApi;
import com.modules.service.PersonnageService;
import com.modules.service.interfaces.IPersonnageService;
import com.modules.service.outils.Vector2;
import com.modules.vue.Fenetre;
import com.modules.vue.Rendu;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class PersonnageController implements PersonnageApi {
    private final int[] listeInput = {GLFW.GLFW_KEY_W,GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_D};
    private static final PersonnageApi personnageController = new PersonnageController();

    @Override
    public void deplacement()
    {
        for(Integer key:listeInput)
        {
            checkDeplacement(Fenetre.getInstance().getWindow(), key);
        }
    }
    public void checkDeplacement(long window, int key)
    {
        if(glfwGetKey(window, key) == GLFW_PRESS)
        {
            getAWSDkeys(window);
        }
        if(glfwGetKey(window, key) == GLFW_RELEASE)
        {
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_FALSE);
        }
    }

    private void getAWSDkeys(long window) {
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            moveUp();
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            moveDown();
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
        }
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            moveRight();
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            moveLeft();
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
        }
        drawPlayer();
    }

    private void moveLeft() {
        PersonnageService.getPersonnageService().getPosition().addX(-1);
    }

    private void moveRight() {
        PersonnageService.getPersonnageService().getPosition().addX(1);
    }

    private void moveDown() {
        PersonnageService.getPersonnageService().getPosition().addY(-1);
    }

    private void moveUp() {
        PersonnageService.getPersonnageService().getPosition().addY(1);
    }

    public static PersonnageApi getPersonnageController() {
        return personnageController;
    }

    @Override
    public void subitDegats(int dommage) {

    }

    public void drawPlayer() {
        PersonnageService.getPersonnageService().drawPlayer();
    }
}
