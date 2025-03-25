package geometries;

import primitives.Ray;

public class Cylinder extends Tube
{
    private final double height;

    public Cylinder(double height, Ray axis, double radius)
    {
        super(axis, radius);
        this.height = height;
    }
}
