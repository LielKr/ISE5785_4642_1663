package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable>geometries=new LinkedList<Intersectable>();

    public Geometries(Intersectable... geometries) {
        add (geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    public Geometries() {

    }

    /**
     * Finds all intersection points of a given ray with the geometries in the collection.
     *
     * @param ray the ray for which intersection points are being searched
     * @return a list of points where the ray intersects with the geometries,
     *         or null if there are no intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> pointList = null;

        // Iterate through each geometry in the collection
        for (Intersectable item : geometries) {
            List<Point> itemPointList = item.findIntersections(ray);

            // If the current geometry has intersection points
            if (itemPointList != null) {
                if (pointList == null)
                    pointList = new LinkedList<>(itemPointList);
                else
                    pointList.addAll(itemPointList);
            }
        }

        // Return the list of intersection points, or null if no intersections were found
        return pointList;
    }

}
