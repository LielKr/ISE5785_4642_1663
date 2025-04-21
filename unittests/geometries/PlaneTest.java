package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                "Failed to get the normal vector of the plane 0");
    }

    @Test
    void testTestGetNormal() {
    }

    @Test
    void testTestGetNormal1() {
    }

    @Test
    void testFindIntersections() {

        Point p001 = new Point(0.0, 0.0, 1.0);
        Point p101 = new Point(1.0, 0.0, 1.0);
        Point p011 = new Point(0.0, 1.0, 1.0);
        Vector v001= new Vector(0.0,0.0,1.0);

        Plane plane=new Plane(p001,p101,p011);
        Plane normalPlane=new Plane(p011,v001);

        //לא מאונך לא מקביל ולא חותך
        Point p012 = new Point(0.0, 1.0, 2.0);
        Vector v011= new Vector(0.0,1.0,1.0);
        Vector v111= new Vector(1.0,1.0,1.0);

        assertNull(plane.findIntersections(new Ray(p012,v111)), "Ray's line out of plane 1");

        //לא מאונך לא מקביל וחותך
        Point p010 = new Point(0.0, 1.0, 0.0);
        Vector v033= new Vector(0.0,3.0,3.0);
        Point p021 = new Point(0.0, 2.0, 1.0);
        final var exp1 = List.of(p021);
        final var result1 = plane.findIntersections(new Ray(p010,v033));
        assertEquals(exp1, result1, "Ray crosses plane");

        //לא מאונך לא מקביל והקרן מתחילה בנקודת חיתוך
        assertNull(plane.findIntersections(new Ray(p011,v033)), "Ray's line out of plane 2");
        //מתחיל מהנקודה שהמישור מיוצג על ידו
        assertNull(normalPlane.findIntersections(new Ray(p011,v011)), "Ray's line out of plane");

        //מקביל ולא חותך
        Point p002 = new Point(0.0, 0.0, 2.0);
        Vector v100= new Vector(1.0,0.0,0.0);
        Ray rayNull=new Ray(p002,v100);
        assertNull(plane.findIntersections(rayNull), "Ray's line out of plane 3");

        //מקביל ומתלכד
        Ray rayNull2=new Ray(p001,v100);
        assertNull(plane.findIntersections(rayNull2), "Ray's line out of plane 4");

        //מאונך ולא חותך
        Vector v003= new Vector(0.0,0.0,3.0);
        assertNull(plane.findIntersections(new Ray(p002,v003)), "Ray's line out of plane 5");

        //מאונך ונקודת החיתוך

        assertNull(plane.findIntersections(new Ray(p001,v003)), "Ray's line out of plane");

        //מאונך וחותך
        final var exp2 = List.of(p011);
        final var result2 = plane.findIntersections(new Ray(p010,v001));
       assertEquals(exp2, result2, "Ray crosses plane");

    }


}
