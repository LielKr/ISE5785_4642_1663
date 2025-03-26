package geometries;

import primitives.Ray;

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
}
