package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import geometries.Intersectable;

class GeometriesTest {

    @Test
    void testFindIntersections() {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================
        // TC01: empty geometries list
        assertNull(geometries.findIntersections(new Ray(new Point(1.0, 2.0, 3.0), new Vector(-1.0, 0.0, 0.0))));

        geometries.add(new Plane(new Point(2.0, 3.0, 1.0), new Vector(0.0, 1.0, 0.0)));
        geometries.add(new Triangle(new Point(0.0, 0.0, 1.0), new Point(1.0, 1.0, 0.0), new Point(-1.0, 1.0, 0.0)));
        geometries.add(new Sphere(new Point(0.0, 0.0, 0.0), 2.0));
        // TC02: each geometry doesn't have intersection points
        assertNull(geometries.findIntersections(new Ray(new Point(3.0, 3.0, 3.0), new Vector(0.0, 1.0, 0.0))));

        List<Point> points = geometries.findIntersections(new Ray(new Point(-3.0, 0.0, 0.0), new Vector(1.0, 0.0, 0.0)));
        // TC03: just one geometry has intersections point
        assertEquals(2, points.size(), "Expected 2 intersection points with Sphere");

        // TC04: all the geometries have intersection points
        Geometries geometries1 = new Geometries(
                new Sphere(new Point(1, 1, 1), 1.5),
                new Polygon(
                        new Point(0, 0, 0),
                        new Point(2, 0, 0),
                        new Point(2, 2, 0),
                        new Point(0, 2, 0)
                ),
                new Triangle(
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0, 1)
                )
        );
        List<Point> result = geometries1.findIntersections(
                new Ray(new Point(0.5, 0.5, -1.0), new Vector(0.0, 0.0, 1.0))
        );
        assertEquals(2, result.size(), "Expected 4 intersection points with all geometries");

        // ============ Equivalence Partitions Tests ==============
        // TC05: part of the geometries has intersection points
        assertEquals(1,
                geometries.findIntersections(new Ray(new Point(0.0, -2.0, 0.0), new Vector(0.0, 1.0, 0.0))).size(),
                "Expected 2 intersection points with Plane and Triangle");
    }

}