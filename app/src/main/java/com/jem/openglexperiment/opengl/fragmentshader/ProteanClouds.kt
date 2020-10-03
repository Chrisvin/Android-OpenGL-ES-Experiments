package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import com.jem.openglexperiment.getStringFromAsset
import com.jem.openglexperiment.getStringFromRawResource
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class ProteanClouds(val resources: Resources) : BaseFragmentShader(resources) {
    override fun getFragmentShaderCode(resources: Resources): String {
        return resources.getStringFromAsset("protean_clouds.glsl")
    }

    override fun handleFragmentAttributes() {
    }
}