/**
 * Praktikum WPCG, WS 14/15, Gruppe 2
 * Gruppe: Andreas Mauritz (andreas.mauritz@haw-hamburg.de)
 * 		   Christian Schirin (christian.schirin@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 2a
 * Verwendete Quellen:
 *  
 */
package computergraphics.scenegraph;

import javax.media.opengl.GL2;

import computergraphics.math.Vector3;

/**
 * Diese Klasse erm�glicht es in einem Szenengraph Translationen umzusetzen.
 * Translationen werden durch einen 3D-Vektor dargestellt. 
 * @author Andreas & Christian
 *
 */
public class TranslationNode extends Node{

	/**
	 * Translation factors in x-, y- and z-direction.
	 */
	private final Vector3 factor;
	
	/**
	 * Constructor.
	 */
	public TranslationNode(Vector3 factor) {
		this.factor = factor;
	}
	
	@Override
	/**
	 * Sorgt daf�r, dass alle Kindknoten dieses Knotens um einen Translations-
	 * vektor verschoben werden. Inspiriert von ScaleNode#drawGl(GL2) 
	 */
	public void drawGl(GL2 gl) {
		
		// Remember current state of the render system
				gl.glPushMatrix();

		// cast von double auf float, um openGL-Funktion verwenden zu k�nnen. 
		gl.glTranslatef((float) factor.get(0), (float) factor.get(1),
				(float) factor.get(2));		
		
		// Draw all children
		for (int childIndex = 0; childIndex < getNumberOfChildren(); 
				childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}
		
		// Restore original state
		gl.glPopMatrix();
		
	}
	
	

}
