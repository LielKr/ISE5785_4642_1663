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

    @Override

    public List<Point> findIntersections(Ray ray) {
        List<Point> pointList = null;
        for (Intersectable item : geometries) {
            List<Point> itemPointList = item.findIntersections(ray);
            if (itemPointList != null) {
                if (pointList == null)
                    pointList = new LinkedList<>(itemPointList);
                else
                    pointList.addAll(itemPointList);
            }
        }
        return pointList;
    }
}
