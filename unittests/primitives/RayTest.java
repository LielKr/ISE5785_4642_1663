package primitives;

import org.junit.jupiter.api.Test;

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

}
