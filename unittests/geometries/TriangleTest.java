package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Test
    void testFindIntersections() {
        Point p100 = new Point(1.0, 0.0, 0.0);
        Point p010 = new Point(0.0, 1.0, 0.0);
        Point p040 = new Point(0.0, 4.0, 0.0);
        Triangle triangle = new Triangle(p100, p010, p040);

        //על הצלע
        Point p00_50=new Point(0.0, 0.5, 0.0);
        Point pIn=new Point(0.1,1.1,0);
        Point p1= new Point(0.0,1.5,-1.0);
        Vector v0 = new Vector(0.0, 0.0, 3.0);
        assertNull(triangle.findIntersections(new Ray(p1, v0)), "Ray's line out of triangle");

        //על הקודקוד
        Point p_110=new Point(-1.0, 1.0, 0.0);
        assertNull(triangle.findIntersections(new Ray(p_110, v0)), "Ray's line out of triangle");

        //בתוך המשולש
        Point p3=new Point(-0.5, 1.0, -1.0);
        Vector v333=new Vector(3.0, 3.0, 3.0);
        Point p4=new Point(0.5, 2.0, 0.0);
        final var exp1 = List.of(p4);
        final var result1 = plane.findIntersections(new Ray(p3,v333));
        assertEquals(exp1, result1, "Ray crosses triangle");

        //מחוץ למשולש על המשך הצלע
        Point p5=new Point(-1.0, 0.0, -1.0);
        Vector v303=new Vector(3.0, 0.0, 3.0);
        //Point p6=new Point(0.0, 0.0, 0.0);
        assertNull(triangle.findIntersections(new Ray(p5, v303)), "Ray's line out of triangle");

        //מחוץ למשולש מול קודקוד
        Point p7=new Point(0.0, 0.0, -1.0);
        Vector v3=new Vector(-10.0, 2.0, 2.0);
        //Point p8=new Point(-5.0, 1.0, 0.0);
        assertNull(triangle.findIntersections(new Ray(p7, v3)), "Ray's line out of triangle");


        //מחוץ למשולש מול צלע
        Point p9=new Point(-1.0, 0.0, -1.0);
        Vector v4=new Vector(3.0, 0.0, 2.0);
        //Point p11=new Point(-5.0, 1.0, 0.0);
        assertNull(triangle.findIntersections(new Ray(p9, v4)), "Ray's line out of triangle");



    }

    }
