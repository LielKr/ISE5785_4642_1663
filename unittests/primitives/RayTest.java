package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testRay() {
        Ray r=new Ray(new Point(0.0,0.0,0.0), new Vector(1.0,2.0,3.0));
        assertEquals(1.0,r.getDirection().length(),0.00001,"Ray is not nomalized!!");

    }

    @Test
    void testGetPoint() {
        Point p1 = new Point(1, 2, 3);
        Ray ray = new Ray(p1, new Vector(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: t is a negative number
        assertEquals(new Point(-2, 2, 3), ray.getPoint(-3), "Bad getPoint with negative t");

        // TC02: t is a positive number
        assertEquals(new Point(4, 2, 3), ray.getPoint(3), "Bad getPoint with positive t");

        // =============== Boundary Values Tests =================
        // TC03: t is zero
        assertEquals(p1, ray.getPoint(0), "Bad getPoint with t=0");
    }

    @Test
    void testFindClosestPoint() {
            Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

            // ============ Equivalence Partition Test ==============

            List<Point> pointsList = List.of(
                    new Point(7, 8, 9),
                    new Point(1, 2, 3),
                    new Point(4, 5, 6)
            );

            // TC01: Closest point is in the middle
            assertEquals(
                    new Point(1, 2, 3),
                    ray.findClosestPoint(pointsList),
                    "Expected the closest point to be (1,2,3)");

            // =============== Boundary Value Tests ==================

            // TC02: No points provided
            pointsList = List.of();
            assertNull(
                    ray.findClosestPoint(pointsList),
                    "Expected null for an empty list of points");

            // TC03: Closest point is the first in the list
            pointsList = List.of(
                    new Point(1, 2, 3),
                    new Point(7, 8, 9),
                    new Point(4, 5, 6)
            );
            assertEquals(
                    new Point(1, 2, 3),
                    ray.findClosestPoint(pointsList),
                    "Expected the first point (1,2,3) to be the closest");

            // TC04: Closest point is the last in the list
            pointsList = List.of(
                    new Point(7, 8, 9),
                    new Point(4, 5, 6),
                    new Point(1, 2, 3)
            );
            assertEquals(
                    new Point(1, 2, 3),
                    ray.findClosestPoint(pointsList),
                    "Expected the last point (1,2,3) to be the closest");
        }



}
