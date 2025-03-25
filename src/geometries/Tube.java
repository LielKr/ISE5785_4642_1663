package geometries;
import primitives.Ray;

public class Tube extends RadialGeometry
{
    protected final Ray axis;

    public Tube(Ray axis, double radius)
    {
        super(radius);
        this.axis = axis;
    }
}
