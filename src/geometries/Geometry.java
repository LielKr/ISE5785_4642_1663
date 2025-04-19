package geometries;

import primitives.*;

/**
 * Represents a general geometric shape in 3D space.
 * This is an abstract base class for all geometries that can provide a normal vector at a given point.
 */
public abstract class Geometry implements Intersectable {

    /**
     * Returns the normal vector to the geometry at the specified point.
     *
     * @param point the point located on the geometry
     * @return the normal vector at the specified point on the geometry
     */
    public abstract Vector getNormal(Point point);

    }