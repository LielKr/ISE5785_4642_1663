package geometries;

import java.awt.*;
import java.util.List;
import primitives.Util;
import primitives.Ray;
import primitives.Util.*;
import primitives.Vector;
import primitives.Point;

import static primitives.Util.alignZero;

/**
 * Represents a sphere in 3D space defined by a center point and a radius.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a Sphere with a given center and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Point head = ray.getHead();
        Vector dir = ray.getDirection();

        if (head.equals(center))

            return List.of(new Intersection(this,ray.getPoint(radius)));

        Vector cHead = center.subtract(head);
        double tm = dir.dotProduct(cHead);
        double dSquared = cHead.lengthSquared() - tm * tm;
        double thSquared = radius*radius - dSquared;
        if (alignZero(thSquared) <= 0)
            return null;

        double th = Math.sqrt(thSquared); // t1 < t2 (always)
        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm - th);
        return t1 <= 0
                ? List.of(new Intersection(this,ray.getPoint(t2)))
                : List.of(new Intersection(this,ray.getPoint(t1)),new Intersection(this,ray.getPoint(t2)));
    }
}
