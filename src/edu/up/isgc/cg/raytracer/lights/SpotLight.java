package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * @Author: David
 * @params: position - The position vector of the light source.
 * @params: color - The color of the light.
 * @params: intensity - The intensity of the light.
 * @params: cutoffAngle - The angle defining the cone of illumination.
 * @params: direction - The direction vector of the spotlight.
 * Represents a spotlight source. That generates a cone like light to the lights group that we have.
 * Also implements little diffuse in the projection on any surface.
 */
public class SpotLight extends Light {
    private double cutoffAngle;
    private Vector3D direction;

    /**
     * @Author: David
     * @params: None
     * @return: The cutoff angle of the spotlight.
     * Retrieves the cutoff angle of the spotlight cone.
     */
    public double getCutoffAngle() {
        return cutoffAngle;
    }

    /**
     * @Author: David
     * @params: cutoffAngle - The cutoff angle to set.
     * @return: None
     * Sets the cutoff angle of the spotlight cone.
     */
    public void setCutoffAngle(double cutoffAngle) {
        this.cutoffAngle = cutoffAngle;
    }

    /**
     * @Author: David
     * @params: None
     * @return: The direction vector of the spotlight.
     * Retrieves the direction vector of the spotlight.
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * @Author: David
     * @params: direction - The direction vector to set.
     * @return: None
     * Sets the direction vector of the spotlight.
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    /**
     * @Author: David
     * @params: position - The position vector of the spotlight.
     * @params: color - The color of the spotlight.
     * @params: intensity - The intensity of the spotlight.
     * @params: cutoffAngle - The angle defining the cone of illumination.
     * @params: direction - The direction vector of the spotlight.
     * @return: None
     * Constructs a spotlight with the specified parameters.
     */
    public SpotLight(Vector3D position, Color color, double intensity, double cutoffAngle, Vector3D direction) {
        super(position, color, intensity);
        setCutoffAngle(cutoffAngle);
        setDirection(direction);
    }

    /**
     * @Author: David
     * @params: intersection - The intersection point at which to calculate the dot product.
     * @return: The dot product between light direction and surface normal at the intersection.
     * Calculates the dot product between the direction from the spotlight to the intersection
     * point and the surface normal at that point, with added functionality for spotlight
     * direction and cutoff angle.
     */
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
