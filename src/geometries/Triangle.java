package geometries;

import primitives.*;
import primitives.Ray;
import geometries.Polygon;
import java.util.List;

/**
 * Represents a triangle in 3D space defined by three vertices.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a Triangle from three points.
     *
     * @param point1 the first vertex of the triangle
     * @param point2 the second vertex of the triangle
     * @param point3 the third vertex of the triangle
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3);

    }

    public List<Point> findIntersections(Ray ray) {
        Plane plane=new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
        if(plane.findIntersections(ray)==null) return null;
        Point p0=ray.getHead();
        Vector rayDirection=ray.getDirection();
        Vector v1=vertices.get(0).subtract(p0);
        Vector v2=vertices.get(1).subtract(p0);
        Vector v3=vertices.get(2).subtract(p0);
        Vector n1=v1.crossProduct(v2).normalize();
        Vector n2=v2.crossProduct(v3).normalize();
        Vector n3=v3.crossProduct(v1).normalize();

        double d1=n1.dotProduct(rayDirection);
        double d2=n2.dotProduct(rayDirection);
        double d3=n3.dotProduct(rayDirection);

        if((d1>0&& d2>0 && d3>0)||(d1<0&& d2<0 && d3<0))
            return plane.findIntersections(ray);
        else return null;

    }
}
