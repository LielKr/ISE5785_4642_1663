package geometries;

import primitives.Ray;

/**
 * Represents a finite cylinder in 3D space, defined by a central axis, radius, and height.
 * Inherits from {@link Tube}, which represents an infinite cylinder.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructs a Cylinder with a given height, axis, and radius.
     *
     * @param height the height of the cylinder
     * @param axis   the central axis of the cylinder
     * @param radius the radius of the cylinder
     */
    public Cylinder(double height, Ray axis, double radius) {
        super(axis, radius);
        this.height = height;
    }
}
