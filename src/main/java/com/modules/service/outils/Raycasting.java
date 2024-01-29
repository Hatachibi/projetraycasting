package com.modules.service.outils;

import com.modules.entity.Monde;
import com.modules.service.MondeService;
import com.modules.service.PersonnageService;
import com.modules.service.outils.Calcul;
import com.modules.service.outils.ConstantesInfos;
import com.modules.vue.Fenetre;
import com.modules.vue.Rendu;

import java.util.ArrayList;
import java.util.List;

public class Raycasting {
    public final int NUMBER_RAYS = 60;
    public final float FOV = 30;

    public final int pixelPerRay = 16;

    private final static Raycasting INSTANCE = new Raycasting();

    public static Raycasting getInstance() {
        return INSTANCE;
    }

    public void computeRays() {

        /*Intialize variable*/
        int stopX,stopY,stopPoint;
        float rayX,rayY,rayAngle,x0,y0,distT;
        rayX=0;rayY=0;x0=0;y0=0;distT=0;

        /*Initialize player variable*/
        float playerAngle = (float) Math.toRadians(PersonnageService.getPersonnageService().getAngle());
        float playerX = (float) PersonnageService.getPersonnageService().getPosition().getX();
        float playerY = (float) PersonnageService.getPersonnageService().getPosition().getY();

        /*Intialize ray*/
        rayAngle = Calcul.normalizeAngle((float) (playerAngle-Calcul.RADIAN*FOV));

        /*For each ray calcul the distance*/
        for(int r = 0; r<NUMBER_RAYS; r++){
            /*
                Check Horizontal
            */

            /*Initialize some variable*/
            float maxDepth=0;
            float disH = 10000000;float hx=playerX;float hy=playerY;
            float aTan = (float) (-1 / Math.tan(rayAngle));

            if(rayAngle > Calcul.PI) {rayY= (float) ((((int)playerY>>6)<<6)-0.0001); rayX = (playerY-rayY)*aTan+playerX; y0 = -ConstantesInfos.TAILLE_CARRE; x0=-y0*aTan;} // looking up
            if(rayAngle < Calcul.PI) {rayY= (float) ((((int)playerY>>6)<<6)+ConstantesInfos.TAILLE_CARRE); rayX = (playerY-rayY)*aTan+playerX; y0 = ConstantesInfos.TAILLE_CARRE; x0=-y0*aTan;} // looking down
            if(rayAngle == 0 || rayAngle == Calcul.PI){rayX=playerX;rayY=playerY;maxDepth=Monde.mapY;}
            while (maxDepth < Monde.mapY){
                stopX=(int)(rayX)>>6; stopY=(int)(rayY)>>6; stopPoint=stopY*Monde.mapX+stopX;
                if(stopPoint>0 && stopPoint<Monde.mapX*Monde.mapY && Monde.baseMap[stopPoint] == 1){hx=rayX;hy=rayY;disH = dist(playerX,playerY,hx,hy,rayAngle);maxDepth=Monde.mapY;}
                else {rayX+=x0;rayY+=y0;maxDepth+=1;}
            }

            /*
                Check Vertical
            */

            /*Initialize some variable*/
            maxDepth=0;
            float disV = 10000000;float vx=playerX;float vy=playerY;
            float nTan = (float) (-Math.tan(rayAngle));

            if(rayAngle > Calcul.P2 && rayAngle <Calcul.P3) {rayX= (float) ((((int)playerX>>6)<<6)-0.0001); rayY = (playerX-rayX)*nTan+playerY; x0 = -ConstantesInfos.TAILLE_CARRE; y0=-x0*nTan;} // looking left
            if(rayAngle < Calcul.P2 || rayAngle > Calcul.P3) {rayX= (float) ((((int)playerX>>6)<<6)+ConstantesInfos.TAILLE_CARRE); rayY = (playerX-rayX)*nTan+playerY; x0 = ConstantesInfos.TAILLE_CARRE; y0=-x0*nTan;} // looking right
            if(rayAngle == 0 || rayAngle == Calcul.PI){rayX=playerX;rayY=playerY;maxDepth=Monde.mapX;}
            while (maxDepth < Monde.mapX){
                stopX=(int)(rayX)>>6; stopY=(int)(rayY)>>6; stopPoint=stopY*Monde.mapX+stopX;
                if(stopPoint>0 && stopPoint<Monde.mapX*Monde.mapY && Monde.baseMap[stopPoint] == 1){vx=rayX;vy=rayY;disV = dist(playerX,playerY,vx,vy,rayAngle);maxDepth=Monde.mapX;}
                else {rayX+=x0;rayY+=y0;maxDepth+=1;}
            }

            /*
                Check whether vertical or horizontal line
            */

            if(disV<disH){rayX=vx;rayY=vy;distT = disV;
                Rendu.getInstance().setColor(0.9f,0,0);
            }
            if(disH<disV){rayX=hx;rayY=hy;distT = disH;
                Rendu.getInstance().setColor(0.7f,0,0);
            }

            /*Fix fisheye effect*/
            float ca=Calcul.normalizeAngle(playerAngle-rayAngle);
            distT= (float) (distT*Math.cos(ca));

            int test = 700;

            float lineH = (ConstantesInfos.TAILLE_CARRE*test)/distT; if(lineH>test){lineH=test;}
            float lineOff = test/2-lineH/2;

            /*Render*/
            //Rendu.getInstance().drawTrait(r*pixelPerRay+50,lineOff+120,r*pixelPerRay+50,lineH+lineOff+120,pixelPerRay);
            Rendu.getInstance().drawTrait(playerX,playerY,rayX,rayY,1);
            Rendu.getInstance().resetColor();

            /*Increment Ray*/
            rayAngle += Calcul.normalizeAngle((float) (Calcul.RADIAN+0));
        }
    }

    public static float dist(float ax, float ay, float bx, float by,float angle) {
        return (float) (Math.sqrt((bx - ax) * (bx - ax) + (by - ay) * (by - ay)));
    }

    public static float calculMur(float ra, float a, int[] map, PersonnageService personnageService) {
        int x = (int) (personnageService.getPosition().getX() / Fenetre.WidthFenetre);
        int y = (int) (personnageService.getPosition().getY() / Fenetre.HeigthFenetre);

        return ra;
    }


}
