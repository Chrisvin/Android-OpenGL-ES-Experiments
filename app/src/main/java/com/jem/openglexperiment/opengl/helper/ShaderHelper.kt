package com.jem.openglexperiment.opengl.helper

import android.opengl.GLES20

object ShaderHelper {

    fun loadVertexShader(shaderCode: String): Int {
        return loadShader(GLES20.GL_VERTEX_SHADER, shaderCode)
    }

    fun loadFragmentShader(shaderCode: String): Int {
        return loadShader(GLES20.GL_FRAGMENT_SHADER, shaderCode)
    }

    fun loadShader(type: Int, shaderCode: String): Int {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        return GLES20.glCreateShader(type).also { shader ->
            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
        }
    }

    fun createProgram(vertexShader: Int? = null, fragmentShader: Int? = null): Int {
        return GLES20.glCreateProgram().also {
            vertexShader?.let { vShader ->
                GLES20.glAttachShader(it, vShader)
            }
            fragmentShader?.let { fShader ->
                GLES20.glAttachShader(it, fShader)
            }
            GLES20.glLinkProgram(it)
        }
    }
}