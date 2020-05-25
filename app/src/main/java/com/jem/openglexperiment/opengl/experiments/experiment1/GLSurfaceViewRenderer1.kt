package com.jem.openglexperiment.opengl.experiments.experiment1

import android.opengl.Matrix
import android.os.SystemClock
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.shapes.Square
import com.jem.openglexperiment.opengl.shapes.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer1 : BaseGLSurfaceViewRenderer() {

    private val scratch = FloatArray(16)
    private val rotationMatrix = FloatArray(16)

    private lateinit var triangle: Triangle

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        triangle = Triangle()
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)

        val time = SystemClock.uptimeMillis() % 2000L
        val angle = 0.180f * time.toInt()
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, 1.0f)
        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0)

        triangle.draw(scratch)
    }

}