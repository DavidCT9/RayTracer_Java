package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.objects.Object3D;

public class Intersection {

    private double distance;
    private Vector3D position;
    private Vector3D normal;
    private Object3D object;

    /**
     * @Author: Jafet
     * @params: position - The position vector of the intersection point.
     * @params: distance - The distance from the ray origin to the intersection point.
     * @params: normal - The normal vector at the intersection point.
     * @params: object - The object intersected by the ray.
     * Initializes an Intersection object with the specified position, distance, normal, and intersected object.
     */
    public Intersection(Vector3D position, double distance, Vector3D normal, Object3D object) {
        setPosition(position);
        setDistance(distance);
        setNormal(normal);
        setObject(object);
    }

    /**
     * @Author: Jafet
     * @return: The distance from the ray origin to the intersection point.
     * Retrieves the distance from the ray origin to the intersection point.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @Author: Jafet
     * @params: distance - The distance from the ray origin to the intersection point.
     * Sets the distance from the ray origin to the intersection point.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @Author: Jafet
     * @return: The position vector of the intersection point.
     * Retrieves the position vector of the intersection point.
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * @Author: Jafet
     * @params: position - The position vector of the intersection point.
     * Sets the position vector of the intersection point.
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * @Author: Jafet
     * @return: The normal vector at the intersection point.
     * Retrieves the normal vector at the intersection point.
     */
    public Vector3D getNormal() {
        return normal;
    }

    /**
     * @Author: Jafet
     * @params: normal - The normal vector at the intersection point.
     * Sets the normal vector at the intersection point.
     */
    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    /**
     * @Author: Jafet
     * @return: The object intersected by the ray.
     * Retrieves the object intersected by the ray.
     */
    public Object3D getObject() {
        return object;
    }

    /**
     * @Author: Jafet
     * @params: object - The object intersected by the ray.
     * Sets the object intersected by the ray.
     */
    public void setObject(Object3D object) {
        this.object = object;
    }
}
