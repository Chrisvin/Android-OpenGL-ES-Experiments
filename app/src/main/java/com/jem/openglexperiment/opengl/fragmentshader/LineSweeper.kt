package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class LineSweeper(val resources: Resources) : BaseFragmentShader(resources) {

    override fun getFragmentShaderFileName(): String {
        return "line_sweeper.glsl"
    }

    override fun handleFragmentAttributes() {
    }
}