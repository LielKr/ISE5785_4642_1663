package primitives;

/**
 * Represents a vector in 3D space.
 * A vector is a directed entity that supports operations such as addition, scaling,
 * dot product, cross product, normalization, and more.
 * A zero vector is not allowed.
 */
public class Vector extends Point {

    /**
     * Constructs a vector from three coordinate values.
     *
     * @param x the X component
     * @param y the Y component
     * @param z the Z component
     * @throws IllegalArgumentException if the vector is the zero vector
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Constructs a vector from a {@link Double3} object.
     *
     * @param double3 the 3D coordinate
     * @throws IllegalArgumentException if the vector is the zero vector
     */
    public Vector(Double3 double3) {
        super(double3);
        if (double3.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Returns a new vector that is the result of adding this vector to another.
     *
     * @param vector the vector to add
     * @return the resulting vector
     */
    public Vector add(Vector vector) {
        return new Vector(this.xyz.add(vector.xyz));
    }

    /**
     * Returns a new vector that is the result of scaling this vector by a scalar.
     *
     * @param scalar the scale factor
     * @return the scaled vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param v the other vector
     * @return the dot product
     */
    public double dotProduct(Vector v) {
        return this.xyz.d1 * v.xyz.d1 + this.xyz.d2 * v.xyz.d2 + this.xyz.d3 * v.xyz.d3;
    }

    /**
     * Calculates the cross product of this vector with another vector.
     *
     * @param v the other vector
     * @return the resulting vector from the cross product
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
                this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1
        );
    }

    /**
     * Returns the squared length (magnitude) of this vector.
     *
     * @return the squared length
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Returns the length (magnitude) of this vector.
     *
     * @return the length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Returns a normalized version of this vector (unit vector).
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        return new Vector(
                this.xyz.d1 / length(),
                this.xyz.d2 / length(),
                this.xyz.d3 / length()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector other)
                && super.equals((Point) other);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }
}
