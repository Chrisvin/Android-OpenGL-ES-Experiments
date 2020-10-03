//version 120

precision mediump float;
uniform vec4 vColor1;
uniform vec4 vColor2;
uniform float vOscillations;
void main() {
    float x = vTime * 2.0 * vOscillations;
    gl_FragColor = mix(vColor1, vColor2, ((cos(x * 3.1416) + 1.0) / 2.0));
}
