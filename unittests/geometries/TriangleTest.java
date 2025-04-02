package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
Point point0=new Point(0.0,0.0,0.0);
Point point1=new Point(1.0,0.0,0.0);
Point point2=new Point(0.0,1.0,0.0);
Point point3=new Point(0.0,0.0,1.0);
Plane plane=new Plane(point0,point1,point2);
Vector actualNormal = plane.getNormal(point0);
Vector v0=new Vector(0.0,0.0,1.0);
Vector v1 = point2.subtract(point1);
Vector v2 = point3.subtract(point1);

@Test
void testGetNormal() {
    assertTrue(actualNormal.equals(v0) || actualNormal.equals(v0.scale(-1)), "Normal vector is incorrect");
    assertDoesNotThrow(() -> plane.getNormal(point0), "Failed to get normal vector");
    assertEquals(1.0, actualNormal.length(),0.00001, "Plane's normal is not a unit vector");
    assertEquals(0.0, actualNormal.dotProduct(v1), "Normal is not orthogonal to v1");
    assertEquals(0.0, actualNormal.dotProduct(v2), "Normal is not orthogonal to v2");

}



}