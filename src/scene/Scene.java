package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Point;


public class Scene {
    public String sceneName;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();

    public Scene(String sceneName) {
        this.sceneName = sceneName;
    }

    public Scene setBackground(Color color) {
        this.background = color;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
    /**
     * Returns the background color of the scene.
     *
     * @return the background color
     */
    public Color getBackground() {
        return background;
    }


    /**
     * @return the ambient light of the scene
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * @return the collection of geometries in the scene
     */
    public Geometries getGeometries() {
        return geometries;
    }

}
