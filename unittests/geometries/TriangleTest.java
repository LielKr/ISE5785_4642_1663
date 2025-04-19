package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Triangle} class.
 */
class TriangleTest {

    /** Points defining the triangle. */
    Point point0 = new Point(0.0, 0.0, 0.0);
    Point point1 = new Point(1.0, 0.0, 0.0);
    Point point2 = new Point(0.0, 1.0, 0.0);
    Point point3 = new Point(0.0, 0.0, 1.0);

    /** Plane defined by three points of the triangle. */
    Plane plane = new Plane(point0, point1, point2);

    /** Computed normal vector of the plane at a given point. */
    Vector actualNormal = plane.getNormal(point0);

    /** Vectors used for normal verification. */
    Vector v0 = new Vector(0.0, 0.0, 1.0);
    Vector v1 = point2.subtract(point1);
    Vector v2 = point3.subtract(point1);

//    /**
//     * Test method for {@link Triangle#getNormal(Point)}.
//     * Verifies correct computation of the normal vector.
//     */
//    @Test
//    void testGetNormal() {
//        // Verify that the computed normal is either v0 or its negation
//        assertTrue(actualNormal.equals(v0) || actualNormal.equals(v0.scale(-1)), "Normal vector is incorrect");
//
//        // Ensure no exception is thrown when retrieving the normal
//        assertDoesNotThrow(() -> plane.getNormal(point0), "Failed to get normal vector");
//        assertDoesNotThrow(() -> plane.getNormal(new Point(0.5,0.0,0.0)), "Failed to get normal vector");
//
//
//        // Check that the normal vector has unit length
//        assertEquals(1.0, actualNormal.length(), 0.00001, "Plane's normal is not a unit vector");
//
//        // Ensure that the normal is orthogonal to vector v1
//        assertEquals(0.0, actualNormal.dotProduct(v1), "Normal is not orthogonal to v1");
//
//        // Ensure that the normal is orthogonal to vector v2
//        assertEquals(0.0, actualNormal.dotProduct(v2), "Normal is not orthogonal to v2");
//    }
}
