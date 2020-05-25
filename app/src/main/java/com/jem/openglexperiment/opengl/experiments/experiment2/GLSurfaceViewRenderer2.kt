package com.jem.openglexperiment.opengl.experiments.experiment2

import android.opengl.GLES20
import android.opengl.Matrix
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.shapes.RightAngleTriangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer2 : BaseGLSurfaceViewRenderer() {

    private lateinit var triangle: RightAngleTriangle

    private var angle: Float = 0f
    private val rotationMatrix = FloatArray(16)

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        triangle = RightAngleTriangle()
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        Matrix.rotateM(rotationMatrix, 0, vPMatrix, 0, angle, 0f, 0f, 1f)
        triangle.draw(rotationMatrix)
    }

    fun updateAngle(angleChange: Float) {
        angle += angleChange
    }
}