package com.jem.openglexperiment.opengl.experiment1

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import com.jem.openglexperiment.opengl.common.GLHandler
import com.jem.openglexperiment.opengl.shapes.Square
import com.jem.openglexperiment.opengl.shapes.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer1 : GLSurfaceView.Renderer {

    private val glHandler = GLHandler()
    private val scratch = FloatArray(16)
    private val rotationMatrix = FloatArray(16)

    private lateinit var triangle: Triangle
    private lateinit var square: Square

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        triangle = Triangle()
        square = Square()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        glHandler.initProjectionMatrix(width, height, 3f, 7f)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        glHandler.updateMatrices()

        val time = SystemClock.uptimeMillis() % 2000L
        val angle = 0.180f * time.toInt()
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, 1.0f)
        Matrix.multiplyMM(scratch, 0, glHandler.vPMatrix, 0, rotationMatrix, 0)

        triangle.draw(scratch)
    }

}