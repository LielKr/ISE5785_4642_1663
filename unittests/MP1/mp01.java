//package renderer;
//
//import geometries.*;
//import lighting.AmbientLight;
//import lighting.DirectionalLight;
//import lighting.PointLight;
//import lighting.SpotLight;
//import org.junit.jupiter.api.Test;
//import primitives.*;
//import scene.Scene;
//
//import static java.awt.Color.*;
//
//public class mP1 {
//    private final Scene scene = new Scene("Test scene");
//    private final Camera.Builder camera = Camera.getBuilder()     //
//            .setRayTracer(scene, RayTracerType.SIMPLE);        //(new Point(0, 280, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0))
//           camera.setVpDistance(1000)setVpSize(200, 200);
//            //.setRayTracer(new RayTracerBasic(scene));
//    Geometries geometries = new Geometries();
//    /**
//     * test the test to verify if the connection between shadows and lights works well
//     */
//    @Test
//    public void mP1() {
//        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
//
//
//        // Set up the scene
//        /*scene.lights.add( //
//                new SpotLight(new Color(700, 400, 400), new Point(1000, 150, 0), new Vector(-1, 0, 0)) //
//                        .setKl(1E-5).setKq(1.5E-7));
//        */
//        scene.setBackground(new Color(30, 30, 30));
//
//        // Create geometries
//
//        Plane plane1=(Plane)new Plane(new Point(0,300,0),new Vector(0,1,0)).setEmission(new Color(157,138,117))
//                .setMaterial(new Material().setShininess(30).setKD(0.3).setKS(0.3));
//        geometries.add(plane1);
//        Plane plane2=(Plane)new Plane(new Point(300,0,0),new Vector(1,0,0)).setEmission(new Color(157,138,117))
//                .setMaterial(new Material().setShininess(30).setKD(0.3).setKS(0.3));
//        geometries.add(plane2);
//        Plane plane3=(Plane)new Plane(new Point(-300,0,0),new Vector(1,0,0)).setEmission(new Color(157,138,117))
//                .setMaterial(new Material().setShininess(30).setKD(0.3).setKS(0.3));
//        geometries.add(plane3);
//        Plane plane4=(Plane)new Plane(new Point(0,0,0),new Vector(0,1,0)).setEmission(new Color(157,138,117))
//                .setMaterial(new Material().setShininess(30).setKD(0.3).setKS(0.3));
//        geometries.add(plane4);
//
//        Plane plane5=(Plane)new Plane(new Point(0,0,-500),new Vector(0,0,1)).setEmission(new Color(157,138,117))
//                .setMaterial(new Material().setShininess(30).setKD(0.3).setKS(0.3));
//        geometries.add(plane5);
//
//        Polygon surfaceBillard=(Polygon) new Polygon(new Point(-150,110,-350),new Point(-23,110,-350),new Point(-23,110,-105),new Point(-150,110,-105))
//                .setEmission(new Color(GREEN))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(surfaceBillard);
//
//        Polygon cot1Billard=(Polygon) new Polygon(new Point(-150,110,-350),new Point(-23,110,-350),new Point(-23,70,-350),new Point(-150,70,-350))
//                .setEmission(new Color(54,23,0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(cot1Billard);
//
//        Polygon cot2Billard=(Polygon) new Polygon(new Point(-150,110,-350),new Point(-150,110,-105),new Point(-150,70,-105),new Point(-150,70,-350))
//                .setEmission(new Color(54,23,0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(cot2Billard);
//
//        Polygon cot3Billard=(Polygon) new Polygon(new Point(-23,110,-350),new Point(-23,110,-105),new Point(-23,70,-105),new Point(-23,70,-350))
//                .setEmission(new Color(54,23,0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(cot3Billard);
//
//        Polygon cot4Billard=(Polygon) new Polygon(new Point(-23,110,-105),new Point(-150,110,-105),new Point(-150,70,-105),new Point(-23,70,-105))
//                .setEmission(new Color(54,23,0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(cot4Billard);
//
//        // Pied 1
//        Point pied1A = new Point(-150, 70, -350);
//        Point pied1B = new Point(-143, 70, -350);
//        Point pied1C = new Point(-143, 0, -350);
//        Point pied1D = new Point(-150, 0, -350);
//        Polygon pied1 = (Polygon) new Polygon(pied1A, pied1B, pied1C, pied1D)
//                .setEmission(new Color(54, 23, 0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(pied1);
//
//// Pied 2
//        Point pied2A = new Point(-150, 70, -105);
//        Point pied2B = new Point(-143, 70, -105);
//        Point pied2C = new Point(-143, 0, -105);
//        Point pied2D = new Point(-150, 0, -105);
//        Polygon pied2 = (Polygon) new Polygon(pied2A, pied2B, pied2C, pied2D)
//                .setEmission(new Color(54, 23, 0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(pied2);
//
//// Pied 3
//        Point pied3A = new Point(-23, 70, -350);
//        Point pied3B = new Point(-30, 70, -350);
//        Point pied3C = new Point(-30, 0, -350);
//        Point pied3D = new Point(-23, 0, -350);
//        Polygon pied3 = (Polygon) new Polygon(pied3A, pied3B, pied3C, pied3D)
//                .setEmission(new Color(54, 23, 0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(pied3);
//
//// Pied 4
//        Point pied4A = new Point(-23, 70, -105);
//        Point pied4B = new Point(-30, 70, -105);
//        Point pied4C = new Point(-30, 0, -105);
//        Point pied4D = new Point(-23, 0, -105);
//        Polygon pied4 = (Polygon) new Polygon(pied4A, pied4B, pied4C, pied4D)
//                .setEmission(new Color(54, 23, 0))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(pied4);
//
//// Définition des dimensions du bureau
//        double bureauLength = 200; // Longueur du bureau
//        double bureauWidth = 100; // Largeur du bureau
//        double bureauHeight = 5; // Hauteur du bureau (épaisseur de la base)
//
//// Points pour la base du bureau
//        Point bureauBaseA = new Point(150, 110, -350);
//        Point bureauBaseB = new Point(150 + bureauLength, 110, -350);
//        Point bureauBaseC = new Point(150 + bureauLength, 110, -350 - bureauWidth);
//        Point bureauBaseD = new Point(150, 110, -350 - bureauWidth);
//
//// Création de la base du bureau en utilisant un polygone rectangulaire
//        Polygon bureauBase = (Polygon) new Polygon(bureauBaseA, bureauBaseB, bureauBaseC, bureauBaseD)
//                .setEmission(new Color(GRAY))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(bureauBase);
//
//        // Points pour la face arrière du bureau
//        Point bureauBackA = bureauBaseA.add(new Vector(0, bureauHeight, 0));
//        Point bureauBackB = bureauBaseB.add(new Vector(0, bureauHeight, 0));
//        Point bureauBackC = bureauBaseB.add(new Vector(0, bureauHeight, -bureauWidth));
//        Point bureauBackD = bureauBaseA.add(new Vector(0, bureauHeight, -bureauWidth));
//
//// Création de la face arrière du bureau
//        Polygon bureauBack = (Polygon) new Polygon(bureauBackA, bureauBackB, bureauBackC, bureauBackD)
//                .setEmission(new Color(GRAY))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(bureauBack);
//
//// Points pour la face avant du bureau
//        Point bureauFrontA = bureauBaseA;
//        Point bureauFrontB = bureauBaseB;
//        Point bureauFrontC = bureauBaseB.add(new Vector(0, 0, -bureauWidth));
//        Point bureauFrontD = bureauBaseA.add(new Vector(0, 0, -bureauWidth));
//
//// Création de la face avant du bureau
//        Polygon bureauFront = (Polygon) new Polygon(bureauFrontA, bureauFrontB, bureauFrontC, bureauFrontD)
//                .setEmission(new Color(GRAY))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(bureauFront);
//
//// Points pour la face droite du bureau
//        Point bureauRightA = bureauBaseB;
//        Point bureauRightB = bureauBaseB.add(new Vector(0, bureauHeight, 0));
//        Point bureauRightC = bureauBaseB.add(new Vector(0, bureauHeight, -bureauWidth));
//        Point bureauRightD = bureauBaseB.add(new Vector(0, 0, -bureauWidth));
//
//// Création de la face droite du bureau
//        Polygon bureauRight = (Polygon) new Polygon(bureauRightA, bureauRightB, bureauRightC, bureauRightD)
//                .setEmission(new Color(GRAY))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(bureauRight);
//
//// Points pour la face gauche du bureau
//        Point bureauLeftA = bureauBaseA;
//        Point bureauLeftB = bureauBaseA.add(new Vector(0, bureauHeight, 0));
//        Point bureauLeftC = bureauBaseA.add(new Vector(0, bureauHeight, -bureauWidth));
//        Point bureauLeftD = bureauBaseA.add(new Vector(0, 0, -bureauWidth));
//
//// Création de la face gauche du bureau
//        Polygon bureauLeft = (Polygon) new Polygon(bureauLeftA, bureauLeftB, bureauLeftC, bureauLeftD)
//                .setEmission(new Color(GRAY))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(bureauLeft);
//
//// Points pour la face supérieure du bureau
//        Point bureauTopA = bureauBaseA.add(new Vector(0, bureauHeight, 0));
//        Point bureauTopB = bureauBaseB.add(new Vector(0, bureauHeight, 0));
//        Point bureauTopC = bureauBaseB;
//        Point bureauTopD = bureauBaseA;
//
//// Création de la face supérieure du bureau
//        Polygon bureauTop = (Polygon) new Polygon(bureauTopA, bureauTopB, bureauTopC, bureauTopD)
//                .setEmission(new Color(GRAY))
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3));
//        geometries.add(bureauTop);
//
//        // Polygone rectangle debout
//        Point corner1 = new Point(-250, 0, -499);
//        Point corner2 = new Point(-50, 0, -499);
//        Point corner3 = new Point(-50, 200, -499);
//        Point corner4 = new Point(-250, 200, -499);
//        Polygon rectangle = (Polygon) new Polygon(corner1, corner2, corner3, corner4)
//                .setEmission(new Color(0, 0, 0))
//                .setMaterial(new Material().setKD(0.3).setKS(0.3).setKT(0.2).setShininess(30).setKR(1.)); // Réglages des propriétés matérielles
//        geometries.add(rectangle);
//
//
//
//
//        // Boule 1 (couleur rouge)
//        Sphere sphere4 = (Sphere) new Sphere(new Point(-120, 113, -200), 3.)
//                .setEmission(new Color(255, 0, 0)) // Couleur rouge
//                .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.).setShininess(300));
//        geometries.add(sphere4);
//
//// Boule 2 (couleur bleue)
//        Sphere sphere2 = (Sphere) new Sphere(new Point(-90, 113, -227), 3.)
//                .setEmission(new Color(0, 0, 255)) // Couleur bleue
//                .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.).setShininess(300));
//        geometries.add(sphere2);
//
//// Boule 3 (couleur blanche)
//        Sphere sphere3 = (Sphere) new Sphere(new Point(-45, 113, -240), 3.)
//                .setEmission(new Color(255, 255, 255)) // Couleur blanche
//                .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.).setShininess(300));
//        geometries.add(sphere3);
//
//
//
//// Points pour le pavé de verre
//        Point verreBaseA = new Point(150,110,-350);
//        Point verreBaseB = new Point(350,110,-350);
//        Point verreBaseC = new Point(350,180,-350);
//        Point verreBaseD = new Point(150,180,-350);
//
//// Création du pavé de verre en utilisant un polygone rectangulaire
//        Polygon verre = (Polygon) new Polygon(verreBaseA, verreBaseB, verreBaseC, verreBaseD)
//                .setEmission(new Color(24,40,39))
//                .setMaterial(new Material().setKD(0.).setKS(0.).setKT(1).setShininess(0).setKR(0.001)); // Propriétés matérielles
//        geometries.add(verre);
//
//        double polyRadius = 30.0;
//        int numSides = 12;
//        double height = 300;
//        double offset = 200;
//
//        generatePillar(polyRadius, numSides, height, offset);
//
//        double polyRadius1 = 30.0;
//        double polyRadius2 = 30.0;
//        int numSides1 = 12;
//        double height1 = 300.0;
//        double offset1 = 0.;
//        double offset2 = 0.;
//        double centerX = 200.0;
//        double centerY = 0.0;
//        double centerZ = 400.0;
//        Color color = new Color(157, 138, 117);
//        double kd = 0.5;
//        double ks = 0.5;
//        int shininess = 30;
//        double kr = 0.0; // Valeur par défaut : 0.0
//        double kt = 0.0; // Valeur par défaut : 0.0
//
//        generatePillar(polyRadius1, polyRadius2, numSides1, height1, offset1, offset2, centerX, centerY, centerZ,0.,0., color, kd, ks, shininess, kr, kt);
//        generatePillar(5.,10,5,20,0.,0.,200.,110.,-375.,0.,0.,new Color(13,232,1),0.5,0.5,70,0.3,0.);
//        //quille
//        generatePillar(40.,70,50,50,0.,0.,50.,0.,-375.,0.,0.,new Color(BLACK),0.5,0.5,70,0.,0.);
//        generatePillar(3,10,20,20,0.,0.,275,140,-375.,0.,0.,new Color(90,0,112),0.5,0.5,100,0.3,0.);
//        generatePillar(6,5,20,2,0.,0.,50,50,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(7,6,20,3,0.,0.,50,52,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(7,7,20,3,0.,0.,50,55,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(6,7,20,3,0.,0.,50,58,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(5,6,20,2,0.,0.,50,61,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(4,5,20,2,0.,0.,50,62,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(3,4,20,2,0.,0.,50,64,-375.,0.,0.,new Color(RED),0.5,0.5,100,0.3,0.);
//        generatePillar(2.5,3,20,1.5,0.,0.,50,66,-375.,0.,0.,new Color(WHITE),0.5,0.5,100,0.3,0.);
//        generatePillar(3,2.5,20,1.5,0.,0.,50,67.5,-375.,0.,0.,new Color(WHITE),0.5,0.5,100,0.3,0.);
//        generatePillar(4,3,20,2,0.,0.,50,69,-375.,0.,0.,new Color(RED),0.5,0.5,100,0.3,0.);
//        generatePillar(4.5,4,20,1,0.,0.,50,71,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(4.5,4.5,20,2,0.,0.,50,72,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(4,4.5,20,1.5,0.,0.,50,74,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(3,4,20,1.5,0.,0.,50,74.5,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//        generatePillar(1,3,20,1.5,0.,0.,50,76,-375.,0.,0.,new Color(white),0.5,0.5,100,0.3,0.);
//
//
//
//
//
//
//
//        //door
//        generatePillar(45,45,4,200,0.,0.,-344,0,0,0.,0.,new Color(47,72,110),0.5,0.5,100,0.3,0.);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////GEOMETRIESS LUMIERES
//
//        Sphere lum = (Sphere) new Sphere(new Point(200, 295, 2000), 5.)
//                .setEmission(new Color(YELLOW))
//                .setMaterial(new Material().setKD(0.).setKS(0.).setKT(0.5).setShininess(30));
//        geometries.add(lum);
//
//        Sphere sphere1 = (Sphere) new Sphere(new Point(-270, 270, -470), 5.)
//                .setEmission(new Color(YELLOW))
//                .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.5).setShininess(30));
//        geometries.add(sphere1);
//
//        Sphere sphere5 = (Sphere) new Sphere(new Point(0,300,0), 30.)
//                .setEmission(new Color(white)) // Couleur bleue
//                .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.5).setShininess(30).setKR(0.2));
//        geometries.add(sphere5);
//        Sphere sphere6 = (Sphere) new Sphere(new Point(0, 50, 500), 50.)
//                .setEmission(new Color(250,11,102))
//                .setMaterial(new Material().setKD(0.3).setKS(0.3).setKT(0.5).setShininess(30).setKR(0.3));
//        geometries.add(sphere6);
//
//
//
//
//
//        scene.geometries = geometries;
//
//        scene.lights.add(new PointLight(new Color(100,100,100),new Point(-270, 270, -470)));
//        scene.lights.add(new PointLight(new Color(100,100,100),new Point(0, 299, 0)));
//        scene.lights.add(new PointLight(new Color(100,100,100),new Point(200, 295, 2000)));
//        scene.lights.add(new SpotLight(new Color(MAGENTA),new Point(275,139,-375),new Vector(0,-1,0)));
//
//
//        // Set up the camera and render the image
//        camera.setImageWriter(new ImageWriter("imgMP1", 1500, 1000))
//                .setMultithreading(4)
//                .setUseAntiAliasing(true)
//                //.setAliasingRays(10)
//                .renderImage()
//                .writeToImage();
//    }
//
//    public void generatePillar(double polyRadius, int numSides, double height, double offset) {
//        // Calcul de l'angle entre les côtés du polygone
//        double angle = 2 * Math.PI / numSides;
//
//        // Points pour le premier polygone
//        Point[] polyPoints1 = new Point[numSides];
//
//        // Points pour le deuxième polygone
//        Point[] polyPoints2 = new Point[numSides];
//
//        // Calcul des coordonnées des points du premier polygone
//        for (int i = 0; i < numSides; i++) {
//            double x = polyRadius * Math.cos(i * angle) - offset;
//            double z = polyRadius * Math.sin(i * angle) + offset;
//            polyPoints1[i] = new Point(x, height, z);
//        }
//
//        // Calcul des coordonnées des points du deuxième polygone
//        for (int i = 0; i < numSides; i++) {
//            double x = polyRadius * Math.cos(i * angle) - offset;
//            double z = polyRadius * Math.sin(i * angle) + offset;
//            polyPoints2[i] = new Point(x, 0, z);
//        }
//
//        // Création du premier polygone
//        Polygon polygon1 = (Polygon) new Polygon(polyPoints1)
//                .setEmission(new Color(157, 138, 117)) // Couleur du polygone (vert)
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3)); // Propriétés matérielles
//        geometries.add(polygon1);
//
//        // Création du deuxième polygone
//        Polygon polygon2 = (Polygon) new Polygon(polyPoints2)
//                .setEmission(new Color(157, 138, 117)) // Couleur du polygone (vert)
//                .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3)); // Propriétés matérielles
//        geometries.add(polygon2);
//
//        // Création des rectangles reliant les côtés des polygones
//        for (int i = 0; i < numSides; i++) {
//            Polygon rectangle = (Polygon) new Polygon(
//                    polyPoints1[i],
//                    polyPoints2[i],
//                    polyPoints2[(i + 1) % numSides],
//                    polyPoints1[(i + 1) % numSides]
//            ).setEmission(new Color(157, 138, 117)) // Couleur du polygone (vert)
//                    .setMaterial(new Material().setShininess(100).setKD(0.3).setKS(0.3)); // Propriétés matérielles
//            geometries.add(rectangle);
//        }
//    }
//
//    public void generatePillar(double polyRadius1, double polyRadius2, int numSides, double height,
//                               double offset1, double offset2, double centerX, double centerY, double centerZ,
//                               double xOffset, double zOffset, Color color, double kd, double ks, int shininess,
//                               double kr, double kt) {
//
//        double angle = 2 * Math.PI / numSides;
//        Point[] polyPoints1 = new Point[numSides];
//        Point[] polyPoints2 = new Point[numSides];
//
//        for (int i = 0; i < numSides; i++) {
//            double x1 = polyRadius1 * Math.cos(i * angle) + centerX - offset1;
//            double y1 = centerY + height;
//            double z1 = polyRadius1 * Math.sin(i * angle) + centerZ + offset1;
//            polyPoints1[i] = new Point(x1, y1, z1);
//
//            double x2 = polyRadius2 * Math.cos(i * angle) + centerX - offset2 + xOffset;
//            double y2 = centerY;
//            double z2 = polyRadius2 * Math.sin(i * angle) + centerZ + offset2 + zOffset;
//            polyPoints2[i] = new Point(x2, y2, z2);
//        }
//
//        Polygon polygon1 = (Polygon) new Polygon(polyPoints1)
//                .setEmission(color)
//                .setMaterial(new Material().setKD(kd).setKS(ks).setShininess(shininess).setKR(kr).setKT(kt));
//        geometries.add(polygon1);
//
//        Polygon polygon2 = (Polygon) new Polygon(polyPoints2)
//                .setEmission(color)
//                .setMaterial(new Material().setKD(kd).setKS(ks).setShininess(shininess).setKR(kr).setKT(kt));
//        geometries.add(polygon2);
//
//        for (int i = 0; i < numSides; i++) {
//            Polygon rectangle = (Polygon) new Polygon(
//                    polyPoints1[i],
//                    polyPoints2[i],
//                    polyPoints2[(i + 1) % numSides],
//                    polyPoints1[(i + 1) % numSides]
//            ).setEmission(color)
//                    .setMaterial(new Material().setKD(kd).setKS(ks).setShininess(shininess).setKR(kr).setKT(kt));
//            geometries.add(rectangle);
//        }
//    }
//}