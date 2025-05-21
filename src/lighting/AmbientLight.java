package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    private final Color intensity;
 static final public AmbientLight NONE = new AmbientLight(Color.BLACK);
    // כרגע לא נדרש-
    public AmbientLight(Color intensity, Double3 double3) {
        this.intensity = intensity;
    }

    public AmbientLight(Color intensity)
    {
        this.intensity = intensity;
    }

    public Color getIntensity()
    { return intensity;}




}
