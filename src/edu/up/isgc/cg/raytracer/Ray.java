package edu.up.isgc.cg.raytracer;

public class Ray {
    private Vector3D origin;
    private Vector3D direction;

    /**
     * @Author: Jafet
     * @params: origin - The origin point of the ray.
     * @params: direction - The direction vector of the ray.
     * Initializes a Ray object with the specified origin and direction.
     */
    public Ray(Vector3D origin, Vector3D direction) {
        setOrigin(origin);
        setDirection(direction);
    }

    /**
     * @Author: Jafet
     * @return: The origin point of the ray.
     * Retrieves the origin point of the ray.
     */
    public Vector3D getOrigin() {
        return origin;
    }

    /**
     * @Author: Jafet
     * @params: origin - The origin point of the ray.
     * Sets the origin point of the ray.
     */
    public void setOrigin(Vector3D origin) {
        this.origin = origin;
    }

    /**
     * @Author: Jafet
     * @return: The normalized direction vector of the ray.
     * Retrieves the normalized direction vector of the ray.
     */
    public Vector3D getDirection() {
        return Vector3D.normalize(direction);
    }

    /**
     * @Author: Jafet
     * @params: direction - The direction vector of the ray.
     * Sets the direction vector of the ray and normalizes it.
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
