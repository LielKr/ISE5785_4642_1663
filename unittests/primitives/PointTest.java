package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point point1= new Point(2.0, 4.0, 6.0);
    Point point2= new Point(3.0, 5.0, 7.0);
    Vector v1 = new Vector(1.0, 2.0, 3.0);
    Vector v2= new Vector(-1.0,-2.0,-3.0);


    @Test
    void testSubtract() {
        assertEquals(new Point(2.0, 3.0, 4.0), point2.add(v1),"Wrong result of adding two vectors");
///  /////////////////////////////////////////////
    }

    @Test
    void testAdd() {
        assertEquals(new Point(3.0, 6.0, 9.0), point1.add(v1),"Wrong result of adding two vectors");
        assertEquals(new Point(1.0, 2.0, 3.0), point1.add(v2),"Wrong result of adding two vectors");
    }

    @Test
    void testDistance() {
        assertEquals(0.0, point1.distance(point1), 0.00001,"Wrong result of distanceing two vectors");
        assertEquals(1.732, point2.distance(point1),0.00001,"Wrong result of distanceing two vectors");
    }

    @Test
    void testDistanceSquared() {
        assertEquals(0.0, point1.distanceSquared(point1), 0.00001,"Calculate the squared distance between two points does not work correctly");
        assertEquals(3.0, point1.distanceSquared(point2), 0.00001,"Calculate the squared distance between two points does not work correctly");
    }
}