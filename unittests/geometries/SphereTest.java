package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

//import primitives.Point;
//import primitives.Ray;
//import primitives.Vector;

import java.util.List;

/**
 * Unit tests for the Sphere class.
 * This class tests the getNormal method to ensure it returns a correct normal vector.
 */
class SphereTest {

    /**
     * Tests the getNormal method to verify it returns a correct normal vector.
     * The normal vector should have a length of 1 and should be directed outward from the sphere.
    כן */
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


    /**
     * A point used in some tests
     */
    private final Point p001 = new Point(0, 0, 1);
    /**
     * A point used in some tests
     */
    private final Point p100 = new Point(1, 0, 0);
    /**
     * A vector used in some tests
     */
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310));
        assertNotNull(result1, "Can't be empty list");
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)

        // TC04: Ray starts after the sphere (0 points)

        // =============== Boundary Values Tests ==================
        // **** Group 1: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        // TC12: Ray starts at sphere and goes outside (0 points)
        // **** Group 2: Ray's line goes through the center
        // TC21: Ray starts before the sphere (2 points)
        // TC22: Ray starts at sphere and goes inside (1 points)
        // TC23: Ray starts inside (1 points)
        // TC24: Ray starts at the center (1 points)
        // TC25: Ray starts at sphere and goes outside (0 points)
        // TC26: Ray starts after sphere (0 points)
        // **** Group 3: Ray's line is tangent to the sphere (all tests 0 points)
        // TC31: Ray starts before the tangent point
        // TC32: Ray starts at the tangent point
        // TC33: Ray starts after the tangent point
        // **** Group 4: Special cases
        // TC41: Ray's line is outside sphere, ray is orthogonal to ray start to sphere's center line
        // TC42: Ray's starts inside, ray is orthogonal to ray start to sphere's center line
    }
}
