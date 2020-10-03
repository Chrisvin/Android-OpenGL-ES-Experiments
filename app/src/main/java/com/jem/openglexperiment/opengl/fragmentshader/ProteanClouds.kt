package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class ProteanClouds(val resources: Resources) : BaseFragmentShader(resources) {

    override fun getFragmentShaderFileName(): String {
        return "protean_clouds.glsl"
    }

    override fun handleFragmentAttributes() {
    }
}