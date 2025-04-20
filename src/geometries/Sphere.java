package geometries;

import java.awt.*;
import java.util.List;

import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import primitives.Point;

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
    public List<Point> findIntersections(Ray ray)
    {
        Point p0=ray.getHead();
        Vector u=center.subtract(p0);
        double tm= ray.getDirection().dotProduct(u);
        double d= Util.alignZero(Math.sqrt(u.dotProduct(u)-tm*tm));
        if(d>=radius){ return null; }
        double th=Util.alignZero(Math.sqrt(radius * radius - d*d));
        double t1=tm-th;
        double t2=tm+th;
        if(t1>0&&t2>0){return List.of(ray.getPoint(t1),ray.getPoint(t2));}
        if(t1>0&&t2<0){return List.of(ray.getPoint(t1));}
        if(t1<0&&t2>0){return List.of(ray.getPoint(t2));}
        return null;
    }
}
