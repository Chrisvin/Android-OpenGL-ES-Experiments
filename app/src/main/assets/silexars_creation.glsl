//version 120

// Reference Source: https://www.shadertoy.com/view/XsXXDn
// Credits: Danilo Guanabara

void main() {
    vec3 c;
    float l, z= vTime;
    vec2 p=gl_FragCoord.xy/vResolution.xy;
    for (int i=0;i<3;i++) {
        vec2 uv=p;
        p-=.5;
        z+=0.07;
        l=length(p);
        uv+=p/l*(sin(z)+1.0)*abs(sin(l  *9.0 - z*2.0));
        p+=.5;
        c[i]= 0.01 / length(abs(fract(uv) - 0.5));
    }
    gl_FragColor = vec4(c/l, 1.0);
}
