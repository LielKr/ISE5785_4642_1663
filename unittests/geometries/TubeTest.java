package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class TubeTest {


    Ray r1 = new Ray(Point.ZERO, new Vector(1, 0, 0));
    Tube tube = new Tube(new Ray(Point.ZERO, new Vector(0, 0, 1)), 1);
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple test for the getNormal function
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 1)), "Wrong normal for tube");

        // =============== Boundary Values Tests =================
        // TC02: (p-p0) is orthogonal to the axis of the tube
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 0)), "Wrong normal for tube");

        // ============ Equivalence Partitions Tests ==============
        // TC03: Test if the point is vertical to the axis
        assertDoesNotThrow(() -> new Tube(r1, 1).getNormal(new Point(0, 1, 0)), "Failed to throw an exception when the point is vertical to the axis");
    }

}