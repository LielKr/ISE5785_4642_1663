package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


class PolygonTest {

    @Test
    void testGetNormal() {
        Polygon polygon = new Polygon(new Point(1, 1, 0), new Point(1, 0, 0), new Point(-1, -1, 0), new Point(0, 1, 0));
        Point[] pts = {
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(-1, 1, 1)
        };
        Polygon pol = new Polygon(pts);

        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)),"Exception was thrown in getNormal method");
        Vector result = pol.getNormal(new Point(0, 0, 1));

        // בדיקה שהנורמל הוא וקטור יחידה (אורך 1)
        assertEquals(1, result.length(), 0.00001,
                "Polygon's normal is not a unit vector");

        // בדיקה שהנורמל מאונך לכל אחת מצלעות המצולע
        for (int i = 0; i < 3; ++i) {
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), 0.00001,
                    "Polygon's normal is not orthogonal to one of the edges");
        }
    }
}