/**
* Praktikum WPCG, Wintersemester 2014/2015
* Gruppe: 
* Andreas Mauritz (Andreas.Mauritz@haw-hamburg.de)
* Christian Schirin (Christian.Schirin@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 2, Aufgabe 1
* Verwendete Quellen: 
* Phillip Jenke: Folien zu Vorlesung 2 - Polygonale Netze (S.6)
* 
*/
package computergraphics.datastructures;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.MathHelpers;
import computergraphics.math.Vector3;

/** Diese Klasse repräsentiert ein Polygonales Netz in dem die Polygone alle
 * Dreiecke sind. Es wird bei dieser Implementierung auf Wert der verständlich-
 * keit der Implementation statt Effizienz Wert gelegt.
 * @author Andreas Mauritz, Christian Schirin 
 */
public class TriangleMesh implements ITriangleMesh {
	
	//Ein Polygonales Netz ist definiert durch eine... 
	
	//Knotenliste V...
	private List<Vertex> vertexList; 
	
	//und eine Facettenliste F. 
	private List<Triangle> facetList; //Unsere Facetten sind alle dreieckig

	/** Initializer. */ 
	{ 
		vertexList = new ArrayList<>();
		facetList = new ArrayList<>();
	}

	@Override
	public void addTriangle(Triangle t) {
		facetList.add(t);
	}

	@Override
	public int addVertex(Vertex v) {
		vertexList.add(v);
		
		/* Da Vertices an das Ende der Liste angehängt werden, ist der Index 
		 * des lezten Vertex der hinzugefügt wurde der letzte erreichbare Index
		 * der Liste. */
		return vertexList.size() - 1;	
	}

	@Override
	public int getNumberOfTriangles() {
		return facetList.size();
	}

	@Override
	public int getNumberOfVertices() {
		return vertexList.size();
	}

	@Override
	public Triangle getTriangle(int index) {
		return facetList.get(index);
	}

	@Override
	public Vertex getVertex(int index) {
		return vertexList.get(index);
	}

	/**
	 * {@inheritDoc}
	 * Calling this method has the same behaviour as this code block:
	 * { V.clear(); F.clear(); }
	 * where V is the vertex list and F is the facet list of the Triangle Mesh.
	 */
	@Override
	public void clear() {
		vertexList.clear();
		facetList.clear();
	}
	
	
	//Aufgabe 2: Dreiecksnormalen ausrechnen und speichern
	public void calculateAllNormals() {
		
		//Normale für jedes Dreieck aus facetList setzen
		for (Triangle t : facetList){
			
			Vector3 posA = getVertex(t.getA()).getPosition();
			Vector3 posB = getVertex(t.getB()).getPosition();
			Vector3 posC = getVertex(t.getC()).getPosition();
			
			Vector3 normal = MathHelpers.calculateNormal(posA,posB,posC);

			//normierter Normalenvector dem Dreieck hinzufügen
			t.setNormal(normal.getNormalized());			
		}
	}
	
	
	
	// Methods for Testing: equals and toString
	
	/**
	 * Use this to check if this triangle mesh is equal to 
	 * another triangle mesh.
	 */
	@Override
	public boolean equals(Object o) {
		//check for reference equality
		if (this == o) {return true;};
		//check if null or different type
		if (!(o instanceof TriangleMesh)) {return false;};
		//Safe cast now possible
		TriangleMesh that = (TriangleMesh)o;
		
		//two meshes are equal if their vertex lists and facet lists are equal.
		return this.vertexList.equals(that.vertexList) && 
			   this.facetList.equals(that.facetList);
	}
	
	
	/** 
	 * Returns a string representation of the Triangle Mesh, 
	 * useful for debugging and testing. 
	 */
	@Override 
	public String toString() {
		return "TriangleMesh("
				+ "vertexList="+vertexList+","
				+ "facetList="+facetList
				+ ")";
	}



}
