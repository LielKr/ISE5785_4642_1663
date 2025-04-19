package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for the Cylinder class.
 * This class tests the getNormal method to ensure it returns a correct normal vector.
 */
class CylinderTest {

    /**
     * Tests the getNormal method to verify it returns a correct normal vector.
     * The normal vector should have a length of 1 and should be perpendicular to the cylinder's surface.
     */
    @Test
    public void testGetNormalCylinder() {
        // ======= Test Case 1: Normal for infinite cylinder =======
        // Creating an infinite cylinder with axis on the Y-axis and radius 1
        Cylinder infiniteCylinder = new Cylinder(5.0, new Ray(new Point(0, 0, 0), new Vector(0, 1, 0)), 1);

        // Test a point on the side of the cylinder
        Point sidePoint = new Point(1, 0, 0);  // A point on the side of the cylinder
        Vector normal = infiniteCylinder.getNormal(sidePoint);

        // The normal vector should be perpendicular to the Y-axis, meaning it lies in the X-Z plane
        assertEquals(new Vector(1, 0, 0), normal, "Normal vector for infinite cylinder is incorrect");

        // ======= Test Case 2: Normal for finite cylinder =======
        // Creating a finite cylinder with height 10, axis along the Y-axis and radius 2
        Cylinder finiteCylinder = new Cylinder(10, new Ray(new Point(0, 0, 0), new Vector(0, 1, 0)), 2);

        // Test a point on the side of the cylinder
        Point sidePointFinite = new Point(2, 5, 0);  // A point on the side of the cylinder
        Vector normalSide = finiteCylinder.getNormal(sidePointFinite);

        // The normal vector on the side should be perpendicular to the axis (Y-axis) and in the X-Z plane
        assertEquals(new Vector(1, 0, 0), normalSide, "Normal vector on the side of the finite cylinder is incorrect");

        // ======= Test Case 3: Normal for top base of the finite cylinder =======
        // Test a point on the top base (at height 10)
        Point topBasePoint = new Point(2, 10, 0);
        Vector normalTop = finiteCylinder.getNormal(topBasePoint);

        // The normal vector for the top base should point in the direction of the Y-axis (upward)
        assertEquals(new Vector(0, 1, 0), normalTop, "Normal vector on the top base of the finite cylinder is incorrect");

        // ======= Test Case 4: Normal for bottom base of the finite cylinder =======
        // Test a point on the bottom base (at height 0)
        Point bottomBasePoint = new Point(2, 0, 0);
        Vector normalBottom = finiteCylinder.getNormal(bottomBasePoint);

        // The normal vector for the bottom base should point in the opposite direction of the Y-axis (downward)
        assertEquals(new Vector(0, -1, 0), normalBottom, "Normal vector on the bottom base of the finite cylinder is incorrect");

        // ======= Test Case 5: Edge case for a point on the axis of the cylinder =======
        // Test a point on the axis (for finite cylinder), this should throw an exception
        assertThrows(IllegalArgumentException.class, () -> finiteCylinder.getNormal(new Point(0, 0, 0)),
                "Expected exception when point is on the axis of the cylinder");
    }
}

