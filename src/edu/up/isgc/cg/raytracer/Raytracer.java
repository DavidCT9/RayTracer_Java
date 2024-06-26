package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.lights.DirectionalLight;
import edu.up.isgc.cg.raytracer.lights.Light;
import edu.up.isgc.cg.raytracer.lights.PointLight;
import edu.up.isgc.cg.raytracer.lights.SpotLight;
import edu.up.isgc.cg.raytracer.objects.*;
import edu.up.isgc.cg.raytracer.tools.OBJReader;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: David
 * @Coauthor: Jafet
 * This class represents a raytracer application.
 * It generates an image by tracing the paths of rays through a scene and simulating the way they interact with objects and lights.
 */
public class Raytracer {
    /**
     @Author: David
     * The main method of the raytracer application.
     * It initializes the scene, camera, lights, objects, and then generates the image using ray tracing.
     * Finally, it saves the generated image to a file.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println(new Date());

        /*
        Scene scene01 = new Scene();
        scene01.setCamera(new Camera(new Vector3D(0, 0.5, -3.5), 90, 60, 514, 270, 0.6, 50.0));
        scene01.addLight(new PointLight(new Vector3D(1.5, 1, -3), Color.WHITE, 4));
        scene01.addLight(new SpotLight(new Vector3D(3.0, 4.0, -3.0), Color.white, 4, 50, Vector3D.normalize(new Vector3D(0.0, -0.4, 0.3))));
        scene01.addLight(new SpotLight(new Vector3D(0, 4.0, 0.0), Color.white, 4, 40, Vector3D.normalize(new Vector3D(0.0, -0.4, 0.3))));
        scene01.addLight(new SpotLight(new Vector3D(-3.0, 4.0, 3.0), Color.white, 4, 50, Vector3D.normalize(new Vector3D(0.0, -0.4, 0.3))));

        scene01.addObject(OBJReader.getModel3D("evolutionScene/SmallTeapot.obj", new Vector3D(-2.5, -1.0, 1.5),  Color.ORANGE, 9.0, 0.2, false));
        scene01.addObject(OBJReader.getModel3D("evolutionScene/StandfordBunnyOK.obj", new Vector3D(0.0, -1.0, 0), new Color(0, 77, 158), 3.0, 0.8, false));
        scene01.addObject(OBJReader.getModel3D("evolutionScene/UBC_UP_ToyHorseOK.obj", new Vector3D(-2.75, -1.0, -1.0), Color.magenta, 5.0, 0.6, true));
        scene01.addObject(OBJReader.getModel3D("evolutionScene/Plano.obj", new Vector3D(2.0, -1.0, 1.5), new Color(50 , 50, 50), 100.0, 0.2, false));
*/

        Scene scene02 = new Scene();
        //        scene02.setCamera(new Camera(new Vector3D(1.5, 1.0, -3), 90, 56, 4096, 2160, 0.6, 50.0));
        scene02.setCamera(new Camera(new Vector3D(0, 2.0, -3), 90, 60, 514, 270, 0.6, 50.0));
        //scene02.addLight(new PointLight(new Vector3D(4.0, 1.0, -4.0), Color.WHITE, 5));
        scene02.addLight(new PointLight(new Vector3D(1.5, 1, -5), Color.WHITE, 4));
        scene02.addLight(new SpotLight(new Vector3D(4.0, 5.0, -5.0), Color.white, 5, 60, Vector3D.normalize(new Vector3D(0.0, -0.4, 0.3))));
        scene02.addLight(new SpotLight(new Vector3D(0, 4.0, -5.0), Color.white, 5, 60, Vector3D.normalize(new Vector3D(0.0, -0.4, 0.3))));
        scene02.addLight(new SpotLight(new Vector3D(-4.0, 4.0, -5.0), Color.white, 5, 60, Vector3D.normalize(new Vector3D(0.0, -0.4, 0.3))));
        scene02.addLight(new DirectionalLight(new Vector3D(0.0, 1.0, 1.0), Color.WHITE, 0.8));
        scene02.addObject(new Sphere(new Vector3D(0.0, 1.0, -1), 4, Color.BLUE, 8.0, 0.9, true));

        scene02.addObject(OBJReader.getModel3D("statueScene/jar1.obj", new Vector3D(0, 0, 0),  Color.ORANGE, 9.0, 0.2, false));
        scene02.addObject(OBJReader.getModel3D("statueScene/jar2.obj", new Vector3D(0.0, 0, 0), new Color(0, 77, 158), 3.0, 0.8, false));
        scene02.addObject(OBJReader.getModel3D("handScene/hand.obj", new Vector3D(0, 0, 0), Color.magenta, 5.0, 0.6, true));
        scene02.addObject(OBJReader.getModel3D("handScene/gem.obj", new Vector3D(0, 0, 0), Color.magenta, 5.0, 0.6, true));
        scene02.addObject(OBJReader.getModel3D("handScene/Wall.obj", new Vector3D(0, 0, 0), Color.magenta, 5.0, 0.6, true));


        //scene02.addObject(OBJReader.getModel3D("statueScene/statue.obj", new Vector3D(0, 0, 0), Color.magenta, 5.0, 0.6, true));
        scene02.addObject(OBJReader.getModel3D("evolutionScene/Plano.obj", new Vector3D(1.0, 1.0, 1.5), new Color(50 , 50, 50), 100.0, 0.2, false));


        BufferedImage image = raytrace(scene02);
        File outputImage = new File("NOW.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new Date());
    }

    /**
     * @Author: David
     * @Coauthor: Jafet
     * @param scene The scene to be rendered.
     * @return The generated image.
     * Generates an image by tracing rays through the scene and calculating the color of each pixel.
     * Also get the threads available on the machine to parallelize the process.
     */
    public static BufferedImage raytrace(Scene scene) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        Camera mainCamera = scene.getCamera();
        double[] nearFarPlanes = mainCamera.getNearFarPlanes();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();
        Vector3D[][] posRaytrace = mainCamera.calculatePositionsToRay();
        Vector3D pos = mainCamera.getPosition();
        double cameraZ = pos.getZ();
        double[] clippingPlanes = {cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]};

        for (int i = 0; i < posRaytrace.length; i++) {
            for (int j = 0; j < posRaytrace[i].length; j++) {

                Runnable runnable = parallelMethod(posRaytrace, i, j, pos, mainCamera, objects, clippingPlanes, lights, image);
                executorService.execute(runnable);
            }
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(10, TimeUnit.HOURS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("Cancel non-finished on threads");
            }
        }
        executorService.shutdownNow();

        return image;
    }

    /**
     * @Author: EduUpTech
     * @param posRaytrace The array of ray positions.
     * @param i The row index of the ray position.
     * @param j The column index of the ray position.
     * @param cameraPos The position of the camera.
     * @param mainCamera The main camera of the scene.
     * @param objects The list of objects in the scene.
     * @param clippingPlanes The clipping planes for the camera.
     * @param lights The list of lights in the scene.
     * @param image The image being generated.
     * @return A Runnable object representing the task to be executed.
     * Generates a pixel color by tracing a ray and calculating its interaction with objects and lights in the scene.
     */
    public static Runnable parallelMethod(Vector3D[][] posRaytrace, int i, int j, Vector3D cameraPos, Camera mainCamera,
                                          List<Object3D> objects, double[] clippingPlanes, List<Light> lights, BufferedImage image) {

        Runnable thisRunnable = new Runnable() {
            @Override
            public void run() {

                double x = posRaytrace[i][j].getX() + cameraPos.getX();
                double y = posRaytrace[i][j].getY() + cameraPos.getY();
                double z = posRaytrace[i][j].getZ() + cameraPos.getZ();

                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));

                int reflectionInitial = 0;
                Color pixelColor = getSpecularColor(lights, ray, objects, clippingPlanes, reflectionInitial, null);

                setRBG(image, i, j, pixelColor);

            }
        };
        return thisRunnable;

    }

    /**
     * @Author: Jafet
     * @param image The image to set the color.
     * @param i The row index of the pixel.
     * @param j The column index of the pixel.
     * @param color The color to set.
     * Sets the color of a pixel in the image.
     */
    private static synchronized void setRBG(BufferedImage image, int i, int j, Color color) {
        image.setRGB(i, j, color.getRGB());
    }

    /**
     * @Author: Jafet
     * @param original The original color.
     * @param otherColor The color to add.
     * @return The result of adding the two colors.
     * Adds two colors together.
     */
    public static Color addColor(Color original, Color otherColor) {
        float red = (float) Math.clamp((original.getRed() / 255.0) + (otherColor.getRed() / 255.0), 0.0, 1.0);
        float green = (float) Math.clamp((original.getGreen() / 255.0) + (otherColor.getGreen() / 255.0), 0.0, 1.0);
        float blue = (float) Math.clamp((original.getBlue() / 255.0) + (otherColor.getBlue() / 255.0), 0.0, 1.0);
        return new Color(red, green, blue);
    }

    /**
     * @Author: Jafet
     * @Coauthor: David
     * @param ray The ray to be cast.
     * @param objects The list of objects in the scene.
     * @param caster The object casting the ray.
     * @param clippingPlanes The clipping planes for the camera.
     * @return The closest intersection found.
     * Casts a ray into the scene and finds the closest intersection with objects.
     */
    public static Intersection raycast(Ray ray, List<Object3D> objects, Object3D caster, double[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int i = 0; i < objects.size(); i++) {
            Object3D currObj = objects.get(i);
            if (caster == null || !currObj.equals(caster)) {
                Intersection intersection = currObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();

                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }

        return closestIntersection;
    }

    /**
     * @Author: David
     * @param lightSource The light source.
     * @param intersection The intersection point.
     * @param viewerRay The ray from the viewer.
     * @return The intensity of the specular reflection.
     * Calculates the shininess contribution of each light.
     */
    private static double calculateShininess(Light lightSource, Intersection intersection, Ray viewerRay) {
        Vector3D objNormal = intersection.getNormal();
        Vector3D lightDirection = Vector3D.normalize(Vector3D.substract(intersection.getPosition(), lightSource.getPosition()));

        Vector3D halfVector = Vector3D.normalize(Vector3D.add(lightDirection, viewerRay.getDirection()));

        double alpha = intersection.getObject().getShininess() * 10;

        double shininess = Math.max(0, Math.pow(Vector3D.dotProduct(objNormal, halfVector), alpha));

        return shininess;
    }

    /**
     * @Author: David
     * @param intersection The intersection point.
     * @param viewerRay The ray from the viewer.
     * @param objects The list of objects in the scene.
     * @param clippingPlanes The clipping planes for the camera.
     * @param reflectionDepth The depth of reflection.
     * @param color The accumulated color.
     * @param lights The list of lights in the scene.
     * @return The color of reflection.
     * Calculates the color of reflection from an intersection point and the bounces between objects.
     */
    private static Color calculateReflection(Intersection intersection, Ray viewerRay, List<Object3D> objects,
                                             double[] clippingPlanes, int reflectionDepth, Color color,
                                             List<Light> lights) {
        double epsilon = 1e-6;
        Vector3D surfaceNormal = Vector3D.normalize(intersection.getNormal());
        Vector3D incidenceV = viewerRay.getDirection();
        Vector3D reflection = Vector3D.substract(incidenceV, Vector3D.scalarMultiplication(surfaceNormal, 2 * Vector3D.dotProduct(incidenceV, surfaceNormal)));
        Ray reflectionRay = new Ray(Vector3D.add(intersection.getPosition(), Vector3D.scalarMultiplication(surfaceNormal, epsilon)), Vector3D.normalize(reflection));

        Intersection reflectionIntersection = raycast(reflectionRay, objects, intersection.getObject(), clippingPlanes);

        if (reflectionIntersection != null && reflectionDepth <= 2) {
            reflectionDepth += 1;
            //Color reflectionColor = getSpecularColor(lights, reflectionRay, objects, clippingPlanes, reflectionDepth, reflectionIntersection.getObject());
            Color reflectionColor = getSpecularColor(lights, reflectionRay, objects, clippingPlanes, reflectionDepth, intersection.getObject());
            double reflectivity = reflectionIntersection.getObject().getReflectivity();
            reflectionColor = new Color(
                    (float) Math.clamp(reflectionColor.getRed() * reflectivity / 255.0, 0.0, 1.0),
                    (float) Math.clamp(reflectionColor.getGreen() * reflectivity / 255.0, 0.0, 1.0),
                    (float) Math.clamp(reflectionColor.getBlue() * reflectivity / 255.0, 0.0, 1.0)
            );
            color = addColor(color, reflectionColor);
            color = calculateReflection(reflectionIntersection, reflectionRay, objects, clippingPlanes, reflectionDepth, color, lights);
        }

        return color;
    }

    /**
     * @Author: David
     * @param incidenceV The incident vector.
     * @param refractionIndex1 The refractive index of the first medium.
     * @param surfaceNormal The surface normal.
     * @param refractionIndex2 The refractive index of the second medium.
     * @param closestIntersection The closest intersection point.
     * @param objects The list of objects in the scene.
     * @param clippingPlanes The clipping planes for the camera.
     * @param lights The list of lights in the scene.
     * @param reflectionDepth The depth of reflection.
     * @return The color of refraction.
     * Calculates the color of refraction from an intersection point and the redirection of
     * this ray between refractive objects.
     */
    private static Color calculateRefraction(Vector3D incidenceV, double refractionIndex1, Vector3D surfaceNormal,
                                             double refractionIndex2, Intersection closestIntersection, List<Object3D> objects,
                                             double[] clippingPlanes, List<Light> lights, int reflectionDepth) {

        double eta1 = refractionIndex1, eta2 = refractionIndex2;
        double c1 = Math.clamp(Vector3D.dotProduct(surfaceNormal, incidenceV), -1.0, 1.0);
        if (c1 < 0) {
            c1 = -c1;
        } else {
            double temp = eta1;
            eta1 = eta2;
            eta2 = temp;
            surfaceNormal = Vector3D.scalarMultiplication(surfaceNormal, -1);
        }

        Vector3D refractiveV;
        double eta = eta1 / eta2;
        double k = 1 - eta * eta * (1 - c1 * c1);
        if (k < 0) {
            refractiveV = new Vector3D(0, 0, 0);
        } else {
            refractiveV = Vector3D.add(
                    Vector3D.scalarMultiplication(incidenceV, eta),
                    Vector3D.scalarMultiplication(surfaceNormal, eta * c1 - Math.sqrt(k))
            );
        }

        Vector3D epsilon = Vector3D.scalarMultiplication(surfaceNormal, -1e-8);
        Vector3D rayOrigin = Vector3D.add(epsilon, closestIntersection.getPosition());

        Ray refreactiveRay = new Ray(rayOrigin, Vector3D.normalize(refractiveV));
        Intersection refractiveIntersection = raycast(refreactiveRay, objects, closestIntersection.getObject(), clippingPlanes);

        Color pixelColor = getSpecularColor(lights, refreactiveRay, objects, clippingPlanes, reflectionDepth, closestIntersection.getObject());
        return pixelColor;

    }

    /**
     * @Author: EduUpTech
     * @Coauthor: Jafet
     * @param lights The list of lights in the scene.
     * @param ray The ray being cast.
     * @param objects The list of objects in the scene.
     * @param clippingPlanes The clipping planes for the camera.
     * @param reflectionDepth The depth of reflection.
     * @param caster The object casting the ray, or null if there is no caster.
     * @return The calculated color of the pixel.
     * Calculates the color of a pixel based on the intersection of a ray with objects in the scene.
     * This method considers various factors such as direct lighting, specular reflection, shadows, refraction, and reflection.
     * */
    private static Color getSpecularColor(List<Light> lights, Ray ray, List<Object3D> objects,
                                          double[] clippingPlanes, int reflectionDepth, Object3D caster) {

        Intersection closestIntersection = raycast(ray, objects, caster, clippingPlanes);

        Color pixelColor = Color.BLACK;
        if (closestIntersection != null) {
            Color objColor = closestIntersection.getObject().getColor();

            for (Light light : lights) {

                Ray shadowray = new Ray(closestIntersection.getPosition(), light.getPosition());
                Intersection shadowIntersection = raycast(shadowray, objects, closestIntersection.getObject(), clippingPlanes);

                if (shadowIntersection != null) {
                    continue;
                }

                double objReflectivity = closestIntersection.getObject().getReflectivity();
                double objOriginalColorRatio = Math.clamp(1 - objReflectivity, 0, 1); //CLAMP?

                double nDotL = light.getNDotL(closestIntersection);
                Color lightColor = light.getColor();

                double intensity = light.getIntensity() * nDotL;

                //Distancia interseccion a la luz
                double distanceIntersectionLight = Vector3D.calculateDistance(closestIntersection.getPosition(), light.getPosition());
                if (distanceIntersectionLight < 1){ distanceIntersectionLight *= -1;}

                if (light.getClass().equals(PointLight.class) || light.getClass().equals(SpotLight.class)) {
                    intensity /= distanceIntersectionLight;
                }

                double[] lightColors = new double[]{lightColor.getRed() / 255.0, lightColor.getGreen() / 255.0, lightColor.getBlue() / 255.0};
                double[] objColors = new double[]{objColor.getRed() / 255.0, objColor.getGreen() / 255.0, objColor.getBlue() / 255.0};
                for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                    objColors[colorIndex] *= intensity * lightColors[colorIndex];
                }

                double shine = calculateShininess(light, closestIntersection, ray);
                Color specular = new Color(
                        (float) Math.clamp(light.getColor().getRed() * objOriginalColorRatio / 255.0 * shine, 0.0, 1.0),
                        (float) Math.clamp(light.getColor().getGreen() * objOriginalColorRatio / 255.0 * shine, 0.0, 1.0),
                        (float) Math.clamp(light.getColor().getBlue() * objOriginalColorRatio / 255.0 * shine, 0.0, 1.0));

                double baseQty = 0.001;
                Color base = new Color(
                        (float) Math.clamp(objColors[0] * baseQty / 255.0, 0.0, 1.0),
                        (float) Math.clamp(objColors[1] * baseQty / 255.0, 0.0, 1.0),
                        (float) Math.clamp(objColors[2] * baseQty / 255.0, 0.0, 1.0));

                Color diffuse = new Color(
                        (float) Math.clamp(objColors[0] * objOriginalColorRatio, 0.0, 1.0),
                        (float) Math.clamp(objColors[1] * objOriginalColorRatio, 0.0, 1.0),
                        (float) Math.clamp(objColors[2] * objOriginalColorRatio, 0.0, 1.0));

                pixelColor = addColor(pixelColor, base);
                pixelColor = addColor(pixelColor, diffuse);
                pixelColor = addColor(pixelColor, specular);

                if (closestIntersection.getObject().isRefractive()) {
                    Color refraColor = calculateRefraction(ray.getDirection(), 1.0, closestIntersection.getNormal(), 1.4,
                            closestIntersection, objects, clippingPlanes, lights, reflectionDepth);

                    refraColor = new Color(
                            (float) Math.clamp(refraColor.getRed() * objReflectivity / 255.0, 0.0, 1.0),
                            (float) Math.clamp(refraColor.getGreen() * objReflectivity / 255.0, 0.0, 1.0),
                            (float) Math.clamp(refraColor.getBlue() * objReflectivity / 255.0, 0.0, 1.0));

                    pixelColor = refraColor;


                }

                Color reflectionColor = calculateReflection(closestIntersection, ray, objects, clippingPlanes, reflectionDepth, Color.BLACK, lights);

                reflectionColor = new Color(
                        (float) Math.clamp(reflectionColor.getRed() * objReflectivity / 255.0, 0.0, 1.0),
                        (float) Math.clamp(reflectionColor.getGreen() * objReflectivity / 255.0, 0.0, 1.0),
                        (float) Math.clamp(reflectionColor.getBlue() * objReflectivity / 255.0, 0.0, 1.0));

                pixelColor = addColor(pixelColor, reflectionColor);


            }
        }

        return pixelColor;
    }


}
