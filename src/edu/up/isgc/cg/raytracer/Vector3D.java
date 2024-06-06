package edu.up.isgc.cg.raytracer;

public class Vector3D {
    private static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    private double x, y, z;

    /**
     * @Author: Jafet
     * @params: x - The x-coordinate of the vector.
     * @params: y - The y-coordinate of the vector.
     * @params: z - The z-coordinate of the vector.
     * Initializes a Vector3D object with the given coordinates.
     */
    public Vector3D(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * @Author: Jafet
     * @return: The x-coordinate of the vector.
     * Retrieves the x-coordinate of the vector.
     */
    public double getX() {
        return x;
    }

    /**
     * @Author: Jafet
     * @params: x - The x-coordinate to set.
     * Sets the x-coordinate of the vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @Author: Jafet
     * @return: The y-coordinate of the vector.
     * Retrieves the y-coordinate of the vector.
     */
    public double getY() {
        return y;
    }

    /**
     * @Author: Jafet
     * @params: y - The y-coordinate to set.
     * Sets the y-coordinate of the vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @Author: Jafet
     * @return: The z-coordinate of the vector.
     * Retrieves the z-coordinate of the vector.
     */
    public double getZ() {
        return z;
    }

    /**
     * @Author: Jafet
     * @params: z - The z-coordinate to set.
     * Sets the z-coordinate of the vector.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @Author: Jafet
     * @return: A copy of the vector.
     * Creates and returns a copy of the vector.
     */
    public Vector3D clone(){
        return new Vector3D(getX(), getY(), getZ());
    }

    /**
     * @Author: Jafet
     * @return: A zero vector.
     * Retrieves a zero vector.
     */
    public static Vector3D ZERO(){
        return ZERO.clone();
    }

    /**
     * @Author: Jafet
     * @return: A string representation of the vector.
     * Retrieves a string representation of the vector.
     */
    @Override
    public String toString(){
        return "Vector3D{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                "}";
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The first vector.
     * @params: vectorB - The second vector.
     * @return: The dot product of the two vectors.
     * Calculates and returns the dot product of two vectors.
     */
    public static double dotProduct(Vector3D vectorA, Vector3D vectorB){
        return (vectorA.getX() * vectorB.getX()) + (vectorA.getY() * vectorB.getY()) + (vectorA.getZ() * vectorB.getZ());
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The first vector.
     * @params: vectorB - The second vector.
     * @return: The cross product of the two vectors.
     * Calculates and returns the cross product of two vectors.
     */
    public static Vector3D crossProduct(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D((vectorA.getY() * vectorB.getZ()) - (vectorA.getZ() * vectorB.getY()),
                (vectorA.getZ() * vectorB.getX()) - (vectorA.getX() * vectorB.getZ()),
                (vectorA.getX() * vectorB.getY()) - (vectorA.getY() * vectorB.getX()));
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The vector.
     * @return: The magnitude of the vector.
     * Calculates and returns the magnitude of a vector.
     */
    public static double magnitude (Vector3D vectorA){
        return Math.sqrt(dotProduct(vectorA, vectorA));
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The first vector.
     * @params: vectorB - The second vector.
     * @return: The result of adding the two vectors.
     * Adds two vectors together and returns the result.
     */
    public static Vector3D add(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY(), vectorA.getZ() + vectorB.getZ());
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The first vector.
     * @params: vectorB - The second vector.
     * @return: The result of subtracting the second vector from the first.
     * Subtracts the second vector from the first and returns the result.
     */
    public static Vector3D substract(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY(), vectorA.getZ() - vectorB.getZ());
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The vector.
     * @return: The normalized vector.
     * Normalizes the vector and returns the result.
     */
    public static Vector3D normalize(Vector3D vectorA){
        double mag = Vector3D.magnitude(vectorA);
        return new Vector3D(vectorA.getX() / mag, vectorA.getY() / mag, vectorA.getZ() / mag);
    }

    /**
     * @Author: Jafet
     * @params: vectorA - The vector.
     * @params: scalar - The scalar value.
     * @return: The result of multiplying the vector by the scalar.
     * Multiplies the vector by a scalar value and returns the result.
     */
    public static Vector3D scalarMultiplication(Vector3D vectorA, double scalar){
        return new Vector3D(vectorA.getX() * scalar, vectorA.getY() * scalar, vectorA.getZ() * scalar);
    }

    /**
     * @Author: Jafet
     * @params: posA - The first position vector.
     * @params: posB - The second position vector.
     * @return: The distance between the two positions.
     * Calculates and returns the distance between two positions represented by vectors.
     */
    public static double calculateDistance(Vector3D posA, Vector3D posB){
        double x = posA.getX() - posB.getX();
        double y = posA.getY() - posB.getY();
        double z = posA.getZ() - posB.getZ();
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
}
