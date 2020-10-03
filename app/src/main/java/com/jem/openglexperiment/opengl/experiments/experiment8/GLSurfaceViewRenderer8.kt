package com.jem.openglexperiment.opengl.experiments.experiment8

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.fragmentshader.SilexarsCreation
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer8(val resources: Resources) : BaseGLSurfaceViewRenderer() {

    private lateinit var silexarsCreation: SilexarsCreation

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        silexarsCreation = SilexarsCreation(resources)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        silexarsCreation.setResolution(width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        silexarsCreation.draw()
    }

}