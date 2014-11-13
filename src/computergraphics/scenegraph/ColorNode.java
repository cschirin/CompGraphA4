package computergraphics.scenegraph;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;

import computergraphics.math.Vector3;

public class ColorNode extends Node {

	/**
	 * Color used in this node. The color vector contains the red, green and
	 * blue channel. Each cannel contains values in [0;1];
	 */
	private Vector3 color = new Vector3(0.5, 0.5, 0.5);

	/**
	 * Shader representation.
	 */
	private CgGlslShader shader;

	/**
	 * Constructor
	 */
	public ColorNode(Vector3 color) {
		this.color.copy(color);

		// Use a Phong shader
		shader = new CgGlslShader("shader/vertex_shader_phong_shading.glsl",
				"shader/fragment_shader_phong_shading.glsl");
	}

	@Override
	public void drawGl(GL2 gl) {

		float r = (float) color.get(0);
		float g = (float) color.get(1);
		float b = (float) color.get(2);

		// Use color material
		float diffuseColor[] = new float[] { r, g, b, 1.0f };
		float specularColor[] = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
		// Use smaller values when using multiple light sources!
		float ambientColor[] = new float[] { 0.3f * r, 0.3f * g, 0.3f * b, 1.0f };
		float shininess[] = { 20.0f };
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_DIFFUSE,
				diffuseColor, 0);
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_AMBIENT,
				ambientColor, 0);
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_SPECULAR,
				specularColor, 0);
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_SHININESS,
				shininess, 0);

		// Setup the shader
		JoglShader.use(shader, gl);

		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}

	}

}
