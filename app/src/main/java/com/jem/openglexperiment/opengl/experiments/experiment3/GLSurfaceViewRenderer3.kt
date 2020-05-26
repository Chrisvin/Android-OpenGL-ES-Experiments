package com.jem.openglexperiment.opengl.experiments.experiment3

import android.opengl.GLES20
import android.opengl.Matrix
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.shapes.FourTriangles
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer3: BaseGLSurfaceViewRenderer() {

    private lateinit var triangle: FourTriangles

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        triangle = FourTriangles()
    }

    override fun onDrawFrame(gl: GL10?) {
//        super.onDrawFrame(gl)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -5f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)
        triangle.draw(vPMatrix)
    }
}