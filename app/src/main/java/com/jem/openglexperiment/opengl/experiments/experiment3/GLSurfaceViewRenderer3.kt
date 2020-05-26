package com.jem.openglexperiment.opengl.experiments.experiment3

import android.opengl.GLES20
import android.opengl.Matrix
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.shapes.FourTriangles
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer3 : BaseGLSurfaceViewRenderer() {

    private lateinit var triangle: FourTriangles
    private val scaleMatrix = FloatArray(16)
    private var scaleFactor = 1f

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        triangle = FourTriangles()
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        Matrix.scaleM(scaleMatrix, 0, vPMatrix, 0, scaleFactor, scaleFactor, scaleFactor)
        triangle.draw(scaleMatrix)
    }

    fun updateScaleFactor(scaleFactorDiff: Float) {
        this.scaleFactor += scaleFactorDiff
    }
}