package com.jem.openglexperiment.opengl.fragmentshader

import android.opengl.GLES20
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class ColorOscillator(
    private val oscillationsPerSecond: Float = 0.5f,
    private val color1: FloatArray = floatArrayOf(1f, 0f, 0f, 1f),
    private val color2: FloatArray = floatArrayOf(0f, 0.66f, 1f, 1f)
) : BaseFragmentShader() {

    override fun getFragmentShaderCode(): String {
        return "precision mediump float;" +
                "uniform vec4 vColor1;" +
                "uniform vec4 vColor2;" +
                "uniform float vOscillations;" +
                "void main() {" +
                "   float x = vTime * 2.0 * vOscillations;" +
                "   gl_FragColor = mix(vColor1, vColor2, ((cos(x * 3.1416) + 1.0) / 2.0));" +
                "}"
    }

    override fun handleFragmentAttributes() {
        GLES20.glGetUniformLocation(program, "vColor1").also {
            GLES20.glUniform4fv(it, 1, color1, 0)
        }
        GLES20.glGetUniformLocation(program, "vColor2").also {
            GLES20.glUniform4fv(it, 1, color2, 0)
        }
        GLES20.glGetUniformLocation(program, "vOscillations").also {
            GLES20.glUniform1f(it, oscillationsPerSecond)
        }
    }

}