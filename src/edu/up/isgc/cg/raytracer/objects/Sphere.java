package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

public class Sphere extends Object3D {
    private double radius;

    /**
     * @Author: Jafet
     * @Coauthor: David
     * @params: position - The position vector of the sphere.
     * @params: radius - The radius of the sphere.
     * @params: color - The color of the sphere.
     * @params: shininess - The shininess of the sphere.
     * @params: reflectivity - The reflectivity of the sphere.
     * @params: isRefractive - Whether the sphere is refractive or not.
     * Constructs a sphere with the specified parameters.
     */
    public Sphere(Vector3D position, double radius, Color color, double shininess, double reflectivity, boolean isRefractive) {
        super(position, color, shininess, reflectivity, isRefractive);
        setRadius(radius);
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The radius of the sphere.
     * Retrieves the radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @Author: Jafet
     * @params: radius - The radius to set.
     * @return: None
     * Sets the radius of the sphere.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @Author: Jafet
     * @params: ray - The ray to find intersection with.
     * @return: An Intersection object representing the intersection point, if any.
     * Calculates the intersection of the sphere with the given ray.
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        Vector3D L = Vector3D.substract(getPosition(), ray.getOrigin());
        double tca = Vector3D.dotProduct(L, ray.getDirection());
        double L2 = Math.pow(Vector3D.magnitude(L), 2);
        double d2 = L2 - Math.pow(tca, 2);
        if (d2 >= 0) {
            double d = Math.sqrt(d2);
            double t0 = tca - Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));
            double t1 = tca + Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));

            double distance = Math.min(t0, t1);
            Vector3D position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
            Vector3D normal = Vector3D.normalize(Vector3D.substract(position, getPosition()));
            return new Intersection(position, distance, normal, this);
        }

        return null;
    }
}
