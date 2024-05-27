package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.lights.Light;

import java.awt.*;

public abstract class Object3D implements IIntersectable{
    private Color color;
    private Vector3D position;
    private double shininess;
    private double reflectivity;
    private boolean isRefractive;

    public Object3D(Vector3D position, Color color, Double shininess, Double reflectivity, boolean isRefractive) {
        setPosition(position);
        setColor(color);
        setShininess(shininess);
        setReflectivity(reflectivity);
        setRefractive(isRefractive);
    }

    public boolean isRefractive() {
        return isRefractive;
    }

    public void setRefractive(boolean refractive) {
        isRefractive = refractive;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

}
