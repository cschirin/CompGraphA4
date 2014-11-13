// These vectors need to be provided by the vertex shader
// Normal direction
varying vec3 N;
// Viewer direction
varying vec3 v;
varying vec3 lightPosition;
varying vec4 color;

/**
 * Fragment shader: Phong lighting model, Phong shading, one light
 * source taken from GL. 
 */
void main (void)
{
    // Helping vectors
    vec3 L = normalize(lightPosition - v);
    vec3 E = normalize(-v);
    vec3 R = normalize(reflect(-L,N));
    
    // Phong lighting model
    vec4 ambient = gl_FrontMaterial.ambient;
    vec4 diffuse = clamp( color * abs(dot(N,L))  , 0.0, 1.0 ) ;
    vec4 spec = clamp ( gl_FrontMaterial.specular * pow(abs(dot(R,E)), gl_FrontMaterial.shininess) , 0.0, 1.0 );
    gl_FragColor = ambient + diffuse + spec;
}
