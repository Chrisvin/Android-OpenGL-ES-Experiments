package com.jem.openglexperiment.opengl.experiments.experiment5

import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.fragmentshader.LineSweeper
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer5 : BaseGLSurfaceViewRenderer() {

    private lateinit var pulse: LineSweeper

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        pulse = LineSweeper()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        pulse.setResolution(width.toFloat(), height.toFloat())
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        pulse.draw()
    }
}