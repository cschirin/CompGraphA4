package computergraphics.scenegraph;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

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
        displayList = gl.glGenLists(1);
        
        gl.glNewList(displayList, GL2.GL_COMPILE);
        
        //The draw code starts here
            
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
                //System.out.println(vertexA.getColor().get(0));
                gl.glColor3d(vertexA.getColor().get(0), vertexA.getColor().get(1), vertexA.getColor().get(2));
                gl.glNormal3d(triangle.getNormal().get(0),triangle.getNormal().get(1),
                        triangle.getNormal().get(2));
                gl.glVertex3d(positionA.get(0),positionA.get(1),positionA.get(2));
                //Draw vertex B 
                gl.glColor3d(vertexB.getColor().get(0), vertexB.getColor().get(1), vertexB.getColor().get(2));
                gl.glNormal3d(triangle.getNormal().get(0),triangle.getNormal().get(1),
                        triangle.getNormal().get(2));                
                gl.glVertex3d(positionB.get(0),positionB.get(1),positionB.get(2));
                //Draw vertex C 
                gl.glColor3d(vertexC.getColor().get(0), vertexC.getColor().get(1), vertexC.getColor().get(2));
                gl.glNormal3d(triangle.getNormal().get(0),triangle.getNormal().get(1),
                        triangle.getNormal().get(2));
                gl.glVertex3d(positionC.get(0),positionC.get(1),positionC.get(2));
                
                
            }
            gl.glEnd(); //end drawing
        //The draw code ends here
            
        gl.glEndList(); //Create the display list.
        
        displayListMissing = false; //We just created the display list
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
