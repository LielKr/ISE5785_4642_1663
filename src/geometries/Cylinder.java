package geometries;
import static primitives.Util.isZero;

import primitives.*;
import primitives.Ray;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a finite cylinder in 3D space, defined by a central axis, radius, and height.
 * Inherits from {@link Tube}, which represents an infinite cylinder.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructs a Cylinder with a given height, axis, and radius.
     *
     * @param height the height of the cylinder
     * @param axis   the central axis of the cylinder
     * @param radius the radius of the cylinder
     */
    public Cylinder(double height, Ray axis, double radius) {
        super(axis, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        Point p0 = axis.getPoint(0d);
        Vector dir = this.axis.getDirection();

        //  If p0 is the head of the axis
        if (point.equals(p0))
            return dir.scale(-1);

        // If p1 is the end of the axis
        if (point.equals(axis.getPoint(height)))
            return dir;

        // If the point is on the top or bottom surface of the cylinder

        if (Util.isZero(p0.subtract(point).dotProduct(dir)))
            return dir.scale(-1d);

        if (Util.isZero(axis.getPoint(height).subtract(point).dotProduct(dir)))
            return dir;

        // Otherwise, call the superclass method
        return super.getNormal(point);
    }

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        return
    }
}






