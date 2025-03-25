package primitives;



public class Point {
    public static final Point ZERO =new Point(Double3.ZERO);

    protected final Double3 xyz;

    public Point(double x, double y, double z) {
        xyz=new Double3(x,y,z);
    }

    public Point(Double3 xyz) {
        this.xyz=xyz;
    }



    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }

    public double distanceSquared(Point p1) {
        Double3 temp = this.xyz.subtract(p1.xyz);
        temp = temp.product(temp);
        return temp.d1 + temp.d2 + temp.d3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);

    }

    @Override
    public String toString()
    {
        return "" + xyz;
    }



}
