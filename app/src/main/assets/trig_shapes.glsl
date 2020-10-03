//version 120

precision mediump float;

uniform float innerTrigFactor;

void main(){
    vec3 color = vec3(0.620, 0.041, 0.094);
    vec2 pos = vec2(0.5, 0.9) - gl_FragCoord.xy/vResolution.x;

    float r = length(pos)*2.0;
    float a = atan(pos.y, pos.x);

    float f = smoothstep(-0.5, -0.0, cos(a*10. + vTime * 8.0))*0.2+0.5;
    color = max(color, mix(color, vec3(0., 0., 1.-smoothstep(f, f+0.4, r)), .75));

    f = abs(cos(a*floor(innerTrigFactor) - vTime * 5.0)*sin(a*3. - vTime * 4.))*0.5+.31;
    color = max(color, mix(color, vec3(1.-smoothstep(f, f+0.2, r / 0.65)), 0.5));

    f = ceil(sin(a*3. - vTime * 2.0)*sin(a*1.0 + vTime * 4.0))*0.5+.4;
    color = max(color, mix(color, vec3(1.-smoothstep(f, f+0.2, r / 0.2)), 0.5));

    gl_FragColor = vec4(color, 1.0);
}
