/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.datastructures;

import computergraphics.math.Vector3;

/**
 * Representation of a vertex.
 * 
 * @author Philipp Jenke
 * 
 */
public class Vertex {

	/**
	 * 3D position of the vertex.
	 */
	private final Vector3 position = new Vector3(0, 0, 0);

	/**
	 * (Normalized) normal direction of the vertex.
	 */
	private Vector3 normal = new Vector3(0, 0, 0);

	/**
	 * Color value at the vertex
	 */
	private Vector3 color = new Vector3(0, 0, 0);

	/**
	 * Constructor.
	 * 
	 * @param position
	 *            Initial value for position.
	 */
	public Vertex(Vector3 position) {
		this.position.copy(position);
	}

	/**
	 * Constructor.
	 * 
	 * @param position
	 *            Initial value for position.
	 * @param normal
	 *            Initial value for normal.
	 */
	public Vertex(Vector3 position, Vector3 normal) {
		this.position.copy(position);
		this.normal.copy(normal);
	}

	/**
	 * Constructor.
	 * 
	 * @param position
	 *            Initial value for position.
	 * @param normal
	 *            Initial value for normal.
	 */
	public Vertex(Vector3 position, Vector3 normal, Vector3 color) {
		this.position.copy(position);
		this.normal.copy(normal);
		this.color.copy(color);
	}

	/**
	 * Getter.
	 */
	public Vector3 getPosition() {
		return position;
	}

	/**
	 * Getter.
	 */
	public Vector3 getNormal() {
		return normal;
	}

	/**
	 * Getter.
	 */
	public Vector3 getColor() {
		return color;
	}

	/**
	 * @param normal
	 */
	public void setNormal(Vector3 normal) {
		this.normal.copy(normal);
	}

	/**
	 * @param normal
	 */
	public void setColor(Vector3 color) {
		this.color.copy(color);
	}
	
	//CUSTOM CODE
	
	/**
	 * toString Method for debugging/testing/tracing.
	 */
	public String toString() {
	    return "Vertex("
	    	+ "position="+position+","
	        + "normal="+normal+","
	        + "color="+color
	        +")";
	}
}