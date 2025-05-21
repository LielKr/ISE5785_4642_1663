package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * A simple implementation of the ray tracer that extends RayTracerBase.
 * This class calculates the color seen along a ray by finding the closest intersection
 * and returning the ambient light for now.
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructs a SimpleRayTracer for the given scene.
     *
     * @param scene the scene to render
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a given ray through the scene and returns the resulting color.
     * If no intersections are found, returns the background color.
     * Otherwise, calculates the color at the closest intersection point.
     *
     * @param ray the ray to trace
     * @return the color resulting from tracing the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.getGeometries().findIntersections(ray);

        if (intersections == null || intersections.isEmpty()) {
            return scene.getBackground();
        }

        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color of a given point in the scene.
     * In this simplified version, it only returns the ambient light color.
     *
     * @param point the point in the scene (currently unused)
     * @return the ambient light color of the scene
     */
    private Color calcColor(Point point) {
        return scene.getAmbientLight().getIntensity();
    }
}
