package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.lights.Light;

import java.awt.*;

public abstract class Object3D implements IIntersectable {
    private Color color;
    private Vector3D position;
    private double shininess;
    private double reflectivity;
    private boolean isRefractive;

    /**
     * @Author: Jafet
     * @Coauthor: David
     * @params: position - The position vector of the object.
     * @params: color - The color of the object.
     * @params: shininess - The shininess of the object.
     * @params: reflectivity - The reflectivity of the object.
     * @params: isRefractive - Whether the object is refractive or not.
     * Constructs a 3D object with the specified parameters.
     */
    public Object3D(Vector3D position, Color color, Double shininess, Double reflectivity, boolean isRefractive) {
        setPosition(position);
        setColor(color);
        setShininess(shininess);
        setReflectivity(reflectivity);
        setRefractive(isRefractive);
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: Whether the object is refractive or not.
     * Checks if the object is refractive.
     */
    public boolean isRefractive() {
        return isRefractive;
    }

    /**
     * @Author: Jafet
     * @params: refractive - Whether the object is refractive or not.
     * @return: None
     * Sets whether the object is refractive or not.
     */
    public void setRefractive(boolean refractive) {
        isRefractive = refractive;
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The reflectivity of the object.
     * Retrieves the reflectivity of the object.
     */
    public double getReflectivity() {
        return reflectivity;
    }

    /**
     * @Author: Jafet
     * @params: reflectivity - The reflectivity to set.
     * @return: None
     * Sets the reflectivity of the object.
     */
    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The shininess of the object.
     * Retrieves the shininess of the object.
     */
    public double getShininess() {
        return shininess;
    }

    /**
     * @Author: Jafet
     * @params: shininess - The shininess to set.
     * @return: None
     * Sets the shininess of the object.
     */
    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The color of the object.
     * Retrieves the color of the object.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @Author: Jafet
     * @params: color - The color to set.
     * @return: None
     * Sets the color of the object.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The position vector of the object.
     * Retrieves the position vector of the object.
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * @Author: Jafet
     * @params: position - The position vector to set.
     * @return: None
     * Sets the position vector of the object.
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }
}

