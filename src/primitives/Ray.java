package primitives;

import java.util.Objects;

/**
 * Represents a ray in 3D space defined by an origin point and a direction vector.
 */
public class Ray {

    /**
     * The starting point (origin) of the ray.
     */
    private final Point head;

    /**
     * The normalized direction vector of the ray.
     */
    private final Vector direction;

    /**
     * Constructs a ray with a given origin and direction.
     *
     * @param head      the origin point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return "head: " + head + " , direction: " + direction;
    }

    public Vector getDirection() {
        return direction;
    }
    public Point getHead() { return head; }


    //המתודה מחשבת נקודה על הישר של הקרן,
    //במרחק נתון מראש הקרן
    public Point getPoint(double t) {
        // if t is zero, return the head point
        if (Util.isZero(t))
            return head;
        return head.add(direction.scale(t));
    }

}
