//version 120

precision mediump float;

float plot (float t, float pct){
    return smoothstep(pct - 0.5, pct, t) -
        smoothstep(pct, pct + 0.5, t);
}

void main() {
    float theta = vTime * 2.0;
    vec2 coords = gl_FragCoord.xy / vResolution.xy;
    vec3 color = mix(
        vec3(0.509, 0.470, 0.755),
        mix(
            vec3(0.350, 0.990, 1.000),
            vec3(1.0),
            abs(sin(theta))
        ),
        plot(
            abs(cos(theta)) + coords.x,
            abs(sin(theta)) + coords.y
        )
    );
    gl_FragColor = vec4(color, 1.0);
}
