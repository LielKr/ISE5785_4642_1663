package geometries;

import primitives.Point;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Plane} class.
 */
class PlaneTest {

    // Define test points
    Point point = new Point(1.0, 2.0, 3.0);
    Point point1 = new Point(2.0, 4.0, 6.0);
    Point point2 = new Point(1.0, 0.0, 0.0);
    Point point3 = new Point(0, 1, 1);
    Point point4 = new Point(0, 0, 1);
    Point point5 = new Point(1.0, 1.0, 1.0);

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     * Verifies proper construction and exception handling.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a valid plane creation
        assertDoesNotThrow(() -> new Plane(point3, new Point(0, 1, 0), point4),
                "Failed to create a proper plane");

        // TC02: Test for exception when all points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(point, point, point),
                "Failed to throw an exception when creating a plane with all the same points");

        // =============== Boundary Values Tests =================
        // TC03: Test for exception when points are collinear
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(point2, new Point(2, 0, 0), new Point(3, 0, 0)),
                "Failed to throw an exception when creating a plane with points on the same line");

        // TC04: Test for exception when two points converge
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(Point.ZERO, point5, point5),
                "Failed to throw an exception when creating a plane with points that converge");

        assertThrows(IllegalArgumentException.class,
                () -> new Plane(point5, Point.ZERO, point5),
                "Failed to throw an exception when creating a plane with points that converge");

        assertThrows(IllegalArgumentException.class,
                () -> new Plane(point5, point5, Point.ZERO),
                "Failed to throw an exception when creating a plane with points that converge");
    }

    /**
     * Test method for {@link Plane#getNormal()}.
     * Verifies correct computation of the normal vector.
     */
    @Test
    void testGetNormal() {
        // Define a test point and expected normal vector
        Point point2 = new Point(1, 0, 0);
        Vector expectedNormal = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for correct normal vector calculation
        Vector normal = new Plane(point2, new Point(0, 1, 0), new Point(1, 1, 0)).getNormal();

        assertTrue(normal.equals(expectedNormal) || normal.equals(new Vector(0, 0, -1)),
                "Failed to get the normal vector of the plane");
    }

    @Test
    void testTestGetNormal() {
    }

    @Test
    void testTestGetNormal1() {
    }

    @Test
    void testFindIntersections() {
    }
}
