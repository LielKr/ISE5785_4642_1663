package geometries;

import primitives.Point;

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
}
