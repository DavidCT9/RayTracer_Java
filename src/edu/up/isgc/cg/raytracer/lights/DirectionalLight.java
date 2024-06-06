package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

public class DirectionalLight extends Light {
    private Vector3D direction;


    /**
     * @Author: Jafet
     * @Coauthor: David
     * @params: direction - The direction vector of the light source.
     * @params: color - The color of the light.
     * @params: intensity - The intensity of the light.
     * Represents a directional light source. Inherits color and intensity from Light class.
     * Computes lighting effects using dot product between light direction and surface normal.
     */
    public DirectionalLight(Vector3D direction, Color color, double intensity) {
        super(Vector3D.ZERO(), color, intensity);
        setDirection(direction);
    }

    /**
     * @Author: Jafet
     * @return: The direction vector of the light source.
     * Retrieves the direction vector of the light.
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * @Author: Jafet
     * @params: direction - The direction vector to set for the light source.
     * Sets and normalizes the direction vector of the light source.
     */
    public void setDirection(Vector3D direction) {
        this.direction = Vector3D.normalize(direction);
    }

    /**
     * @Author: Jafet
     * @params: intersection - The intersection point to calculate the dot product.
     * @return: The dot product between light direction and surface normal at the intersection.
     * Calculates dot product between light direction and surface normal at intersection,
     * ensuring the value is non-negative.
     */
    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)), 0.0);
    }

}
