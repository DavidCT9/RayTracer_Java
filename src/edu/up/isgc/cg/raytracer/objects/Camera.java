package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * @Author: Jafet
 * @params: position - The position vector of the camera.
 * @params: fovH - The horizontal field of view angle.
 * @params: fovV - The vertical field of view angle.
 * @params: width - The width resolution of the camera.
 * @params: height - The height resolution of the camera.
 * @params: nearPlane - The near clipping plane distance.
 * @params: farPlane - The far clipping plane distance.
 * @return: None
 * Represents a camera object in the ray tracer, defining the view parameters
 * and resolution for rendering scenes.
 */
public class Camera extends Object3D {
    //FOV[0] = Horizontal | FOV[1] = Vertical
    private double[] fieldOfView = new double[2];
    private double defaultZ = 15.0;
    private int[] resolution = new int[2];
    private double[] nearFarPlanes = new double[2];

    public Camera(Vector3D position, double fovH, double fovV,
                  int width, int height, double nearPlane, double farPlane) {
        super(position, Color.BLACK, 10.0, 0.0,false); //Last shininess
        setFOV(fovH, fovV);
        setResolution(width, height);
        setNearFarPlanes(new double[]{nearPlane, farPlane});
    }

    /**
     * @Author: Jafet
     * @return: The field of view angles as an array, where [0] is horizontal and [1] is vertical.
     * Retrieves the field of view angles of the camera.
     */
    public double[] getFieldOfView() {
        return fieldOfView;
    }

    /**
     * @Author: Jafet
     * @params: fieldOfView - The field of view angles to set.
     * Sets the field of view angles of the camera.
     */
    private void setFieldOfView(double[] fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    /**
     * @Author: Jafet
     * @return: The horizontal field of view angle.
     * Retrieves the horizontal field of view angle of the camera.
     */
    public double getFOVHorizontal() {
        return fieldOfView[0];
    }

    /**
     * @Author: Jafet
     * @return: The vertical field of view angle.
     * Retrieves the vertical field of view angle of the camera.
     */
    public double getFOVVertical() {
        return fieldOfView[1];
    }

    /**
     * @Author: Jafet
     * @params: fovH - The horizontal field of view angle to set.
     * Sets the horizontal field of view angle of the camera.
     */
    public void setFOVHorizontal(double fovH) {
        fieldOfView[0] = fovH;
    }

    /**
     * @Author: Jafet
     * @params: fovV - The vertical field of view angle to set.
     * Sets the vertical field of view angle of the camera.
     */
    public void setFOVVertical(double fovV) {
        fieldOfView[1] = fovV;
    }

    /**
     * @Author: Jafet
     * @params: fovH - The horizontal field of view angle to set.
     *          fovV - The vertical field of view angle to set.
     * Sets both the horizontal and vertical field of view angles of the camera.
     */
    public void setFOV(double fovH, double fovV) {
        setFOVHorizontal(fovH);
        setFOVVertical(fovV);
    }

    /**
     * @Author: Jafet
     * @return: The default distance of the camera from the scene.
     * Retrieves the default distance of the camera from the scene.
     */
    public double getDefaultZ() {
        return defaultZ;
    }

    /**
     * @Author: Jafet
     * @params: defaultZ - The default distance to set.
     * Sets the default distance of the camera from the scene.
     */
    public void setDefaultZ(double defaultZ) {
        this.defaultZ = defaultZ;
    }

    /**
     * @Author: Jafet
     * @return: The resolution of the camera as an array, where [0] is width and [1] is height.
     * Retrieves the resolution of the camera.
     */
    public int[] getResolution() {
        return resolution;
    }

    /**
     * @Author: Jafet
     * @params: width - The width resolution to set.
     * Sets the width resolution of the camera.
     */
    public void setResolutionWidth(int width) {
        resolution[0] = width;
    }

    /**
     * @Author: Jafet
     * @params: height - The height resolution to set.
     * Sets the height resolution of the camera.
     */
    public void setResolutionHeight(int height) {
        resolution[1] = height;
    }


    /**
     * @Author: Jafet
     * @params: width - The width resolution to set.
     *          height - The height resolution to set.
     * Sets both the width and height resolution of the camera.
     */
    public void setResolution(int width, int height) {
        setResolutionWidth(width);
        setResolutionHeight(height);
    }

    /**
     * @Author: Jafet
     * @return: The width resolution of the camera.
     * Retrieves the width resolution of the camera.
     */
    public int getResolutionWidth() {
        return resolution[0];
    }

    /**
     * @Author: Jafet
     * @return: The height resolution of the camera.
     * Retrieves the height resolution of the camera.
     */
    public int getResolutionHeight() {
        return resolution[1];
    }

    /**
     * @Author: Jafet
     * @params: resolution - The resolution to set.
     * Sets the resolution of the camera.
     */
    private void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    /**
     * @Author: Jafet
     * @return: The near and far clipping planes as an array, where [0] is near and [1] is far.
     * Retrieves the near and far clipping planes of the camera.
     */
    public double[] getNearFarPlanes() {
        return nearFarPlanes;
    }


    /**
     * @Author: Jafet
     * @params: nearFarPlanes - The near and far clipping planes to set.
     * Sets the near and far clipping planes of the camera.
     */
    private void setNearFarPlanes(double[] nearFarPlanes) {
        this.nearFarPlanes = nearFarPlanes;
    }


    /**
     * @Author: Jafet
     * @return: A 2D array of vectors representing positions to cast rays from.
     * Calculates and returns positions within the camera's field of view for casting rays.
     */
    public Vector3D[][] calculatePositionsToRay() {
        double angleMaxX = getFOVHorizontal() / 2.0;
        double radiusMaxX = getDefaultZ() / Math.cos(Math.toRadians(angleMaxX));

        double maxX = Math.sin(Math.toRadians(angleMaxX)) * radiusMaxX;
        double minX = -maxX;

        double angleMaxY = getFOVVertical() / 2.0;
        double radiusMaxY = getDefaultZ() / Math.cos(Math.toRadians(angleMaxY));

        double maxY = Math.sin(Math.toRadians(angleMaxY)) * radiusMaxY;
        double minY = -maxY;

        Vector3D[][] positions = new Vector3D[getResolutionWidth()][getResolutionHeight()];
        double posZ = defaultZ;

        double stepX = (maxX - minX) / getResolutionWidth();
        double stepY = (maxY - minY) / getResolutionHeight();
        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                double posX = minX + (stepX * x);
                double posY = maxY - (stepY * y);
                positions[x][y] = new Vector3D(posX, posY, posZ);
            }
        }
        return positions;
    }

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
