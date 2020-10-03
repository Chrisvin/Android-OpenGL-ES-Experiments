package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import android.opengl.GLES20
import android.os.SystemClock
import com.jem.openglexperiment.getStringFromAsset
import com.jem.openglexperiment.opengl.base.BaseFragmentShader
import java.util.*

class SmoothPulse(val resources: Resources) : BaseFragmentShader(resources) {

    private var touchLocationCount: Int = 0
    private val touchLocations: FloatArray = FloatArray(MAX_TOUCH_LOCATIONS * 2)
    private val touchLocationStartTimes: LongArray = LongArray(MAX_TOUCH_LOCATIONS)
    private val touchLocationTimes: FloatArray = FloatArray(MAX_TOUCH_LOCATIONS)
    private val touchLocationColors: FloatArray = FloatArray(MAX_TOUCH_LOCATIONS * 3)

    private val colorRandom: Random = Random()

    override fun getFragmentShaderCode(resources: Resources): String {
        return resources.getStringFromAsset("smooth_pulse.glsl")
    }

    override fun handleFragmentAttributes() {
        GLES20.glGetUniformLocation(program, "locationCount").also {
            GLES20.glUniform1i(it, touchLocationCount)
        }
        GLES20.glGetUniformLocation(program, "locations").also {
            GLES20.glUniform2fv(it, touchLocationCount, touchLocations, 0)
        }
        val currentTime = SystemClock.uptimeMillis()
        for (i in 0..touchLocationCount) {
            touchLocationTimes[i] =
                ((currentTime - touchLocationStartTimes[i]) % TIME_MOD_LIMIT).toFloat() / 1000.0f
        }
        GLES20.glGetUniformLocation(program, "locationTimes").also {
            GLES20.glUniform1fv(it, touchLocationCount, touchLocationTimes, 0)
        }
        GLES20.glGetUniformLocation(program, "locationColors").also {
            GLES20.glUniform3fv(it, touchLocationCount, touchLocationColors, 0)
        }
    }

    private fun setColors(index: Int, reset: Boolean = false) {
        for (i in index..index + 2) {
            touchLocationColors[i] =
                if (reset) {
                    0f
                } else {
                    colorRandom.nextFloat()
                }
        }
    }

    fun addTouchLocation(x: Float, y: Float) {
        if (touchLocationCount == MAX_TOUCH_LOCATIONS) {
            return
        }
        touchLocations[touchLocationCount * 2] = x
        touchLocations[(touchLocationCount * 2) + 1] = y
        touchLocationStartTimes[touchLocationCount] = SystemClock.uptimeMillis()
        setColors(touchLocationCount * 3)
        touchLocationCount++
    }

    fun undoTouchLocation() {
        if (touchLocationCount == 0) {
            return
        }
        touchLocationCount--
        touchLocations[touchLocationCount * 2] = 0f
        touchLocations[(touchLocationCount * 2) + 1] = 0f
        touchLocationStartTimes[touchLocationCount] = 0L
        setColors(touchLocationCount * 3, reset = true)
    }

    companion object {
        private const val MAX_TOUCH_LOCATIONS = 100

        private const val TIME_MOD_LIMIT = 2 * Math.PI * 1000
    }
}