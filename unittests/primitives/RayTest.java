package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testRay() {
        Ray r=new Ray(new Point(0.0,0.0,0.0), new Vector(1.0,2.0,3.0));
        assertEquals(1.0,r.getDirection().length(),0.00001,"Ray is not nomalized!!");

    }
}