package com.jem.openglexperiment.opengl.shapes

import android.opengl.GLES20
import com.jem.openglexperiment.opengl.helper.ByteBufferHelper
import com.jem.openglexperiment.opengl.helper.ShaderHelper

class Cube {

    private val COORDS_PER_VERTEX = 3
    private val COLORS_PER_VERTEX = 4
    private val vertexCoords = floatArrayOf(
        -0.5f, 0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        0.5f, -0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, 0.5f, -0.5f
    )
    private val vertexBuffer =
        ByteBufferHelper.allocateDirectFloatBuffer(vertexCoords)

    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3)
//    private val drawListBuffer = FloatBufferHelper.allocateDirectFloatBuffer(drawOrder)

    private val colors = floatArrayOf(
        1f, 0f, 0f, 1f,
        0f, 0.6f, 0.23f, 1f,
        0f, 0.98f, 0.1f, 1f,
        1f, 0f, 0f, 1f,
        0f, 1f, 0.8f, 1f,
        0f, 0.2f, 0.4f, 1f,
        1f, 0f, 0f, 1f,
        0f, 1f, 0.25f, 1f,
        0f, 0.9f, 0.78f, 1f,
        1f, 0f, 0f, 1f,
        0f, 0.58f, 0.48f, 1f,
        0.24f, 0.45f, 1f, 1f
    )
    private val colorBuffer = ByteBufferHelper.allocateDirectFloatBuffer(colors)

    private val vertexCount: Int = vertexCoords.size / COORDS_PER_VERTEX
    private val vertexStride: Int = COORDS_PER_VERTEX * 4 // 4 bytes per vertex
    private val colorCount: Int = colors.size / COLORS_PER_VERTEX
    private val colorStride: Int = COLORS_PER_VERTEX * 4 // 4 bytes per vertex

    private val vertexShaderCode = "" +
            "uniform mat4 vpMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vec4 vColor;" +
            "varying vec4 interpolatedColor;" +
            "void main() {" +
            "   interpolatedColor = vColor;" +
            "   gl_Position = vpMatrix * vPosition;" +
            "}"

    private val fragmentShaderCode = "" +
            "precision mediump float;" +
            "varying vec4 interpolatedColor;" +
            "void main() {" +
            "   gl_FragColor = interpolatedColor;" +
            "}"

    private var program = 0
    private var positionHandle = 0
    private var colorHandle = 0
    private var vpMatrixHandle = 0

    init {
        val vertexShader = ShaderHelper.loadVertexShader(vertexShaderCode)
        val fragmentShader = ShaderHelper.loadFragmentShader(fragmentShaderCode)
        program = ShaderHelper.createProgram(vertexShader, fragmentShader)
    }

    fun draw(vpMatrix: FloatArray) {
        GLES20.glUseProgram(program)
        vpMatrixHandle = GLES20.glGetUniformLocation(program, "vpMatrix").also {
            GLES20.glUniformMatrix4fv(it, 1, false, vpMatrix, 0)
        }
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition").also {
            GLES20.glEnableVertexAttribArray(it)
            GLES20.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )
        }
        colorHandle = GLES20.glGetAttribLocation(program, "vColor").also {
            GLES20.glEnableVertexAttribArray(it)
            GLES20.glVertexAttribPointer(
                it,
                COLORS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                colorStride,
                colorBuffer
            )
        }
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        GLES20.glDisableVertexAttribArray(positionHandle)
    }

}