package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.tools.Barycentric;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a 3D model composed of triangles.
 */
public class Model3D extends Object3D {
    private List<Triangle> triangles;

    /**
     * @Author: Jafet
     * @Coauthor: David
     * @params: position - The position vector of the model.
     * @params: triangles - An array of triangles composing the model.
     * @params: color - The color of the model.
     * @params: shininess - The shininess of the model.
     * @params: reflectivity - The reflectivity of the model.
     * @params: isRefractive - Whether the model is refractive or not.
     * Constructs a 3D model with the specified parameters.
     */
    public Model3D(Vector3D position, Triangle[] triangles, Color color, double shininess, double reflectivity, boolean isRefractive) {
        super(position, color, shininess, reflectivity, isRefractive);
        setTriangles(triangles);
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The list of triangles composing the model.
     * Retrieves the list of triangles composing the model.
     */
    public List<Triangle> getTriangles() {
        return triangles;
    }

    /**
     * @Author: Jafet
     * @params: triangles - An array of triangles to set.
     * @return: None
     * Sets the triangles composing the model.
     */
    public void setTriangles(Triangle[] triangles) {
        Vector3D position = getPosition();
        Set<Vector3D> uniqueVertices = new HashSet<>();
        for (Triangle triangle : triangles) {
            uniqueVertices.addAll(Arrays.asList(triangle.getVertices()));
        }

        for (Vector3D vertex : uniqueVertices) {
            vertex.setX(vertex.getX() + position.getX());
            vertex.setY(vertex.getY() + position.getY());
            vertex.setZ(vertex.getZ() + position.getZ());
        }
        this.triangles = Arrays.asList(triangles);
    }


    /**
     * @Author: Jafet
     * @params: ray - The ray to find intersection with.
     * @return: An Intersection object representing the intersection point, if any.
     * Calculates the intersection of the model with the given ray.
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        double distance = -1;
        Vector3D position = Vector3D.ZERO();
        Vector3D normal = Vector3D.ZERO();

        for (Triangle triangle : getTriangles()) {
            Intersection intersection = triangle.getIntersection(ray);
            double intersectionDistance = intersection.getDistance();
            if (intersectionDistance > 0 &&
                    (intersectionDistance < distance || distance < 0)) {
                distance = intersectionDistance;
                position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
                normal = Vector3D.ZERO();
                double[] uVw = Barycentric.CalculateBarycentricCoordinates(position, triangle);
                Vector3D[] normals = triangle.getNormals();
                for (int i = 0; i < uVw.length; i++) {
                    normal = Vector3D.add(normal, Vector3D.scalarMultiplication(normals[i], uVw[i]));
                }
            }
        }

        if (distance == -1) {
            return null;
        }

        return new Intersection(position, distance, normal, this);
    }
}
