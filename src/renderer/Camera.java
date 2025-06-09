package renderer;

import primitives.Color;
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

    private ImageWriter imageWriter;   // Responsible for writing the final image
    private RayTracerBase rayTracer;   // Ray tracing engine for calculating colors
    private int nX = 1;                // Image resolution width (default 1)
    private int nY = 1;                // Image resolution height (default 1)

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
     * Renders the image using ray tracing.
     *
     * @return this Camera instance
     */
    public Camera renderImage() {
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRay(j, i);
            }
        }
        return this;
    }




    /**
     * Casts a ray through the center of a given pixel, calculates its color, and writes it to the image.
     *
     * @param j pixel column index (X axis)
     * @param i pixel row index (Y axis)
     */
//    private void castRay(int j, int i) {
//        Ray ray = constructRay(nX, nY, j, i);
//        Color color = rayTracer.traceRay(ray);
//        imageWriter.writePixel(j, i, color);
//    }

    private void castRay(int j, int i) {
        imageWriter.writePixel(j, i, rayTracer.traceRay(constructRay(nX, nY, j, i)));
    }

    /**
     * Draws a grid on the image with the specified interval and color.
     * Only draws lines and leaves the rest of the image intact.
     *
     * @param interval the spacing between grid lines
     * @param color    the color of the grid lines
     * @return this Camera instance
     */
    public Camera printGrid(int interval, Color color) {
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * Writes the rendered image to a file with the given name (without extension).
     *
     * @param filename name of the image file (without extension)
     * @return this Camera instance
     */
    public Camera writeToImage(String filename) {
        try {
            java.lang.reflect.Field field = imageWriter.getClass().getDeclaredField("imageName");
            field.setAccessible(true);
            field.set(imageWriter, filename);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set image filename", e);
        }
        imageWriter.writeToImage();
        return this;
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
     * Builder class for constructing Camera objects step-by-step.
     */
    public static class Builder {
        private final Camera camera = new Camera();
        private Point target = null;

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

        public Builder setResolution(int nX, int nY) {
            if (nX <= 0 || nY <= 0) {
                throw new IllegalArgumentException("Resolution values must be positive");
            }
            camera.nX = nX;
            camera.nY = nY;
            return this;
        }

        public Builder setRayTracer(Scene scene, RayTracerType rayTracerType) {
            if (rayTracerType == RayTracerType.SIMPLE) {
                camera.rayTracer = new SimpleRayTracer(scene);
            } else {
                camera.rayTracer = null;
            }
            return this;
        }

        public Camera build() {
            try {
                validate(camera);

                if (camera.nX <= 0 || camera.nY <= 0) {
                    throw new IllegalStateException("Resolution must be set and positive");
                }

                camera.imageWriter = new ImageWriter("default", camera.nX, camera.nY);

                if (camera.rayTracer == null) {
                    camera.rayTracer = new SimpleRayTracer(null);
                }

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
    }
}
