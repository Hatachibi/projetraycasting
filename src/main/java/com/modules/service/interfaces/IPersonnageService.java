package com.modules.service.interfaces;

import com.modules.service.outils.Vector2;

public interface IPersonnageService {

    void deplacement();

    void drawPlayer();

    void updatePlayer();

    void setDirection(Vector2 direction);

    Vector2 getPosition();

    void addAngle(float angle);

    float getAngle();

    float getMouseSpeed();

    void setMouseSpeed(float ms);
}
