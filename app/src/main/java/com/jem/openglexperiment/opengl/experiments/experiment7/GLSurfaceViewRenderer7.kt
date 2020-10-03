package com.jem.openglexperiment.opengl.experiments.experiment7

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.fragmentshader.SmoothPulse
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer7(val resources: Resources) : BaseGLSurfaceViewRenderer() {

    private lateinit var pulse: SmoothPulse

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        pulse = SmoothPulse(resources)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        pulse.setResolution(width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        pulse.draw()
    }

    fun addTouchLocation(x: Float, y: Float) {
        if (::pulse.isInitialized) {
            pulse.addTouchLocation(x, y)
        }
    }

    fun undoTouchLocation() {
        if (::pulse.isInitialized) {
            pulse.undoTouchLocation()
        }
    }
}