package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a plane in 3D space defined by a point and a normal vector.
 */
public class Plane extends Geometry {

    /**
     * The normal vector of the plane.
     */
    private final Vector normal;

    /**
     * A point on the plane.
     */
    private final Point q;

    /**
     * Constructs a plane from three points lying on the plane.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.normal = null;
        this.q = p1;
    }

    /**
     * Constructs a plane from a point and a normal vector.
     *
     * @param p      a point on the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point p, Vector normal) {
        this.q = p;
        this.normal = normal.normalize();
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
