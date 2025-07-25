package renderer;

import static java.awt.Color.BLUE;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.*;

import java.util.List;

/**
 * Test rendering a basic image
 * @author Dan Zilberstein
 */
class LightsTests {
    /** Default constructor to satisfy JavaDoc generator */
    LightsTests() { /* to satisfy JavaDoc generator */ }

    /** First scene for some of tests */
    private final Scene          scene1                  = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(38, 38, 38)));
    /** Second scene for some of tests */
    private final Scene          scene2                  = new Scene("Test scene")
            .setAmbientLight(new AmbientLight(new Color(38, 38, 38)));

    /** First camera builder for some of tests */
    private final Camera.Builder camera1                 = Camera.getBuilder()                                          //
            .setRayTracer(scene1, RayTracerType.SIMPLE)                                                                      //
            .setLocation(new Point(0, 0, 1000))                                                                              //
            .setDirection(Point.ZERO, Vector.AXIS_Y)                                                                         //
            .setVpSize(150, 150).setVpDistance(1000);

    /** Second camera builder for some of tests */
    private final Camera.Builder camera2                 = Camera.getBuilder()                                          //
            .setRayTracer(scene2, RayTracerType.SIMPLE)                                                                      //
            .setLocation(new Point(0, 0, 1000))                                                                              //
            //.setDirection(Point.ZERO, Vector.AXIS_Y)//************************************************************************8
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpSize(200, 200).setVpDistance(1000);

    /** Shininess value for most of the geometries in the tests */
    private static final int     SHININESS               = 301;
    /** Diffusion attenuation factor for some of the geometries in the tests */
    private static final double  KD                      = 0.5;
    /** Diffusion attenuation factor for some of the geometries in the tests */
    private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);

    /** Specular attenuation factor for some of the geometries in the tests */
    private static final double  KS                      = 0.5;
    /** Specular attenuation factor for some of the geometries in the tests */
    private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);

    /** Material for some of the geometries in the tests */
    private final Material       material                = new Material().setKD(KD3).setKS(KS3).setShininess(SHININESS);
    /** Light color for tests with triangles */
    private final Color          trianglesLightColor     = new Color(800, 500, 250);
    /** Light color for tests with sphere */
    private final Color          sphereLightColor        = new Color(800, 500, 0);
    /** Color of the sphere */
    private final Color          sphereColor             = new Color(BLUE).reduce(2);

    /** Center of the sphere */
    private final Point          sphereCenter            = new Point(0, 0, -50);
    /** Radius of the sphere */
    private static final double  SPHERE_RADIUS           = 50d;

    /** The triangles' vertices for the tests with triangles */
    private final Point[]        vertices                =
            {
                    // the shared left-bottom:
                    new Point(-110, -110, -150),
                    // the shared right-top:
                    new Point(95, 100, -150),
                    // the right-bottom
                    new Point(110, -110, -150),
                    // the left-top
                    new Point(-75, 78, 100)
            };
    /** Position of the light in tests with sphere */
    private final Point          sphereLightPosition     = new Point(-50, -50, 25);
    /** Light direction (directional and spot) in tests with sphere */
    private final Vector         sphereLightDirection    = new Vector(1, 1, -0.5);
    /** Position of the light in tests with triangles */
    private final Point          trianglesLightPosition  = new Point(30, 10, -100);
    /** Light direction (directional and spot) in tests with triangles */
    private final Vector         trianglesLightDirection = new Vector(-2, -2, -2);

    /** The sphere in appropriate tests */
    private final Geometry       sphere                  = new Sphere(sphereCenter, SPHERE_RADIUS)
            .setEmission(sphereColor).setMaterial(new Material().setKD(KD).setKS(KS).setShininess(SHININESS));
    /** The first triangle in appropriate tests */
    private final Geometry       triangle1               = new Triangle(vertices[3], vertices[1], vertices[0])
           .setMaterial(material);
    /** The first triangle in appropriate tests */
    private final Geometry       triangle2               = new Triangle(vertices[2], vertices[1], vertices[0])
            .setMaterial(material);
    /** Produce a picture of a sphere lighted by a directional light */
    @Test
    void sphereDirectional() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new DirectionalLight(sphereLightColor, sphereLightDirection));

        camera1 //
                .setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightSphereDirectional");
    }

    /** Produce a picture of a sphere lighted by a point light */
    @Test
    void spherePoint() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(sphereLightColor, sphereLightPosition) //
                .setKl(0.001).setKq(0.0002));

        camera1 //
                .setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightSpherePoint");
    }

    /** Produce a picture of a sphere lighted by a spotlight */
    @Test
    void sphereSpot() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(sphereLightColor, sphereLightPosition, sphereLightDirection) //
                .setKl(0.001).setKq(0.0001));

        camera1 //
                .setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightSphereSpot");
    }

    /** Produce a picture of two triangles lighted by a directional light */
    @Test
    void trianglesDirectional() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new DirectionalLight(trianglesLightColor, trianglesLightDirection));

        camera2.setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightTrianglesDirectional");
    }

    /** Produce a picture of two triangles lighted by a point light */
    @Test
    void trianglesPoint() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new PointLight(trianglesLightColor, trianglesLightPosition) //
                .setKl(0.001).setKq(0.0002));

        camera2.setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightTrianglesPoint");
    }

    /** Produce a picture of two triangles lighted by a spotlight */
    @Test
    void trianglesSpot() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection) //
                .setKl(0.001).setKq(0.0001));

        camera2.setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightTrianglesSpot");
    }

    /** Produce a picture of a sphere lighted by a narrow spotlight */
    @Test
    void sphereSpotSharp() {
        scene1.geometries.add(sphere);
        scene1.lights
                .add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5)) //
                        .setKl(0.001).setKq(0.00004).setBeamExponent(10));

        camera1.setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightSphereSpotSharp");
    }

    /** Produce a picture of two triangles lighted by a narrow spotlight */
    @Test
    void trianglesSpotSharp() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection) //
                .setKl(0.001).setKq(0.00004).setBeamExponent(10));

        camera2.setResolution(500, 500) //
                .build() //
                .renderImage() //
                .writeToImage("lightTrianglesSpotSharp");
    }

    /**Our methods**/
    /// Test rendering triangles with dramatic and colorful multiple light sources
    @Test
    void trianglesMultipleLights() {
        scene2.geometries.add(triangle1, triangle2);

        scene2.lights.addAll(List.of(
                // Neutral white directional light from the front
                new DirectionalLight(new Color(500, 500, 500), new Vector(-1, -1, -1)),
                new DirectionalLight(new Color(200, 200, 255), new Vector(0, 0, -1)),

                // Greenish point light from left-top
                new PointLight(new Color(0, 600, 200), new Point(-80, 80, -50))
                        .setKl(0.001).setKq(0.0002),

                // Reddish spotlight from bottom-right
                new SpotLight(new Color(800, 200, 200), new Point(100, -100, 0), new Vector(-2, 2, -2))
                        .setKl(0.001).setKq(0.0001).setBeamExponent(12)
        ));

        camera2.setResolution(600, 600) //
                .build() //
                .renderImage() //
                .writeToImage("enhancedLightTrianglesMultiple");
    }

    /// Test rendering a sphere with strong and distinct multiple light sources
    @Test
    void sphereMultipleLights() {
        // הוספת AmbientLight
        scene1.setAmbientLight(new AmbientLight(new Color(50, 50, 50)));
        scene1.geometries.add(sphere);
        // נקודות מקור האור
        Point lightPos1 = new Point(-50, -50, 25);  // שמאל-מלמעלה
        Point lightPos2 = new Point(50, 50, 25);    // ימין-מלמעלה

        // חישוב כיוונים נכונים לכדור (מהאור לכדור)
        Vector dir1 = sphereCenter.subtract(lightPos1).normalize(); // (0,0,-50) - (-50,-50,25) = (50,50,-75)
        Vector dir2 = sphereCenter.subtract(lightPos2).normalize(); // (0,0,-50) - (50,50,25) = (-50,-50,-75)

        scene1.lights.addAll(List.of(
                // DirectionalLight - אור כיווני חזק
                new DirectionalLight(new Color(600, 300, 100), new Vector(1, 1, -1)),

                // PointLight - במקום שמשתמשים בו בטסטים האחרים
                new PointLight(new Color(800, 500, 0), lightPos1) // משתמש במיקום המוגדר כבר
                        .setKl(0.001).setKq(0.0002),

                // SpotLight - גם במקום שמשתמשים בו בטסטים האחרים
                new SpotLight(new Color(800, 400, 400), sphereLightPosition, dir2) // משתמש במיקום וכיוון המוגדרים
                        .setKl(0.001).setKq(0.0001).setBeamExponent(5)
        ));

        camera1.setResolution(600, 600)
                .build()
                .renderImage()
                .writeToImage("enhancedLightSphereMultiple");
    }

    // או גרסה עם חישוב כיוונים נכונים:
    @Test
    void sphereMultipleLightsFixed() {
        scene1.setAmbientLight(new AmbientLight(new Color(50, 50, 50)));
        scene1.geometries.add(sphere);

        // נקודות מקור האור
        Point lightPos1 = new Point(-50, -50, 25);  // שמאל-מלמעלה
        Point lightPos2 = new Point(50, 50, 25);    // ימין-מלמעלה

        // חישוב כיוונים נכונים לכדור (מהאור לכדור)
        Vector dir1 = sphereCenter.subtract(lightPos1).normalize(); // (0,0,-50) - (-50,-50,25) = (50,50,-75)
        Vector dir2 = sphereCenter.subtract(lightPos2).normalize(); // (0,0,-50) - (50,50,25) = (-50,-50,-75)

        scene1.lights.addAll(List.of(
                // DirectionalLight - אור כללי
                new DirectionalLight(new Color(400, 400, 400), new Vector(1, 1, -1)),

                // PointLight - אור נקודתי חזק
                new PointLight(new Color(800, 400, 200), lightPos1)
                        .setKl(0.001).setKq(0.0002),

                // SpotLight - זרקור מכוון לכדור
                new SpotLight(new Color(600, 600, 800), lightPos2, dir2)
                        .setKl(0.001).setKq(0.0001).setBeamExponent(3)
        ));

        camera1.setResolution(600, 600)
                .build()
                .renderImage()
                .writeToImage("sphereMultipleLightsFixed");
    }


}