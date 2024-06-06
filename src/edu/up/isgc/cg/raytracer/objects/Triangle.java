package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

/**
 * @Author: Jafet
 * @params: v0 - The first vertex of the triangle.
 * @params: v1 - The second vertex of the triangle.
 * @params: v2 - The third vertex of the triangle.
 * @return: None
 * Initializes a Triangle object with given vertices and null normals.
 */
public class Triangle implements IIntersectable {
    public static final double EPSILON = 0.0000000000001;
    private Vector3D[] vertices;
    private Vector3D[] normals;


    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2) {
        setVertices(v0, v1, v2);
        setNormals(null);
    }

    /**
     * @Author: Jafet
     * @params: vertices - An array containing the vertices of the triangle.
     *          normals - An array containing the normals of the triangle.
     * @return: None
     * Initializes a Triangle object with given vertices and normals.
     */
    public Triangle(Vector3D[] vertices, Vector3D[] normals) {
        if(vertices.length == 3){
            setVertices(vertices[0], vertices[1], vertices[2]);
        } else {
            setVertices(Vector3D.ZERO(),Vector3D.ZERO(),Vector3D.ZERO());
        }
        setNormals(normals);
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: An array containing the vertices of the triangle.
     * Retrieves the vertices of the triangle.
     */
    public Vector3D[] getVertices() {
        return vertices;
    }

    /**
     * @Author: Jafet
     * @params: vertices: array of vertices, to assign it to the triangle.
     * @return: None
     * Sets the vertices of the triangle.
     */
    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }

    /**
     * @Author: Jafet
     * @params: v0 - The first vertex of the triangle.
     *          v1 - The second vertex of the triangle.
     *          v2 - The third vertex of the triangle.
     * @return: None
     * Sets the vertices of the triangle.
     */
    public void setVertices(Vector3D v0, Vector3D v1, Vector3D v2) {
        setVertices(new Vector3D[]{v0, v1, v2});
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: The normal vector of the triangle.
     * Retrieves the normal vector of the triangle.
     * Calculating the barycentric normal coordinates of the vertex array.
     */
    public Vector3D getNormal(){
        Vector3D normal = Vector3D.ZERO();
        Vector3D[] normals = this.normals;

        if(normals ==null) {
            Vector3D[] vertices = getVertices();
            Vector3D v = Vector3D.substract(vertices[1], vertices[0]);
            Vector3D w = Vector3D.substract(vertices[0], vertices[2]);
            normal = Vector3D.normalize(Vector3D.crossProduct(v, w));
        } else{
            for(int i = 0; i < normals.length; i++){
                normal.setX(normal.getX() + normals[i].getX());
                normal.setY(normal.getY() + normals[i].getY());
                normal.setZ(normal.getZ() + normals[i].getZ());
            }
            normal.setX(normal.getX() / normals.length);
            normal.setY(normal.getY() / normals.length);
            normal.setZ(normal.getZ() / normals.length);
        }
        return normal;
    }

    /**
     * @Author: Jafet
     * @params: None
     * @return: An array containing the normal vectors of the triangle.
     * Retrieves the normal vectors of the triangle.
     */
    public Vector3D[] getNormals() {
        if(normals == null) {
            Vector3D normal = getNormal();
            setNormals(new Vector3D[]{normal, normal, normal});
        }
        return normals;
    }

    /**
     * @Author: Unknown
     * @params: normals: array of normals
     * @return: None
     * Sets the normal vectors of the triangle.
     */
    private void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }

    /**
     * @Author: Unknown
     * @params: vn0 - The normal vector at vertex 0.
     *          vn1 - The normal vector at vertex 1.
     *          vn2 - The normal vector at vertex 2.
     * @return: None
     * Sets the normal vectors of the triangle.
     */
    public void setNormals(Vector3D vn0, Vector3D vn1, Vector3D vn2) {
        setNormals(new Vector3D[]{vn0, vn1, vn2});
    }


    /**
     * @Author: Jafet
     * @params: ray - The ray to find intersection with.
     * @return: An Intersection object representing the intersection point, if any.
     * Calculates the intersection of the triangle with the given ray using an epsilon
     * to avoid self collisions.
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        Intersection intersection = new Intersection(null, -1, null, null);

        Vector3D[] vert = getVertices();
        Vector3D v2v0 = Vector3D.substract(vert[2], vert[0]);
        Vector3D v1v0 = Vector3D.substract(vert[1], vert[0]);
        Vector3D vectorP = Vector3D.crossProduct(ray.getDirection(), v1v0);
        double det = Vector3D.dotProduct(v2v0, vectorP);
        double invDet = 1.0 / det;
        Vector3D vectorT = Vector3D.substract(ray.getOrigin(), vert[0]);
        double u = invDet * Vector3D.dotProduct(vectorT, vectorP);

        if (!(u < 0 || u > 1)) {
            Vector3D vectorQ = Vector3D.crossProduct(vectorT, v2v0);
            double v = invDet * Vector3D.dotProduct(ray.getDirection(), vectorQ);
            if (!(v < 0 || (u + v) > (1.0 + EPSILON))) {
                double t = invDet * Vector3D.dotProduct(vectorQ, v1v0);
                intersection.setDistance(t);
            }
        }

        return intersection;
    }
}
