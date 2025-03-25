package primitives;

public class Vector extends Point
{


    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    public Vector (Double3 double3)
    {
      super(double3);
        if (double3.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }


    public Vector add(Vector vector)
    {
        return new Vector(this.xyz.add(vector.xyz));
    }
    public Vector scale(double scalar)
    {
        return new Vector(this.xyz.scale(scalar));
    }
    public double dotProduct(Vector v)
    {
        return this.xyz.d1 * v.xyz.d1 + this.xyz.d2 *v.xyz.d2+ this.xyz.d3 * v.xyz.d3;
        //return this.xyz.d1 * v.xyz.d1 + this.xyz.d2 * v.xyz.d2 + this.xyz.d3 * v.xyz.d3;
    }
    public Vector crossProduct(Vector v)
    {
        return new Vector(this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2, this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
                this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }
    public double lengthSquared()
    {
        return this.dotProduct(this);
    }

    public double length()
    {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize()
    {
        return new Vector(this.xyz.d1 / length(), this.xyz.d2 / length(), this.xyz.d3/ length());
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        return (obj instanceof Vector other)
                && super.equals((Point)other);

    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }



}
