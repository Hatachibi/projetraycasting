package com.modules.service;

import com.modules.service.interfaces.IPersonnageService;
import com.modules.service.outils.Vector2;
import com.modules.vue.Rendu;
import org.lwjgl.opengl.GL11;

public class PersonnageService implements IPersonnageService {
    private final double speed;
    protected Vector2 position;

    protected float angle;

    private Vector2 direction;
    private static final IPersonnageService personnageService = new PersonnageService();

    public PersonnageService() {
        position = new Vector2(200,200);
        direction = new Vector2();
        this.speed = 5.85;
        this.angle = 0;
    }

    public static IPersonnageService getPersonnageService() {
        return personnageService;
    }

    @Override
    public void deplacement() {
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        setPosition(positionAfterMoving);
        direction = new Vector2();
    }

    private Vector2 getNormalizedDirection() {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(speed);
        return normalizedVector;
    }

    private void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void drawPlayer() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Rendu.getInstance().drawPoint((float) position.getX(), (float) position.getY(), 20);
    }

    public void addAngle(float angle) {
        float tmp = this.angle + angle*10;
        if(tmp >= 360 || tmp <=0){
            this.angle = 360;
        } else {
            this.angle += angle*10;
        }
    }

    public float getAngle() {
        return angle;
    }
}
