package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * @Author: Jafet
 * @Coauthor: David
 * @params: position - The position vector of the light source.
 * @params: color - The color of the light.
 * @params: intensity - The intensity of the light.
 * @return: None
 * Represents a point light source. Inherits from Light and calculates lighting effects
 * based on the dot product between light direction and surface normal.
 */
public class PointLight extends Light {
    public PointLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
    }

    /**
     * @Author: Jafet
     * @params: intersection - The intersection point at which to calculate the dot product.
     * @return: The dot product between light direction and surface normal at the intersection.
     * Calculates the dot product between the direction from the light source to the intersection
     * point and the surface normal at that point, ensuring the value is non-negative.
     */
    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(
                Vector3D.dotProduct(intersection.getNormal(),
                        Vector3D.normalize(Vector3D.substract(getPosition(), intersection.getPosition()))),
                0.0);
    }
}

