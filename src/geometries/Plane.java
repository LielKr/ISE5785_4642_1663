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
     * @param point1 the first point
     * @param point2 the second point
     * @param point3 the third point
     */
    public Plane(Point point1, Point point2, Point point3) {
        this.q = point1;
        this.normal = point2.subtract(point1)
                .crossProduct(point3.subtract(point1))
                .normalize();
    }

    /**
     * Constructs a plane from a point and a normal vector.
     *
     * @param point      a point on the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point point, Vector normal) {
        this.q = point;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector
     */
    public Vector getNormal() {
        return normal;
    }

}
