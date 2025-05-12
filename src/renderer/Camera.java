package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Camera class represents a virtual camera in a 3D scene.
 * It defines the camera's position, orientation, and view plane properties.
 */
public class Camera implements Cloneable {
    private Vector vTo = null;         // Forward direction vector
    private Vector vUp = null;         // Upward direction vector
    private Vector vRight = null;      // Rightward direction vector
    private Point p0 = null;           // Camera position
    private Point pcenter = null;      // Center point of the view plane
    private double distance = 0.0;     // Distance to the view plane
    private double width = 0.0;        // View plane width
    private double height = 0.0;       // View plane height

    private Camera() {} // Private constructor for Builder pattern

    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray from the camera to a pixel on the view plane.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        double pixelWidth = width / nX;
        double pixelHeight = height / nY;

        double xJ = (j - (nX - 1) / 2.0) * pixelWidth;
        double yI = (i - (nY - 1) / 2.0) * pixelHeight;

        Point pIJ = pcenter;

        if (xJ != 0) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (yI != 0) {
            pIJ = pIJ.add(vUp.scale(-yI));
        }

        return new Ray(p0, pIJ.subtract(p0));
    }

    // Getters
    public Vector getVTo()     { return vTo; }
    public Vector getVUp()     { return vUp; }
    public Vector getVRight()  { return vRight; }
    public Point getP0()       { return p0; }
    public Point getPcenter()  { return pcenter; }
    public double getDistance(){ return distance; }
    public double getWidth()   { return width; }
    public double getHeight()  { return height; }

    /**
     * Builder class to construct Camera objects.
     */
    public static class Builder {
        private final Camera camera = new Camera();
        private Point target = null; // Optional target point

        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("Direction vectors must be orthogonal");
            }
            this.target = null;
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            camera.vRight = camera.vTo.crossProduct(camera.vUp);
            return this;
        }

        public Builder setDirection(Point target) {
            this.target = target;
            camera.vTo = null;
            camera.vUp = null;
            camera.vRight = null;
            return this;
        }

        public Builder setDirection(Point target, Vector vUp) {
            this.target = target;
            camera.vUp = vUp.normalize();
            camera.vTo = null;
            camera.vRight = null;
            return this;
        }

        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        public Builder setVpDistance(double distance) {
            if (alignZero(distance) <= 0) {
                throw new IllegalArgumentException("Distance must be positive");
            }
            camera.distance = distance;
            return this;
        }

        public Builder setVpSize(double width, double height) {
            if (alignZero(width) <= 0 || alignZero(height) <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        public Camera build() {
            try {
                validate(camera);
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException ignored) {
                return null;
            }
        }

        private void validate(Camera camera) {
            if (camera.width == 0 || camera.height == 0) {
                throw new IllegalStateException("View plane size is not set");
            }

            if (camera.p0 == null) {
                camera.p0 = Point.ZERO;
            }

            if (camera.distance == 0.0) {
                throw new IllegalStateException("Distance to view plane is not set");
            }

            if (target != null && target.equals(camera.p0)) {
                throw new IllegalStateException("Camera cannot be at the target point");
            }

            if (target != null) {
                camera.vTo = target.subtract(camera.p0).normalize();
                if (camera.vUp == null) {
                    camera.vUp = Vector.AXIS_Y;
                }
            }

            if (camera.vTo == null) {
                camera.vTo = Vector.AXIS_Z;
            }

            if (camera.vUp == null) {
                camera.vUp = Vector.AXIS_Y;
            }

            if (!isOrthogonal(camera.vTo, camera.vUp)) {
                camera.vUp = camera.vTo.crossProduct(camera.vUp).crossProduct(camera.vTo).normalize();
            }

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            camera.pcenter = camera.p0.add(camera.vTo.scale(camera.distance));
            target = null;
        }

        private boolean isOrthogonal(Vector v1, Vector v2) {
            return isZero(v1.dotProduct(v2));
        }

        public Builder setResolution(int nx, int ny) {
            // Future implementation placeholder
            return this;
        }
    }
}
