package com.jem.openglexperiment.opengl.shapes

import android.opengl.GLES20
import com.jem.openglexperiment.opengl.helper.FloatBufferHelper.allocateDirectBuffer
import com.jem.openglexperiment.opengl.helper.ShaderHelper.createProgram
import com.jem.openglexperiment.opengl.helper.ShaderHelper.loadFragmentShader
import com.jem.openglexperiment.opengl.helper.ShaderHelper.loadVertexShader
import java.nio.FloatBuffer

class RightAngleTriangle {

    companion object {
        const val COORDS_PER_VERTEX = 7
        val triangleColorCoords = floatArrayOf(
            -0.5f, -0.25f, 0.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            0.5f, -0.25f, 0.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.559016994f, 0.0f,
            0.0f, 1.0f, 0.0f, 1.0f
        )
    }

    private val vertexShaderCode =
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "attribute vec4 vColor;" +
                "varying vec4 interpolated_color;" +
                "void main() {" +
                "   interpolated_color = vColor;" +
                "   gl_Position =  uMVPMatrix * vPosition;" +
                "}"

    private val fragmentShaderCode =
        "precision mediump float;" +
                "varying vec4 interpolated_color;" +
                "void main() {" +
                "   gl_FragColor = interpolated_color;" +
                "}"

    private var vertexColorBuffer =
        allocateDirectBuffer(triangleColorCoords.size * 4, triangleColorCoords)

    private var mProgram: Int

    private var positionHandle: Int = 0
    private var attributeColorHandle: Int = 0
    private var vPMatrixHandle: Int = 0

    private val vertexCount: Int = triangleColorCoords.size / COORDS_PER_VERTEX
    private val vertexStride: Int = COORDS_PER_VERTEX * 4

    init {
        val vertexShader: Int = loadVertexShader(vertexShaderCode)
        val fragmentShader: Int = loadFragmentShader(fragmentShaderCode)
        mProgram = createProgram(vertexShader, fragmentShader)
    }

    fun draw(mvpMatrix: FloatArray) {
        GLES20.glUseProgram(mProgram)
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix").also {
            GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)
        }
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition").also {
            GLES20.glEnableVertexAttribArray(it)
            vertexColorBuffer.position(0)
            GLES20.glVertexAttribPointer(
                it,
                3,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertexColorBuffer
            )
        }
        attributeColorHandle = GLES20.glGetAttribLocation(mProgram, "vColor").also {
            GLES20.glEnableVertexAttribArray(it)
            vertexColorBuffer.position(3)
            GLES20.glVertexAttribPointer(
                it,
                4,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertexColorBuffer
            )
        }
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        GLES20.glDisableVertexAttribArray(positionHandle)
    }
}