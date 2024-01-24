package com.modules.service.outils;

import com.modules.service.PersonnageService;
import com.modules.vue.Fenetre;
import com.modules.vue.Rendu;

public class Raycasting {

    public final int NUMBER_RAYS = 20;
    public final float LENGTH_RAY = 200;
    public final float FOV = 180;

    private final static Raycasting INSTANCE = new Raycasting();

    public static Raycasting getInstance() {
        return INSTANCE;
    }

    public void computeRays() {
        Vector2 playerPosition = PersonnageService.getPersonnageService().getPosition();
        float angle = PersonnageService.getPersonnageService().getAngle();

        for (float i = -FOV / 2; i <= FOV / 2; i += FOV / NUMBER_RAYS) {
            // Rotation du vecteur (i degrés) autour de l'origine
            Vector2 finishPosition = Calcul.rotateVector(i + angle, new Vector2(playerPosition.getX(), 0));

            // Coordonnées du point de départ
            float x1 = (float) playerPosition.getX();
            float y1 = (float) playerPosition.getY();

            finishPosition.euclidianNormalize(LENGTH_RAY);
            // Coordonnées du point d'arrêt (après la rotation)
            float x2 = (float) (playerPosition.getX() + finishPosition.getX());
            float y2 = (float) (playerPosition.getY() + finishPosition.getY());

            // Dessiner le vecteur
            Rendu.getInstance().drawTrait(x1, y1, x2, y2, 1);
        }
    }

    public static float dist(float ax, float ay, float bx, float by) {
        return (float) (Math.sqrt((bx - ax) * (bx - ax) + (by - ay) * (by - ay)));
    }

    public static float calculMur(float ra, float a, int[] map, PersonnageService personnageService) {
        int x = (int) (personnageService.getPosition().getX() / Fenetre.WidthFenetre);
        int y = (int) (personnageService.getPosition().getY() / Fenetre.HeigthFenetre);

        return ra;
    }


}
