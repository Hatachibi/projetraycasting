package com.modules.service.outils;

public class Raycasting {


    private void computeRays() {

    }

    /**
     * Computes the rays originating from the Camera's position. Each ray starts as an "infinite" line segment that
     * spans up to RESOLUTION pixels. Its endpoint is then truncated to the nearest object that it intersects.
     */
    /*private void computeRays() {
        for (int r = 0; r < this.RESOLUTION; r++) {
            double newMin = this.CAMERA.getCurrentAngle() - this.CAMERA.getFov() / 2;
            double newMax = this.CAMERA.getCurrentAngle() + this.CAMERA.getFov() / 2;

            // Compute the angle of this ray, normalized to our FOV.
            double rayAngle = RaycasterUtils.normalize(r, 0, this.RESOLUTION, newMin, newMax);

            // Compute the coordinates of the end of this ray.
            double ex = this.CAMERA.getX() + RaycasterPanel.MAX_DIST * RaycasterUtils.cos(Math.toRadians(rayAngle));
            double ey = this.CAMERA.getY() + RaycasterPanel.MAX_DIST * RaycasterUtils.sin(Math.toRadians(rayAngle));

            // Construct the current ray object for later.
            this.RAY_LIST[r].setRayCoordinates(this.CAMERA.getX(), this.CAMERA.getY(), ex, ey);

            // Iterate through all objects in the plane and find collisions.
            Point2D.Double minPt = null;
            EntityData minData = null;
            double minDist = Integer.MAX_VALUE;
            for (CollidableEntity2D entity : this.MAP.getEntities()) {
                IntersectionDataPair ip = entity.intersectionPt(this.RAY_LIST[r].getLine());
                if (ip.getPoint() != null) {
                    double currMinDist = ip.getPoint().distance(this.CAMERA.getX(), this.CAMERA.getY());
                    if (currMinDist <= minDist) {
                        minDist = currMinDist;
                        minPt = ip.getPoint();
                        minData = ip.getData();
                    }
                }
            }

            // If we found a closest minima, assign it as the ray's end coordinate.
            this.RAY_LIST[r].setData(minData);
            this.RAY_LIST[r].setAngle(rayAngle);
            if (minPt != null) {
                double ca = RaycasterUtils.normalize(rayAngle, newMin, newMax, -this.CAMERA.getFov() / 2, this.CAMERA.getFov() / 2);
                this.RAY_LIST[r].setEndRayCoordinates(minPt.x, minPt.y);
                this.RAY_LIST[r].setDistance(minDist * RaycasterUtils.cos(Math.toRadians(ca)));
            } else {
                this.RAY_LIST[r].setDistance(Double.POSITIVE_INFINITY);
            }
        }
    }*/
}
