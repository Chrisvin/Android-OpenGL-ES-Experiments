package com.jem.openglexperiment.opengl.fragmentshader

import android.content.res.Resources
import android.opengl.GLES20
import com.jem.openglexperiment.getStringFromAsset
import com.jem.openglexperiment.getStringFromRawResource
import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class TrigonometricShapes(val resources: Resources) : BaseFragmentShader(resources) {

    private var innerTrigFactor: Float = 12f

    override fun getFragmentShaderCode(resources: Resources): String {
        return resources.getStringFromAsset("trig_shapes.glsl")
    }

    override fun handleFragmentAttributes() {
        GLES20.glGetUniformLocation(program, "innerTrigFactor").also {
            GLES20.glUniform1f(it, innerTrigFactor)
        }
    }

    fun updateScaleFactor(scaleFactor: Float) {
        innerTrigFactor += scaleFactor
    }
}