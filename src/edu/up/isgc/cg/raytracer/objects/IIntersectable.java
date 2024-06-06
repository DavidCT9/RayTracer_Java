package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;

/**
 * @Author: Jafet
 * Represents an object that can be intersected by a ray.
 */
public interface IIntersectable {
    /**
     * @Author: Jafet
     * Calculates the intersection of the object with the given ray.
     *
     * @param ray - The ray to find intersection with.
     * @return An Intersection object representing the intersection point, if any.
     */
    public abstract Intersection getIntersection(Ray ray);
}
