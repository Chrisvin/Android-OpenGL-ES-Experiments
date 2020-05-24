package com.jem.openglexperiment.opengl.common

import android.opengl.GLES20
import android.opengl.Matrix

class GLHandler {

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    val vPMatrix = FloatArray(16)
    val projectionMatrix = FloatArray(16)
    val viewMatrix = FloatArray(16)

    fun initProjectionMatrix(width: Int, height: Int, near: Float, far: Float) {
        GLES20.glViewport(0, 0, width, height)
        val ratio = width.toFloat() / height.toFloat()
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, near, far)
    }

    fun updateMatrices() {
        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)
    }


}