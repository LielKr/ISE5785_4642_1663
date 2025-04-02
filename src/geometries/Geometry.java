package geometries;

import primitives.*;

/**
 * Represents a general geometric shape in 3D space.
 * This is an abstract base class for all geometries that can provide a normal vector at a given point.
 */
public abstract class Geometry {

    /**
     * Returns the normal vector to the geometry at a given point.
     *
     * @param point the point on the geometry
     * @return the normal vector at the given point
     */
    public abstract Vector getNormal(Point point) ;
}
