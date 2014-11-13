/**
 * Prof. Philipp Jenke
 * Hochschule f�r Angewandte Wissenschaften (HAW), Hamburg
 * CG1: Educational Java OpenGL framework with scene graph.
 */

package computergraphics.math;


/**
 * Contains various helper classes.
 * 
 * @author pjenke
 * 
 */
public final class MathHelpers {

    /**
     * Numerical precision.
     */
    public static final double EPSILON = 0.0000001;
    public static final int DIMENSION_2 = 2;
    public static final int DIMENSION_3 = 3;
    public static final int DIMENSION_4 = 4;
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    private static final double ONE_HUNDRET_EIGHTY = 180.0;

    /**
     * Disallow instanciation.
     */
    private MathHelpers() {
    }

    /**
     * Convert a radiens angle to a degree angle.
     */
    public static double radiens2degree(double radiens) {
        return radiens / Math.PI * ONE_HUNDRET_EIGHTY;
    }

    /**
     * Convert a degree angle to a radiens angle.
     */
    public static double degree2radiens(final double degree) {
        return degree / ONE_HUNDRET_EIGHTY * Math.PI;
    }

    /**
     * Compute a over b.
     * 
     * @param a
     *            First parameter
     * @param b
     *            Second parameter
     * @return a over b.
     */
    public static double over(int a, int b) {
        return factorial(a) / (factorial(b) * factorial(a - b));
    }

    /**
     * Compute the factorial of a number.
     * 
     * @param n
     *            Number used.
     * @return Factorial of n.
     */
    public static int factorial(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    /**
     * Berechnet eine Facettennormale für ein Dreieck welches durch 3 Ortsvek-
     * toren gegeben ist.
     * @param a erster Vektor
     * @param b zweiter Vektor
     * @param c dritter Vektor
     * @return die berechnete Normale
     */
	public static Vector3 calculateNormal(Vector3 a, Vector3 b, Vector3 c) {
		
		//Vector B minus Vector A
		Vector3 bMinusA = b.subtract(a);
				
		//Vector C minus Vector A
		Vector3 cMinusA = c.subtract(a);

		//Kreuzprodukt bMinusA X cMinusA
		Vector3 crossProduct = bMinusA.cross(cMinusA);
		return crossProduct;
	}
    
    

}
