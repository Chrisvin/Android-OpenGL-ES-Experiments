package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class SilexarsCreation(val resources: Resources) : BaseFragmentShader(resources) {

    override fun getFragmentShaderFileName(): String {
        return "silexars_creation.glsl"
    }

    override fun handleFragmentAttributes() {
    }
}