package geometries;

/**
 * Represents a geometric shape that is defined by a radius.
 * This is an abstract base class for radial geometries such as spheres, tubes, etc.
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the radial geometry.
     */
    protected final double radius;

    /**
     * Constructs a radial geometry with the specified radius.
     *
     * @param radius the radius of the geometry
     */
    protected RadialGeometry(double radius) {
        this.radius = radius;
    }
}
