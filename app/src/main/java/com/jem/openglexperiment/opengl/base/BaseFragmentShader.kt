package com.jem.openglexperiment.opengl.base

import android.content.res.Resources
import android.opengl.GLES20
import android.os.SystemClock
import com.jem.openglexperiment.opengl.helper.ByteBufferHelper
import com.jem.openglexperiment.opengl.helper.ShaderHelper

abstract class BaseFragmentShader(private val resources: Resources) {

    companion object {
        protected const val COORDS_PER_VERTEX = 3
        protected const val BYTES_PER_VERTEX = 4
        protected const val VERTEX_STRIDE = COORDS_PER_VERTEX * BYTES_PER_VERTEX

        private const val TIME_MOD_LIMIT = 2 * Math.PI * 1000

        protected const val COMMON_SHADER_PARAMS = "" +
                "uniform vec2 vResolution;" +
                "uniform float vTime;"
    }

    protected var program: Int = 0

    private var positionHandle = 0

    private var screenWidth: FloatArray? = null

    private val vertexArray = floatArrayOf(
        -1f, 1f, 0f,
        -1f, -1f, 0f,
        1f, -1f, 0f,
        1f, 1f, 0f
    )
    private val vertexBuffer by lazy {
        ByteBufferHelper.allocateDirectFloatBuffer(this.vertexArray)
    }

    private val vertexShaderCode: String = "" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            "   gl_Position = vPosition;" +
            "}"

    private val startTime: Long = SystemClock.uptimeMillis()

    init {
        val vertexShaderHandle = ShaderHelper.loadVertexShader(vertexShaderCode)
        val shaderCode = this.getFragmentShaderCode(resources)
        val hasSomeAttribute = shaderCode.indexOf(";") < shaderCode.indexOf("void main()")
        val fragmentShaderCode =
            if (hasSomeAttribute) {
                shaderCode.replaceFirst(";", ";$COMMON_SHADER_PARAMS")
            } else {
                shaderCode.replaceFirst("void main()", "${COMMON_SHADER_PARAMS}void main()")
            }
        val fragmentShaderHandle = ShaderHelper.loadFragmentShader(fragmentShaderCode)
        program = ShaderHelper.createProgram(vertexShaderHandle, fragmentShaderHandle)
    }

    abstract fun getFragmentShaderCode(resources: Resources): String
    abstract fun handleFragmentAttributes()

    fun setResolution(width: Int, height: Int) {
        this.setResolution(width.toFloat(), height.toFloat())
    }

    fun setResolution(width: Float, height: Float) {
        screenWidth = floatArrayOf(width, height)
    }

    fun draw() {
        GLES20.glUseProgram(program)

        screenWidth?.let { vResolution ->
            GLES20.glGetUniformLocation(program, "vResolution").also {
                GLES20.glUniform2fv(it, 1, vResolution, 0)
            }
        }
        GLES20.glGetUniformLocation(program, "vTime").also {
            GLES20.glUniform1f(
                it,
                ((SystemClock.uptimeMillis() - startTime) % TIME_MOD_LIMIT).toFloat() / 1000.0f
            )
        }
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition").also {
            GLES20.glEnableVertexAttribArray(it)
            GLES20.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                VERTEX_STRIDE,
                vertexBuffer
            )
        }

        handleFragmentAttributes()

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexArray.size / COORDS_PER_VERTEX)
        GLES20.glDisableVertexAttribArray(positionHandle)
    }

}