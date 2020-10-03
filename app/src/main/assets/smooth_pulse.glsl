//version 120

// Reference Source for circle impl: https://www.shadertoy.com/view/ldtyRn

precision mediump float;

const int MAX_TOUCH_LOCATIONS = 100;

uniform int locationCount;
uniform vec2 locations[MAX_TOUCH_LOCATIONS];
uniform float locationTimes[MAX_TOUCH_LOCATIONS];
uniform vec3 locationColors[MAX_TOUCH_LOCATIONS];

float upWave(float dx) {
    return (dx - floor(dx));
}

float circle(vec2 circleCenter, float startRadius, float rangeSize, float ease) {
    // adjust the radius based on radius & ease values
    float radius = startRadius + ease * rangeSize;
    // adjust circleCenter for aspect ratio
    circleCenter.x *= vResolution.x / vResolution.y;

    // (vector length) distance from the pixel to the touch location
    float dist = length(circleCenter);
    // a single pixel in [0, 1] coordinate space is 1 / res;
    float res = min(vResolution.x, vResolution.y);

    // our smoothness area
    float smoothness = 2.0 / res;
    // circle with smoothed edges
    // gives us a nice gradient when we are within radius and radius + smoothness
    float circ = smoothstep(radius / res + smoothness, radius / res, dist);
    // fade the ring as we go outward
    float opacity = (1.0 - smoothstep(0.1, 0.9, ease));
    return mix(0.0, 1.0, circ * opacity);
}

vec3 getCircle(vec3 color, int index, float startRadius, float rangeSize) {
    vec2 circleLocation = locations[index] / vResolution;
    vec2 uv = gl_FragCoord.xy / vResolution.xy;
    // place the circle relative to the provided location (place in center before any touches are registered).
    vec2 circleCenter;
    if (circleLocation.x < 0.0001) {
        circleCenter = vec2(uv - 0.5);
    } else {
        circleCenter = vec2(uv - vec2(circleLocation.x, 1.0 - circleLocation.y));
    }

    // calculate ease value using time
    float ease = upWave(locationTimes[index] * 0.75);
    // uncomment the following line to synchronize the pulses
    //                "   ease = upWave(vTime * 0.75);

    float circle = circle(circleCenter, startRadius, rangeSize, ease) * 0.85;
    return mix(color, locationColors[index], circle);
}

void main() {
    vec3 color = vec3(.21, .22, .28);
    for (int i=0;i<MAX_TOUCH_LOCATIONS;i++) {
        if (i >= locationCount) {
            break;
        }
        color = getCircle(color, i, 50.0, 150.0);
    }
    gl_FragColor = vec4(color, 1.0);
}
