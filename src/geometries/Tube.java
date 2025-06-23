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

    /**
     * This method calculates the normal vector to a point on the surface of the tube.
     * It first calculates the projection of the point onto the axis of the tube and then
     * returns the normalized vector from the center of the tube to the given point.
     *
     * @param point the point on the surface of the tube where the normal vector is calculated.
     * @return the normalized vector pointing from the center of the tube to the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        //calculate the projection of the point on the axis
        Vector pminusP0 = point.subtract(this.axis.getPoint(0d));
        double t = alignZero(this.axis.getDirection().dotProduct(pminusP0));

        //find center of the tube
        //return the normalized vector from the center of the tube to the point
        return point.subtract(this.axis.getPoint(t)).normalize();
    }

    /**
     * This method is used to calculate intersections between the ray and the tube.
     * Currently, it is not implemented and returns null.
     *
     * @param ray the ray to check for intersections with the tube.
     * @return null, as the method is not yet implemented.
     */
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        return null;
    }
}
