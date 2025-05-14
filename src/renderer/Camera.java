package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a virtual camera in a 3D scene.
 * Defines camera orientation, position, and view plane parameters.
 */
public class Camera implements Cloneable {

    private Vector vTo = null;         // Forward direction vector
    private Vector vUp = null;         // Upward direction vector
    private Vector vRight = null;      // Rightward direction vector
    private Point p0 = null;           // Camera position
    private Point pcenter = null;      // Center of the view plane
    private double distance = 0.0;     // Distance to the view plane
    private double width = 0.0;        // View plane width
    private double height = 0.0;       // View plane height

    /**
     * Private constructor for use by the Builder only.
     */
    private Camera() {}

    /**
     * @return A new Builder instance for building a Camera.
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray from the camera to a specific pixel on the view plane.
     *
     * @param nX number of pixels in width
     * @param nY number of pixels in height
     * @param j  column index of pixel
     * @param i  row index of pixel
     * @return ray from camera to pixel (i,j)
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

    /**
     * Placeholder for setting an image writer.
     * @param imageWriter the image writer
     * @return this Camera (for chaining)
     */
    public Object setImageWriter(ImageWriter imageWriter) {
        return this;
    }

    // Getters

    /** @return forward direction vector */
    public Vector getVTo()     { return vTo; }

    /** @return upward direction vector */
    public Vector getVUp()     { return vUp; }

    /** @return rightward direction vector */
    public Vector getVRight()  { return vRight; }

    /** @return camera position */
    public Point getP0()       { return p0; }

    /** @return center point of the view plane */
    public Point getPcenter()  { return pcenter; }

    /** @return distance to view plane */
    public double getDistance(){ return distance; }

    /** @return view plane width */
    public double getWidth()   { return width; }

    /** @return view plane height */
    public double getHeight()  { return height; }

    /**
     * Builder class for constructing Camera objects step-by-step.
     */
    public static class Builder {
        private final Camera camera = new Camera();
        private Point target = null;

        /**
         * Sets direction vectors for the camera.
         * @param vTo forward direction
         * @param vUp upward direction
         * @return this builder
         */
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

        /**
         * Sets a target point the camera looks at (forward direction will be calculated).
         * @param target the point the camera is aimed at
         * @return this builder
         */
        public Builder setDirection(Point target) {
            this.target = target;
            camera.vTo = null;
            camera.vUp = null;
            camera.vRight = null;
            return this;
        }

        /**
         * Sets a target and an upward direction for the camera.
         * @param target point to look at
         * @param vUp upward vector
         * @return this builder
         */
        public Builder setDirection(Point target, Vector vUp) {
            this.target = target;
            camera.vUp = vUp.normalize();
            camera.vTo = null;
            camera.vRight = null;
            return this;
        }

        /**
         * Sets the location of the camera.
         * @param p0 camera position
         * @return this builder
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         * @param distance positive distance value
         * @return this builder
         */
        public Builder setVpDistance(double distance) {
            if (alignZero(distance) <= 0) {
                throw new IllegalArgumentException("Distance must be positive");
            }
            camera.distance = distance;
            return this;
        }

        /**
         * Sets the size of the view plane.
         * @param width width of the view plane
         * @param height height of the view plane
         * @return this builder
         */
        public Builder setVpSize(double width, double height) {
            if (alignZero(width) <= 0 || alignZero(height) <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Finalizes the construction of the Camera.
         * @return a fully configured Camera
         */
        public Camera build() {
            try {
                validate(camera);
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException ignored) {
                return null;
            }
        }

        /**
         * Validates all necessary camera parameters before building.
         */
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

        /**
         * Checks if two vectors are orthogonal.
         * @param v1 first vector
         * @param v2 second vector
         * @return true if orthogonal
         */
        private boolean isOrthogonal(Vector v1, Vector v2) {
            return isZero(v1.dotProduct(v2));
        }

        /**
         * Placeholder for setting resolution.
         * @param nx width in pixels
         * @param ny height in pixels
         * @return this builder
         */
        public Builder setResolution(int nx, int ny) {
            return this;
        }

        /**
         * Placeholder for setting ray tracer (not implemented).
         * @param scene scene to trace
         * @param rayTracerType type of tracer
         * @return null (currently)
         */
        public Builder setRayTracer(Scene scene, RayTracerType rayTracerType) {
            return null;
        }
    }
}
