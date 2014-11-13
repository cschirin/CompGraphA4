package computergraphics.scenegraph;

import javax.media.opengl.GL2;

/**
 * Simple implementation of a Node, which only calls the draw methods of its
 * children
 * 
 * @author Philipp Jenke
 *
 */
public class GroupNode extends Node {

	@Override
	public void drawGl(GL2 gl) {
		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}
	}
}
