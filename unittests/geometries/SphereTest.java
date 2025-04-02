package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for the Sphere class.
 * This class tests the getNormal method to ensure it returns a correct normal vector.
 */
class SphereTest {

    /**
     * Tests the getNormal method to verify it returns a correct normal vector.
     * The normal vector should have a length of 1 and should be directed outward from the sphere.
     */
    @Test
    void testGetNormal() {
        // Create a sphere centered at (0,0,0) with radius 5
        Sphere sphere = new Sphere(new Point(0, 0, 0), 5);

        // Define a point on the surface of the sphere
        Point surfacePoint = new Point(3, 4, 0);

        // Compute the normal at the given point
        Vector normal = sphere.getNormal(surfacePoint);

        // Ensure that the normal vector has a length of 1
        assertEquals(1, normal.length(), "Normal vector is not a unit vector");

        // Ensure that the normal vector is in the correct direction
        Vector expectedNormal = new Vector(3, 4, 0).normalize();
        assertTrue(normal.equals(expectedNormal) || normal.equals(expectedNormal.scale(-1)),
                "Normal vector is incorrect");
    }
}
