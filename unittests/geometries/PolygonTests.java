package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.*;

/**
 * Testing Polygons
 *
 * @author Dan
 */
class PolygonTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1)),
                "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                {new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1)};
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), 0.00001,
                    "Polygon's normal is not orthogonal to one of the edges");
    }


    @Test
    void findIntersections() {
        Polygon triangle = new Polygon(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        //TC01: Inside polygon/triangle(1 Point)

        Ray ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, 1));
        List<Point> result = triangle.findIntersections(ray);
        Point p1 = new Point(1, 1, 0);
        assertEquals(List.of(p1), result, "Inside polygon/triangle(1 Point)");

        //TC02: Outside against edge(0 Points)
        ray = new Ray(new Point(0, 0, -1), new Vector(2, 1, 1));
        assertNull(triangle.findIntersections(ray), "Outside against edge");

        //TC03: Outside the triangle opposite the vertex(0 Points)
        ray = new Ray(new Point(3, -2, -1), new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray), "Opposite the vertex");

        // =============== Boundary Values Tests ==================
        // TC11: On the rib(0 Points)
        ray = new Ray(new Point(0, -1, -1), new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray), "On the rib");

        // TC12: On the vertex(0 Points)
        ray = new Ray(new Point(1, -1, -1), new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray), "On the vertex");

        // TC13: On the continuation of the rib(0 Points)
        ray = new Ray(new Point(3, -4, -1), new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray), "On the continuation of the rib");
    }
}
