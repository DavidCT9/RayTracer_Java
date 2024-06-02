package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

public class SpotLight extends Light {
    private double cutoffAngle;
    private Vector3D direction;

    public double getCutoffAngle() {
        return cutoffAngle;
    }

    public void setCutoffAngle(double cutoffAngle) {
        this.cutoffAngle = cutoffAngle;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    public SpotLight(Vector3D position, Color color, double intensity, double cutoffAngle, Vector3D direction) {
        super(position, color, intensity);
        setCutoffAngle(cutoffAngle);
        setDirection(direction);
    }

    @Override
    public double getNDotL(Intersection intersection) {
        Vector3D lightDir = Vector3D.substract(getPosition(), intersection.getPosition());
        Vector3D spotDir = getDirection();
        double phi = Math.cos(Math.toRadians(getCutoffAngle()));
        double theta = Vector3D.dotProduct(Vector3D.normalize(Vector3D.scalarMultiplication(lightDir, -1)), Vector3D.normalize(spotDir));

        //Soften Spotlight circumference
        double outerCutoff = Math.cos(Math.toRadians(getCutoffAngle()*1.25));
        double epsilon = phi - outerCutoff;
        double relativeIntensity = Math.clamp( (theta-outerCutoff) / epsilon, 0.0, 1.0);


        if (theta > outerCutoff) {
            return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)) * relativeIntensity, 0.0);
        } else {
            return 0;
        }

    }
}
