package com.modules.service;

import com.modules.service.interfaces.IPersonnageService;
import com.modules.service.outils.Vector2;
import com.modules.vue.Rendu;

public class PersonnageService implements IPersonnageService {
    private final double speed;
    protected Vector2 position;
    private Vector2 direction;
    private static final IPersonnageService personnageService = new PersonnageService();

    public PersonnageService() {
        position = new Vector2(0,0);
        direction = new Vector2();
        this.speed = 5.85;
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

    private Vector2 getPosition() {
        return position;
    }

    public void drawPlayer() {
        Rendu.getInstance().drawPoint((float) position.getX(), (float) position.getY(), 20);
    }
}
