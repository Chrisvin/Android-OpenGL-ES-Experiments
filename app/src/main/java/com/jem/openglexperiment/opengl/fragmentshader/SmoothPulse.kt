package com.jem.openglexperiment.opengl.fragmentshader

import android.opengl.GLES20
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class SmoothPulse : BaseFragmentShader() {

    private var touchLocationCount: Int = 0
    private val touchLocations: FloatArray = FloatArray(MAX_TOUCH_LOCATIONS * 2)

    override fun getFragmentShaderCode(): String {
        return "precision mediump float;" +

                "const int MAX_TOUCH_LOCATIONS = 100;" +

                "uniform int locationCount;" +
                "uniform vec2 locations[MAX_TOUCH_LOCATIONS];" +

                "float upWave(float dx) {" +
                "   return (dx - floor(dx));" +
                "}" +

                "float circle(vec2 circleCenter, float startRadius, float rangeSize) {" +
                // calculate ease value using time
                "   float ease = upWave(vTime * 0.75);" +
                // adjust the radius based on radius & ease values
                "   float radius = startRadius + ease * rangeSize;" +
                // adjust circleCenter for aspect ratio
                "   circleCenter.x *= vResolution.x / vResolution.y;" +

                // (vector length) distance from the pixel to the touch location
                "   float dist = length(circleCenter);" +
                // a single pixel in [0, 1] coordinate space is 1 / res;
                "   float res = min(vResolution.x, vResolution.y);" +

                // our smoothness area
                "   float smoothness = 2.0 / res;" +
                // circle with smoothed edges
                // gives us a nice gradient when we are within radius and radius + smoothness
                "   float circ = smoothstep(radius / res + smoothness, radius / res, dist);" +
                // fade the ring as we go outward
                "   float opacity = (1.0 - smoothstep(0.1, 0.9, ease));" +
                "   return mix(0.0, 1.0, circ * opacity);" +
                "}" +

                "vec3 getCircle(vec3 color, vec2 circleLocation, float startRadius, float rangeSize) {" +
                "   vec2 uv = gl_FragCoord.xy / vResolution.xy;" +
                // place the circle relative to the provided location (place in center before any touches are registered).
                "   vec2 circleCenter;" +
                "   if (circleLocation.x < 0.0001) {" +
                "       circleCenter = vec2(uv - 0.5);" +
                "   } else {" +
                "       circleCenter = vec2(uv - vec2(circleLocation.x, 1.0 - circleLocation.y));" +
                "   }" +
                "   float circle = circle(circleCenter, startRadius, rangeSize) * 0.85;" +
                "   return mix(color, vec3(0.0, .64, .91), circle);" +
                "}" +

                "void main() {" +
                "   vec3 color = vec3(.21, .22, .28);" +
                "   for (int i=0;i<MAX_TOUCH_LOCATIONS;i++) {" +
                "       if (i >= locationCount) {" +
                "           break;" +
                "       }" +
                "       color = getCircle(color, locations[i] / vResolution, 50.0, 150.0);" +
                "   }" +
                "   gl_FragColor = vec4(color, 1.0);" +
                "}"
    }

    override fun handleFragmentAttributes() {
        GLES20.glGetUniformLocation(program, "locationCount").also {
            GLES20.glUniform1i(it, touchLocationCount)
        }
        GLES20.glGetUniformLocation(program, "locations").also {
            GLES20.glUniform2fv(it, touchLocationCount, touchLocations, 0)
        }
    }

    fun addTouchLocation(x: Float, y: Float) {
        touchLocations[touchLocationCount * 2] = x
        touchLocations[(touchLocationCount * 2) + 1] = y
        touchLocationCount++
    }

    fun undoTouchLocation() {
        touchLocationCount--
        touchLocations[touchLocationCount * 2] = 0f
        touchLocations[(touchLocationCount * 2) + 1] = 0f
    }

    companion object {
        private const val MAX_TOUCH_LOCATIONS = 100
    }
}