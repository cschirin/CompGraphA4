/**
* Praktikum WPCG, WS 2014/2015
* Bearbeitet am 31.Oktober.2014 und 3.November.2014
* Gruppe: Andreas Mauritz (Andreas.Mauritz@haw-hamburg.de),
*         Christian Schirin (Christian.Schirin@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 3, Aufgabe 1,2,3 und Bonus
* Verwendete Quellen: -
* */


package computergraphics.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.DoubleBinaryOperator;

import javax.imageio.ImageIO;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;

/** Diese klasse stellt factory-methoden bereit, um ein höhenfeld zu erstellen.
 * Ein höhenfeld ist ein dreiecksnetz in gitterstruktur, bei dem die y-werte
 * aus einem bild ausgelesen wurden oder prozedural generiert wurden. Das gitter
 * netz befindet sich im einheitswürfel.
 * @author Christian Schirin, Andreas Mauritz
 */
public class Heightfield {
    /** Konstante, um magic numbers in berechnungen zu vermeiden */
    private static final double MAX_COLOR_VALUE = 255.0;
    
    /** Erstellt ein höhenfeld, basierend auf 2 übergebenen bildern welche
     * die das höhenfeld und eine färbung der dreiecke darstellung sollen
     * @param resolution Die auflösung des höhenfeldes in beide richtungen
     * @param heightfieldPath Pfad der Bilddatei mit den höhenwerten
     * @param colorPath Pfad der Bilddatei mit den Farbwerten
     * @param maxHeight Maximaler höhenwert in der szene (dieser entspricht weiß)
     * @return Dreiecksnetz das ein höhenfeld darstellen soll. 
     * @throws IOException 
     */
    public static ITriangleMesh makeField(int resolution,String heightfieldPath,
            String colorPath, double maxHeight) throws IOException  {
        TriangleMesh heightfield = new TriangleMesh();
        
        BufferedImage colorImage = ImageIO.read(new File(colorPath));
        makeVerticesFromImage(resolution, heightfieldPath, maxHeight,
                heightfield, colorImage);
        makeTriangles(resolution, heightfield);
        
        return heightfield;
        
    }
    
    public static ITriangleMesh makeField(int resolution, String colorPath,
           DoubleBinaryOperator function, double maxHeight) throws IOException {
        TriangleMesh heightfield = new TriangleMesh(); 
        
        BufferedImage colorImage = ImageIO.read(new File(colorPath));
        makeVerticesFromFunction(resolution,function,maxHeight,heightfield,
                colorImage);
        makeTriangles(resolution, heightfield);
        
        return heightfield;
    }

    private static void makeVerticesFromFunction(int resolution,
            DoubleBinaryOperator function, double maxHeight,
            TriangleMesh heightfield, BufferedImage colorImage) {
        /* Für ein höhenfeld muss man die vertices erzeugen, die dreiecke 
         * erzeugen,die y-werte in die vertices eintragen, die farben in die 
         * vertices eintragen. 
         */
        double intervalImageX = colorImage.getWidth() / resolution;
        double intervalImageZ = colorImage.getHeight() / resolution;
        double intervalMesh = 1.0 / resolution;
        // Erste Schleife: Vertices komplett erzeugen, mit farben und höhenwerten
        for(int z = resolution-1; z >= 0; z--) {
            for(int x = 0; x < resolution; x++) {
                double vertexHeight = 
                        function.applyAsDouble(x*intervalMesh,z*intervalMesh);
                //verrechnen mit maximal erlaubter höhe
                vertexHeight *= maxHeight; 
                Color vertexColor = new Color(colorImage
                        .getRGB((int)(x * intervalImageX),(int)(z * intervalImageZ)));
                Vertex vertex = new Vertex(
                        new Vector3(x*intervalMesh, vertexHeight, z*intervalMesh)
                        );
                vertex.setColor(new Vector3(
                        vertexColor.getRed()/MAX_COLOR_VALUE, 
                        vertexColor.getGreen()/MAX_COLOR_VALUE,
                        vertexColor.getBlue()/MAX_COLOR_VALUE
                        )
                );
                heightfield.addVertex(vertex);
            }
        }
    }

    private static void makeTriangles(int resolution, TriangleMesh heightfield) {
        //Zweite schleife: Dreiecke in die menge von vertices einfügen
        for (int z = 0; z < resolution-1; z++) { //res-1 unterer rand weglassen
            for(int x = 0; x < resolution-1; x++) { //res-1 rechter rand weglassen 
                /* Wir berechnen uns den basis-vertex eines dreiecks basierend
                 * auf seinen x und z-koordinaten. 
                 */
                int currentVertex = x + resolution * z;
                
                Triangle triangle = new Triangle(currentVertex, 
                        currentVertex + resolution, currentVertex + resolution + 1);
                heightfield.addTriangle(triangle);
                triangle = new Triangle(currentVertex, currentVertex+resolution+1,
                        currentVertex+1);
                heightfield.addTriangle(triangle);
            }
        }
        //hier normalen berechnen, um Don't Repeat Yourself einzuhalten
        heightfield.calculateAllNormals(); 
    }

    private static void makeVerticesFromImage(int resolution,
            String heightfieldPath, double maxHeight, TriangleMesh heightfield,
            BufferedImage colorImage) throws IOException {
        BufferedImage heightfieldImage = ImageIO.read(new File(heightfieldPath));
        /* Für ein höhenfeld muss man die vertices erzeugen, die dreiecke 
         * erzeugen,die y-werte in die vertices eintragen, die farben in die 
         * vertices eintragen. 
         */
        double intervalImageX = heightfieldImage.getWidth() / resolution;
        double intervalImageZ = heightfieldImage.getHeight() / resolution;
        double intervalMesh = 1.0 / resolution;
        // Erste Schleife: Vertices komplett erzeugen, mit farben und höhenwerten
        for(int z = resolution-1; z >= 0; z--) {
            for(int x = 0; x < resolution; x++) {
                double vertexHeight = new Color(heightfieldImage
                        .getRGB((int)(x * intervalImageX),
                                (int)(z * intervalImageZ)))
                        .getRed()/MAX_COLOR_VALUE;
                //verrechnen mit maximal erlaubter höhe
                vertexHeight *= maxHeight; 
                Color vertexColor = new Color(colorImage
                        .getRGB((int)(x * intervalImageX),(int)(z * intervalImageZ)));
                Vertex vertex = new Vertex(
                        new Vector3(x*intervalMesh, vertexHeight, z*intervalMesh)
                        );
                vertex.setColor(new Vector3(
                        vertexColor.getRed()/MAX_COLOR_VALUE, 
                        vertexColor.getGreen()/MAX_COLOR_VALUE,
                        vertexColor.getBlue()/MAX_COLOR_VALUE
                        )
                );
                heightfield.addVertex(vertex);
            }
        }
    }

}
