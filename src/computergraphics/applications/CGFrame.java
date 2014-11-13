/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import java.io.IOException;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.TranslationNode;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class CGFrame extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Constructor.
	 * @throws IOException 
	 */
	public CGFrame(int timerInverval) throws IOException {
		super(timerInverval);
		
		//Bild zentriert darstellen
		TranslationNode translationNode = 
		        new TranslationNode(new Vector3(-0.5,0,-0.5));
		
		// Colornode erstellen für farbliche Darstellung
		ColorNode colorNode = new ColorNode(new Vector3(0, 0.75, 0));
		
		getRoot().addChild(translationNode);
		translationNode.addChild(colorNode);
	}
	
    /*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		// System.out.println("Tick");
	}

	/**
	 * Program entry point.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new CGFrame(1000);
	}
}
