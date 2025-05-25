package geometries;


import primitives.*;

import java.util.List;

public abstract class Intersectable {


    public static class Intersection {
        public final Geometry geometry;
        public final Point point;

        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof Intersection other)
                    && (this.geometry==other.geometry)
                    && this.point.equals(other.point);
        }
        @Override
        public String toString() {
            return "geometry: " + geometry + "point: " + point;
        }

    }
    public final List<Point> calculateIntersecionsHelper(Ray ray){
        var list = calculateIntersections(ray);
        return list == null ? null : list.stream().map(intersection -> intersection.point).toList();
    }

    public final List<Intersection> calculateIntersections(Ray ray)
    {
        return calculateIntersectionsHelper(ray);
    }

    protected List<Intersection> calculateIntersectionsHelper(Ray ray)
    {
        return null;
    }







}
