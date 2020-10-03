package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class SimplePulse(val resources: Resources) : BaseFragmentShader(resources) {

    override fun getFragmentShaderFileName(): String {
        return "simple_pulse.glsl"
    }

    override fun handleFragmentAttributes() {
    }
}