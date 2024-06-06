package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.lights.Light;
import edu.up.isgc.cg.raytracer.objects.Object3D;
import edu.up.isgc.cg.raytracer.objects.Camera;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private Camera camera;
    private List<Object3D> objects;
    private List<Light> lights;

    /**
     * @Author: Jafet
     * Initializes a Scene object with empty lists for objects and lights.
     */
    public Scene() {
        setObjects(new ArrayList<>());
        setLights(new ArrayList<>());
    }

    /**
     * @Author: Jafet
     * @return: The camera object of the scene.
     * Retrieves the camera object of the scene.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * @Author: Jafet
     * @params: camera - The camera object to set.
     * Sets the camera object of the scene.
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * @Author: Jafet
     * @params: object - The object to add to the scene.
     * Adds an object to the scene.
     */
    public void addObject(Object3D object){
        getObjects().add(object);
    }

    /**
     * @Author: Jafet
     * @return: The list of objects in the scene.
     * Retrieves the list of objects in the scene.
     */
    public List<Object3D> getObjects() {
        if(objects == null){
            objects = new ArrayList<>();
        }
        return objects;
    }

    /**
     * @Author: Jafet
     * @params: objects - The list of objects to set.
     * Sets the list of objects in the scene.
     */
    public void setObjects(List<Object3D> objects) {
        this.objects = objects;
    }

    /**
     * @Author: Jafet
     * @return: The list of lights in the scene.
     * Retrieves the list of lights in the scene.
     */
    public List<Light> getLights() {
        if(lights == null){
            lights = new ArrayList<>();
        }
        return lights;
    }

    /**
     * @Author: Jafet
     * @params: lights - The list of lights to set.
     * Sets the list of lights in the scene.
     */
    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    /**
     * @Author: Jafet
     * @params: light - The light to add to the scene.
     * Adds a light to the scene.
     */
    public void addLight(Light light){
        getLights().add(light);
    }
}
