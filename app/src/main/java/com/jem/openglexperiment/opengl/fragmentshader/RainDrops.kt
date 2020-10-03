package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import android.opengl.GLES20
import android.os.SystemClock
import com.jem.openglexperiment.getStringFromAsset
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class RainDrops(val resources: Resources) : BaseFragmentShader(resources) {

    private val startTime: Long = SystemClock.uptimeMillis()
    private var rainAmount: Float = 1.0f

    override fun getFragmentShaderCode(resources: Resources): String {
        return resources.getStringFromAsset("rain_drops.glsl")
    }

    override fun handleFragmentAttributes() {
        GLES20.glGetUniformLocation(program, "uTime").also {
            GLES20.glUniform1f(
                it,
                ((SystemClock.uptimeMillis() - startTime)).toFloat() / 1000.0f
            )
        }
        GLES20.glGetUniformLocation(program, "rainAmount").also {
            GLES20.glUniform1f(it, rainAmount)
        }
    }

    public fun setRainAmount(rainAmount: Float) {
        this.rainAmount = rainAmount.coerceIn(0f, 1f)
    }

}