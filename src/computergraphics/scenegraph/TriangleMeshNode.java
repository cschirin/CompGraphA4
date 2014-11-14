/**
* Praktikum WPCG, Wintersemester 2014/2015
* Gruppe: 
* Andreas Mauritz (Andreas.Mauritz@haw-hamburg.de)
* Christian Schirin (Christian.Schirin@haw-hamburg.de)
* Aufgabenblatt 4 (Praxis: Texture Mapping), Aufgabe 2
* Verwendete Quellen: -
* 
*/
package computergraphics.scenegraph;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;

public class TriangleMeshNode extends Node {
    
    private ITriangleMesh triangleMesh; //Unser polygonnetz 
    
    private int displayList; // index der display liste
    private boolean displayListMissing = true;
    
    
    /**
     * Default Constructor. 
     */
    public TriangleMeshNode(ITriangleMesh triangleMesh) {
        this.triangleMesh = triangleMesh;
    }
    
    private void initDisplayList(GL2 gl) {
        
        try {
            initTexture(gl);
        } catch (GLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Konnte Textur "+
            triangleMesh.getTextureFilename()+" nicht laden!");
            e.printStackTrace();
        }
        
        displayList = gl.glGenLists(1);
        
        gl.glNewList(displayList, GL2.GL_COMPILE);
        
        //The draw code starts here
             //Texture code starts here    
        
            //We need to iterate over all the Triangles
            int numberOfTriangles = triangleMesh.getNumberOfTriangles();
            
            //initialize drawing
            //gl.glColor3d(0, 0, 0); //red
            gl.glBegin(GL.GL_TRIANGLES);
            
            //Draw every Triangle
            for(int indexTriangle = 0;
                    indexTriangle < numberOfTriangles;
                    indexTriangle++) { 
                // Get the triangle from the Mesh
                Triangle triangle = triangleMesh.getTriangle(indexTriangle); 
                // Get the 3 vertices from the triangle
                Vertex vertexA = triangleMesh.getVertex(triangle.getA());
                Vertex vertexB = triangleMesh.getVertex(triangle.getB());
                Vertex vertexC = triangleMesh.getVertex(triangle.getC());
                
                //Get the position vectors for each Vertex
                Vector3 positionA = vertexA.getPosition();
                Vector3 positionB = vertexB.getPosition();
                Vector3 positionC = vertexC.getPosition();
                
                //Draw the Vertices based on the positions
                
                //Draw vertex A
                gl.glColor3d(vertexA.getColor().get(0), vertexA.getColor().get(1), vertexA.getColor().get(2));
                gl.glNormal3d(triangle.getNormal().get(0),triangle.getNormal().get(1),
                        triangle.getNormal().get(2));
                
                //Get texture coordinate for Vertex A
                Vector3 texCoordA = triangleMesh
                        .getTextureCoordinate(triangle.getTextureCoordinate(0));
                gl.glTexCoord2d(texCoordA.get(0),texCoordA.get(1));
                
                gl.glVertex3d(positionA.get(0),positionA.get(1),positionA.get(2));
                //Draw vertex B 
                gl.glColor3d(vertexB.getColor().get(0), vertexB.getColor().get(1), vertexB.getColor().get(2));
                gl.glNormal3d(triangle.getNormal().get(0),triangle.getNormal().get(1),
                        triangle.getNormal().get(2));
                
                //Get texture coordinate for Vertex B
                Vector3 texCoordB = triangleMesh
                        .getTextureCoordinate(triangle.getTextureCoordinate(1));
                gl.glTexCoord2d(texCoordB.get(0),texCoordB.get(1));
                
                gl.glVertex3d(positionB.get(0),positionB.get(1),positionB.get(2));
                //Draw vertex C 
                gl.glColor3d(vertexC.getColor().get(0), vertexC.getColor().get(1), vertexC.getColor().get(2));
                gl.glNormal3d(triangle.getNormal().get(0),triangle.getNormal().get(1),
                        triangle.getNormal().get(2));
                
                //Get texture coordinate for Vertex C
                Vector3 texCoordC = triangleMesh
                        .getTextureCoordinate(triangle.getTextureCoordinate(2));
                gl.glTexCoord2d(texCoordC.get(0),texCoordC.get(1));
                
                gl.glVertex3d(positionC.get(0),positionC.get(1),positionC.get(2));
                
                
            }
            gl.glEnd(); //end drawing
        //The draw code ends here
            
        gl.glEndList(); //Create the display list.
        
        displayListMissing = false; //We just created the display list
    }

    /**
     * Aktiviert Texturen in OpenGL, lädt eine textur aus einem bild, dessen
     * dateiname im dreiecksnetz angegeben ist, und bindet diese textur.
     * @throws IOException 
     * @throws GLException 
     */
    private void initTexture(GL2 gl) throws GLException, IOException {
        //"Opgengl mitteilen, dass wir texturen verwenden wollen" 
        gl.glEnable(GL.GL_TEXTURE_2D);
        
        //Textur einlesen
        File textureFile = new File(triangleMesh.getTextureFilename());
        //erstmal keine mipmaps benutzen.
        Texture texture = TextureIO.newTexture(textureFile, false); 
        
        //Textur binden
        gl.glBindTexture(texture.getTarget(), texture.getTextureObject());
        //obere zeile lässt sich auch als texture.bind(gl); schreiben
    }

    @Override
    public void drawGl(GL2 gl) {
        if(displayListMissing) {
        // "Insbesondere soll die Displayliste nur einmal erzeugt werden..."
            initDisplayList(gl);
        }
        //"... und zur laufzeit bei jedem Zeichnen lediglich aufgerufen werden"
        gl.glCallList(displayList); 

    }
    
    

}
