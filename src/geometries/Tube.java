package geometries;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.alignZero;
/**
 * Represents an infinite tube in 3D space defined by a central axis and a radius.
 */
public class Tube extends RadialGeometry {

    /**
     * The central axis of the tube.
     */
    protected final Ray axis;

    /**
     * Constructs a Tube with a given axis and radius.
     *
     * @param axis   the central axis of the tube
     * @param radius the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        //calculate the projection of the point on the axis
        double t = alignZero(this.axis.getDirection().dotProduct(point.subtract(this.axis.getPoint(0d))));

        //find center of the tube
        //return the normalized vector from the center of the tube to the point
        return point.subtract(this.axis.getPoint(t)).normalize();
    }

}
