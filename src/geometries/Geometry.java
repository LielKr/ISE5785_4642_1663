package geometries;

import primitives.*;

/**
 * Represents a general geometric shape in 3D space.
 * This is an abstract base class for all geometries that can provide a normal vector at a given point.
 */
public abstract class Geometry extends Intersectable {

    // a. Field for emission color, initialized to black
    protected Color emission = Color.BLACK;

    /**
     * Returns the normal vector to the geometry at the specified point.
     *
     * @param point the point located on the geometry
     * @return the normal vector at the specified point on the geometry
     */
    public abstract Vector getNormal(Point point);

    // b. Getter for emission
    public Color getEmission() {
        return emission;
    }

    // c. Setter for emission using builder pattern
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
