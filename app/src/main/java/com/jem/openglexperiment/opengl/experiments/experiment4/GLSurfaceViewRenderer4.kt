package com.jem.openglexperiment.opengl.experiments.experiment4

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.fragmentshader.ColorOscillator
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer4(private val resources: Resources) : BaseGLSurfaceViewRenderer() {

    private lateinit var colorOscillator: ColorOscillator

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        colorOscillator = ColorOscillator(resources)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        colorOscillator.draw()
    }
}