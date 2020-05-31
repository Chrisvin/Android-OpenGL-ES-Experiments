package com.jem.openglexperiment.opengl.fragmentshader

import android.opengl.GLES20
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class SmoothPulse : BaseFragmentShader() {

    private val touchLocation: FloatArray = floatArrayOf(0f, 0f)

    override fun getFragmentShaderCode(): String {
        return "precision mediump float;" +
                "uniform vec2 vTouch;" +

                "float upWave(float dx) {" +
                "   return (dx - floor(dx));" +
                "}" +

                "float circle(vec2 uv, float startRadius, float rangeSize) {" +
                // calculate ease value using time
                "   float ease = upWave(vTime * 0.75);" +
                // adjust the radius based on radius & ease values
                "   float radius = startRadius + ease * rangeSize;" +
                // place the circle relative to the touch location (place in center before any touches are registered).
                "   vec2 circleCenter;" +
                "   if (vTouch.x < 0.0001) {" +
                "       circleCenter = vec2(uv - 0.5);" +
                "   } else {" +
            "           vec2 touchLocation = vTouch/vResolution;" +
                "       circleCenter = vec2(uv - vec2(touchLocation.x, 1.0-touchLocation.y));" +
                "   }" +
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

                "void main() {" +
                "   vec2 uv = ( gl_FragCoord.xy / vResolution.xy ) ;" +
                "   vec3 color = vec3(.21, .22, .28);" +
                "   float circle = circle(uv, 50.0, 200.0) * 0.85;" +
                "   color = mix(color, vec3(0.0, .64, .91), circle);" +
                "   gl_FragColor = vec4(color, 1.0);" +
                "}"
    }

    override fun handleFragmentAttributes() {
        GLES20.glGetUniformLocation(program, "vTouch").also {
            GLES20.glUniform2fv(it, 1, touchLocation, 0)
        }
    }

    fun updateTouchLocation(x: Float, y: Float) {
        touchLocation[0] = x
        touchLocation[1] = y
    }
}