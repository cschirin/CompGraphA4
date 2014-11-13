package computergraphics.scenegraph;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

/**
 * Parent class for all scene graph nodes
 * 
 * @author Philipp Jenke
 *
 */
public abstract class Node {

	/**
	 * List of child nodes
	 */
	private List<Node> children = new ArrayList<Node>();

	/**
	 * Add a child node.
	 */
	public void addChild(Node child) {
		children.add(child);
	}

	/**
	 * Return the child at the given index.
	 */
	public Node getChildNode(int index) {
		if (index < 0 || index >= getNumberOfChildren()) {
			System.out.println("getChildNode: invalid index.");
			return null;
		}
		return children.get(index);
	}

	/**
	 * Return the number of children
	 */
	public int getNumberOfChildren() {
		return children.size();
	}

	/**
	 * This method is called to draw the node using OpenGL commands. Override in
	 * implementing nodes. Do not forget to call the same method for the
	 * children.
	 */
	public abstract void drawGl(GL2 gl);

}
