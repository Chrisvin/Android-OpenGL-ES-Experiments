package com.jem.openglexperiment.opengl.base

import com.jem.openglexperiment.opengl.helper.ShaderHelper

abstract class BaseFragmentShader {

    protected var program: Int = 0
    private var fragmentShaderHandle: Int = 0

    init {
        fragmentShaderHandle = ShaderHelper.loadFragmentShader(this.getFragmentShaderCode())
        program = ShaderHelper.createProgram(fragmentShader = fragmentShaderHandle)
    }

    abstract fun getFragmentShaderCode(): String

    abstract fun draw()

}