package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import android.opengl.GLES20
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class ColorOscillator(
    resources: Resources,
    private val oscillationsPerSecond: Float = 0.5f,
    private val color1: FloatArray = floatArrayOf(1f, 0f, 0f, 1f),
    private val color2: FloatArray = floatArrayOf(0f, 0.66f, 1f, 1f)
) : BaseFragmentShader(resources) {


    override fun getFragmentShaderFileName(): String {
        return "color_oscillator.glsl"
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