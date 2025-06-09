package renderer;

import geometries.Geometry;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.Intersection;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A simple ray tracer that returns the ambient light color if the ray hits a geometry,
 * or the background color otherwise.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructs a SimpleRayTracer with the given scene.
     *
     * @param scene the scene to be rendered
     */

    private static final double DELTA = 0.1;


    /**
     * Checks if a point is not in shadow.
     * For each light source, casts a shadow ray toward the light and checks if any object blocks the light.
     *
     * @param intersection the intersection point to check for shadow
     * @return true if the point is illuminated (no object blocks the light), false if it's in shadow
     */
    private boolean unshaded(Intersection intersection) {
        // Iterate through all light sources in the scene
        for (LightSource light : scene.lights) {
            // Get the light direction vector from the point toward the light source
            Vector l = light.getL(intersection.point);
            Vector lightDirection = l.scale(-1); // Reverse direction for the shadow ray

            // Create a shadow ray from the point toward the light, slightly shifted along the normal
            Ray lightRay = new Ray(intersection.point, lightDirection, intersection.normalBeforeHit);

            // Calculate the distance from the point to the light source
            double lightDistance = light.getDistance(intersection.point);

            // Find intersections of the shadow ray with geometries, up to the light source distance
            List<Intersection> shadowIntersections = scene.geometries.calculateIntersections(lightRay);
            if (shadowIntersections != null) {
                Double3 ktr = Double3.ONE; // Start with full transparency
                for (Intersection shadowIntersection : shadowIntersections) {
                    // Multiply by the transparency coefficient of each intersected object
                    ktr = ktr.product(shadowIntersection.geometry.getMaterial().kT);
                    // If transparency becomes too low, the light is blocked
                    if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                        return false; // Point is in shadow
                    }
                }
            }
        }
        return true; // No object blocked the light, point is illuminated
    }


    /**
     * Finds the closest intersection point between the ray and the scene.
     *
     * @param ray the ray to trace
     * @return the closest intersection point, or null if none
     */
    private Intersection findClosestIntersection(Ray ray) {
        List<Intersection> intersections = scene.geometries.calculateIntersections(ray);
        if (intersections == null || intersections.isEmpty()) {
            return null;
        }
        return findClosestIntersection(intersections, ray);
    }


    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Intersection closestIntersection = findClosestIntersection(ray);
        if (closestIntersection == null) {
            return scene.background;
        }
        return calcColor(closestIntersection, ray);
    }

    /**
     * Finds the closest point to the ray's origin from a list of intersection points.
     *
     * @param intersections list of intersection points
     * @param ray           the ray whose origin is used for distance calculation
     * @return the closest point to the ray's origin
     */
    private Intersection findClosestIntersection(List<Intersection> intersections, Ray ray) {
        Point origin = ray.getPoint(0);
        Intersection closest = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Intersection i : intersections) {
            double distance = i.point.distance(origin);
            if (distance < minDistance) {
                minDistance = distance;
                closest = i;
            }
        }

        return closest;
    }

    private boolean preprocessIntersection(Intersection intersection, Vector direction) {
        intersection.rayDirection = direction;
        intersection.normalBeforeHit = intersection.geometry.getNormal(intersection.point);
        if (intersection.rayDirection.dotProduct(intersection.normalBeforeHit) > 0)
            intersection.normalBeforeHit = intersection.normalBeforeHit.scale(-1);
        intersection.rayNormalDotProduct = alignZero(direction.dotProduct(intersection.normalBeforeHit));
        //System.out.println("✅ n·v = " + intersection.vNormal);

        return (intersection.rayNormalDotProduct != 0);
    }
//    private boolean preprocessIntersection(Intersection intersection, Vector rayDirection) {
//        intersection.rayDirection = rayDirection;
//        intersection.normal = intersection.geometry.getNormal(intersection.point);
//        intersection.vNormal = alignZero(intersection.rayDirection.dotProduct(intersection.normal));
//
//        // תיקון חשוב: אם הקרן מגיעה מהצד השני של הנורמל, נהפוך את הנורמל
//        if (intersection.vNormal < 0) {
//            intersection.normal = intersection.normal.scale(-1);
//            intersection.vNormal = -intersection.vNormal;
//        }
//
//        intersection.rayNormalDotProduct = intersection.vNormal;
//        return !isZero(intersection.vNormal);
//    }


    private boolean setLightSource(Intersection intersection, LightSource lightSource) {
        intersection.lightSource = lightSource;
        intersection.lightDirection = lightSource.getL(intersection.point);
        intersection.lightNormalDotProduct = alignZero(intersection.lightDirection.dotProduct(intersection.normalBeforeHit));
        //System.out.println("✅ n·l = " + intersection.lightNormalDotProduct);

        return intersection.lightNormalDotProduct * intersection.rayNormalDotProduct > 0;
    }
//private boolean setLightSource(Intersection intersection, LightSource lightSource) {
//    intersection.lightSource = lightSource;
//    intersection.lightDirection = lightSource.getL(intersection.point);
//    intersection.lightNormalDotProduct = alignZero(intersection.lightDirection.dotProduct(intersection.normal));
//    return alignZero(intersection.rayNormalDotProduct * intersection.lightNormalDotProduct) > 0;
//}
//private Boolean setLightSource(Intersection intersection, LightSource lightSource) {
//    intersection.lightSource = lightSource;
//    intersection.lightDirection = lightSource.getL(intersection.point);
//    intersection.lightNormalDotProduct = intersection.lightDirection.dotProduct(intersection.normalBeforeHit);
//    return Util.alignZero(intersection.vNormal * intersection.lightNormalDotProduct) > 0;
//}



    /**
     * Calculates the overall transparency (ktr) from the intersection point to the light source.
     * If the transparency drops below MIN_CALC_COLOR_K, the light is mostly blocked.
     *
     * @param intersection the intersection point to check for partial shadow
     * @param light        the light source to test against
     * @return the cumulative transparency (Double3) from the point to the light
     */
    private Double3 transparency(Intersection intersection, LightSource light) {
        Vector l = light.getL(intersection.point);
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(intersection.point, lightDirection, intersection.normalBeforeHit);
        double lightDistance = light.getDistance(intersection.point);

        List<Intersection> intersections = scene.geometries.calculateIntersections(lightRay);
        if (intersections == null) return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (Intersection shadowIntersection : intersections) {
            ktr = ktr.product(shadowIntersection.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }
        return ktr;
    }


    private Double3 calcDiffusive(Intersection intersection) {
        return intersection.material.kD.scale(Math.abs(intersection.lightNormalDotProduct));
    }

    private Double3 calcSpecular(Intersection intersection) {
        Vector r = intersection.lightDirection
                .subtract(intersection.normalBeforeHit.scale(2 * intersection.lightNormalDotProduct));
        double minusVR = -alignZero(intersection.rayDirection.dotProduct(r));
        return minusVR <= 0 ? Double3.ZERO
                : intersection.material.kS.scale(Math.pow(minusVR, intersection.material.nShininess));
    }


    /**
     * Constructs a reflected ray from a given point and normal.
     *
     * @param intersection the intersection data (includes point, direction, normal)
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Intersection intersection) {
        Vector v = intersection.rayDirection;
        Vector n = intersection.normalBeforeHit;
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n))); // reflection direction
        return new Ray(intersection.point, r, n);
    }

    /**
     * Constructs a refracted ray (same direction, shifted by the normal).
     *
     * @param intersection the intersection data
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Intersection intersection) {
        return new Ray(intersection.point, intersection.rayDirection, intersection.normalBeforeHit);
    }

    /**
     * Calculates the color at the point of intersection using recursive ray tracing.
     * This method initializes the recursive calculation by setting the ray direction
     * and invoking the detailed recursive method with the maximum allowed depth and initial attenuation.
     *
     * @param intersection the intersection data including the geometry and hit point
     * @param ray          the original ray that caused the intersection
     * @return the calculated color at the intersection point
     */
    private Color calcColor(Intersection intersection, Ray ray) {
        intersection.rayDirection = ray.getDirection();
        return calcColor(intersection, MAX_CALC_COLOR_LEVEL, INITIAL_K);
    }

    /**
     * Calculates the color at the intersection point including ambient, local, and global lighting effects.
     * Uses recursive ray tracing to add effects like reflection and refraction.
     *
     * @param intersection the intersection point
     * @param level        recursion depth (to limit the number of recursive calls)
     * @param k            accumulated transparency/reflection attenuation factor
     * @return the total calculated color at the intersection point
     */
    private Color calcColor(Intersection intersection, int level, Double3 k) {
        if (level == 0 || k.lowerThan(MIN_CALC_COLOR_K) || !preprocessIntersection(intersection, intersection.rayDirection)) {
            return Color.BLACK;
        }

        Color ambientAndEmission = scene.ambientLight.getIntensity()
                .scale(intersection.geometry.getMaterial().kA)
                .add(intersection.geometry.getEmission());

        Color localEffects = calcColorLocalEffects(intersection);
        Color globalEffects = calcGlobalEffects(intersection, level, k);

        return ambientAndEmission.add(localEffects).add(globalEffects);
    }


    /**
     * Calculates the local lighting effects (diffuse and specular) at a given intersection point.
     * Includes partial shadowing based on transparency (kt) of objects between the point and the light source.
     *
     * @param intersection the intersection point to compute lighting for
     * @return the color result of local lighting effects at the intersection
     */
    private Color calcColorLocalEffects(Intersection intersection) {
        Color color = Color.BLACK; // Do not include emission here to avoid duplication

        for (LightSource lightSource : scene.lights) {
            if (!setLightSource(intersection, lightSource))
                continue;

            Double3 ktr = transparency(intersection, lightSource); // Calculate transparency
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                continue;

            Color iL = lightSource.getIntensity(intersection.point).scale(ktr); // Scale by transparency
            color = color.add(
                    iL.scale(calcDiffusive(intersection)),
                    iL.scale(calcSpecular(intersection))
            );
        }
        //System.out.println("Local color: " + color);

        return color;
    }


    /**
     * Calculates the global lighting effects (reflection and refraction)
     * by tracing secondary rays recursively and combining their color.
     *
     * @param intersection the point of intersection
     * @param level        the current recursion depth
     * @param k            the current accumulated attenuation factor
     * @return the combined color from global effects
     */
    private Color calcGlobalEffects(Intersection intersection, int level, Double3 k) {
        Vector normal = intersection.normalBeforeHit;
        Ray reflectedRay = constructReflectedRay(intersection);
        Ray refractedRay = constructRefractedRay(intersection);

        Material material = intersection.geometry.getMaterial();

        Color reflectedColor = calcGlobalEffect(reflectedRay, level, k, material.kR);
        Color refractedColor = calcGlobalEffect(refractedRay, level, k, material.kT);
        //System.out.println("Local color: " + reflectedColor.add(refractedColor));//*********************************************************************************

        return reflectedColor.add(refractedColor);
    }

    /**
     * Calculates the global effect (reflection or refraction) by tracing a secondary ray.
     *
     * @param ray   the secondary ray (reflected or refracted)
     * @param level recursion level
     * @param k     accumulated attenuation so far
     * @param kx    the transparency/reflection coefficient of the geometry
     * @return the color contribution from the global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Intersection intersection = findClosestIntersection(ray);
        if (intersection == null) {
            return scene.background;
        }

        // Ensure the ray direction is recorded for this intersection
        intersection.rayDirection = ray.getDirection();

        return calcColor(intersection, level - 1, k.product(kx)).scale(kx);
    }


}