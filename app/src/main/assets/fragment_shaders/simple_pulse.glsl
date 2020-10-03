//version 120

// Reference Source: https://www.shadertoy.com/view/Md3XRB

precision mediump float;
void main() {
    float position = length((gl_FragCoord.xy / vResolution.xy) - 0.5);
    gl_FragColor = smoothstep(2.4, 7.4,
        1.6 * sin(position * 30.0 - 10.0 * vTime) + (0.4/position) - vec4(0.0)
    );
}
