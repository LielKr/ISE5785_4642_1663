package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane extends Geometry
{
    private final Vector normal;
    private final Point q;

    public Plane(Point p1, Point p2, Point p3)
    {
        this.normal = null;
        this.q = p1;
    }
    public Plane(Point p, Vector normal)
    {
        this.q=p;
        this.normal=normal.normalize();
    }

    public Vector getNormal() {
        return normal;
    }
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

}
