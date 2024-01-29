package com.modules.controller;

import com.modules.controller.api.PersonnageApi;
import com.modules.service.PersonnageService;
import com.modules.service.interfaces.IPersonnageService;
import com.modules.service.outils.Calcul;
import com.modules.service.outils.Vector2;
import com.modules.vue.Fenetre;
import com.modules.vue.Rendu;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import static org.lwjgl.glfw.GLFW.*;

public class PersonnageController implements PersonnageApi {
    private final int[] listeInput = {GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_D};
    private static final PersonnageApi personnageController = new PersonnageController();

    //Enable rotation
    private double lastMouseX = 0;

    @Override
    public void deplacement() {
        for (Integer key : listeInput) {
            checkDeplacement(Fenetre.getInstance().getWindow(), key);
        }
        glfwSetCursorPosCallback(Fenetre.getInstance().getWindow(), new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                onMouseMove(window, xpos);
            }
        });
    }

    public void checkDeplacement(long window, int key) {
        if (glfwGetKey(window, key) == GLFW_PRESS) {
            getAWSDkeys(window);
        }
        if (glfwGetKey(window, key) == GLFW_RELEASE) {
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
            //rotateRight(PersonnageService.getPersonnageService().getMouseSpeed());
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            //rotateLeft(PersonnageService.getPersonnageService().getMouseSpeed());
            moveLeft();
            glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
        }
    }

    private void onMouseMove(long window, double xpos) {
        if (xpos > lastMouseX) {
            rotateLeft(PersonnageService.getPersonnageService().getMouseSpeed());
        } else {
            rotateRight(PersonnageService.getPersonnageService().getMouseSpeed());
        }
        lastMouseX = xpos;
    }

    private void rotateLeft(float angle) {
        PersonnageService.getPersonnageService().addAngle(angle);
    }

    private void rotateRight(float angle) {
        PersonnageService.getPersonnageService().addAngle(-angle);
    }

    private void moveRight() {
        float angle = PersonnageService.getPersonnageService().getAngle();
        angle = (float) Math.toRadians(Calcul.rotateDegree(angle, 90));
        Vector2 direction = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
        PersonnageService.getPersonnageService().setDirection(direction);
        //PersonnageServicePersonnageService.getPersonnageService().setDirection(new Vector2(-1, 0));
    }

    private void moveLeft() {
        float angle = PersonnageService.getPersonnageService().getAngle();
        angle = (float) Math.toRadians(Calcul.rotateDegree(angle, 270));
        Vector2 direction = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
        PersonnageService.getPersonnageService().setDirection(direction);
        //PersonnageService.getPersonnageService().setDirection(new Vector2(1,0));
    }

    private void moveDown() {//
        float angle = PersonnageService.getPersonnageService().getAngle();
        angle = (float)Math.toRadians(Calcul.rotateDegree(angle, 180));
        Vector2 direction = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
        PersonnageService.getPersonnageService().setDirection(direction);
        // PersonnageService.getPersonnageService().setDirection(new Vector2(0,-1));
    }

    private void moveUp() {
        float angle = (float) Math.toRadians(PersonnageService.getPersonnageService().getAngle());
        Vector2 direction = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
        PersonnageService.getPersonnageService().setDirection(direction);
        //PersonnageService.getPersonnageService().setDirection(Calcul.rotateVector(PersonnageService.getPersonnageService().getAngle(),new Vector2(0,1)));}
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

    public void updatePlayer(){ PersonnageService.getPersonnageService().updatePlayer();}
}
