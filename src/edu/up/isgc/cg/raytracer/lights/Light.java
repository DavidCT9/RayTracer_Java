package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Object3D;

import java.awt.*;



public abstract class Light extends Object3D {
    private double intensity;

    /**
     * @Author: Jafet
     * @Coauthor: David
     * @params: position - The position vector of the light source.
     * @params: color - The color of the light.
     * @params: intensity - The intensity of the light.
     * Represents a generic light source. Inherits from Object3D and provides an abstract
     * method for calculating the dot product between light direction and surface normal.
     */
    public Light(Vector3D position, Color color, double intensity) {
        super(position, color, 10.0, 0.0, false);
        setIntensity(intensity);
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The intensity of the light.
     * Retrieves the intensity of the light.
     */

    public double getIntensity() {
        return intensity;
    }

    /**
     * @Author: Jafet
     * @params: intensity - The intensity to set.
     * @return: None
     * Sets the intensity of the light.
     */
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * @Author: Jafet
     * @params: intersection - The intersection point at which to calculate the dot product.
     * @return: The dot product between light direction and surface normal at the intersection.
     * Provides an abstract method for subclasses to implement, calculating the dot product
     * between light direction and surface normal at the specified intersection.
     */
    public abstract double getNDotL(Intersection intersection);

    /**
     * @Author: Jafet
     * @params: ray - The ray to find intersection with.
     * @return: An Intersection object representing the intersection point, if any.
     * Returns a default Intersection object, representing no intersection.
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}


