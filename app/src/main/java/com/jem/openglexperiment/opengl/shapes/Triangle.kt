package com.jem.openglexperiment.opengl.shapes

import android.opengl.GLES20
import com.jem.openglexperiment.opengl.helper.ByteBufferHelper.allocateDirectFloatBuffer
import com.jem.openglexperiment.opengl.helper.ShaderHelper.createProgram
import com.jem.openglexperiment.opengl.helper.ShaderHelper.loadFragmentShader
import com.jem.openglexperiment.opengl.helper.ShaderHelper.loadVertexShader
import java.nio.FloatBuffer

class Triangle {

    companion object {
        // number of coordinates per vertex in this array
        const val COORDS_PER_VERTEX = 3
        val color = floatArrayOf(0.13671875f, 0.16953125f, 0.82265625f, 1.0f)
        var triangleCoords = floatArrayOf(     // in counterclockwise order:
            0.0f, 0.622008459f, 0.0f,      // top
            -0.5f, -0.311004243f, 0.0f,    // bottom left
            0.5f, -0.311004243f, 0.0f      // bottom right
        )
    }

    private val vertexShaderCode =
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "void main() {" +
                // the matrix must be included as a modifier of gl_Position
                // Note that the uMVPMatrix factor *must be first* in order
                // for the matrix multiplication product to be correct.
                "  gl_Position =  uMVPMatrix * vPosition;" +
                "}"

    // Use to access and set the view transformation
    private var vPMatrixHandle: Int = 0

    private val fragmentShaderCode =
        "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}"

    private var vertexBuffer: FloatBuffer =
        allocateDirectFloatBuffer(triangleCoords)

    private var mProgram: Int

    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride: Int = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    init {
        val vertexShader: Int = loadVertexShader(vertexShaderCode)
        val fragmentShader: Int = loadFragmentShader(fragmentShaderCode)
        // create empty OpenGL ES Program
        mProgram = createProgram(vertexShader, fragmentShader)
    }

    fun draw(mvpMatrix: FloatArray) { // pass in the calculated transformation matrix
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram)
        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix").also {
            // Pass the projection and view transformation to the shader
            GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)
        }
        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition").also {
            // Enable a handle to the triangle vertices
            GLES20.glEnableVertexAttribArray(it)
            // Prepare the triangle coordinate data
            GLES20.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )
        }
        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->
            // Set color for drawing the triangle
            GLES20.glUniform4fv(colorHandle, 1, color, 0)
        }
        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle)
    }
}