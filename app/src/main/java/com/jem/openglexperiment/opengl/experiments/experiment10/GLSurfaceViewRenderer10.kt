package com.jem.openglexperiment.opengl.experiments.experiment10

import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.fragmentshader.RainDrops
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer10 : BaseGLSurfaceViewRenderer() {

    private lateinit var rain: RainDrops
    private var height: Int = 1

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        rain = RainDrops()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        rain.setResolution(width, height)
        this.height = height
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        rain.draw()
    }

    public fun setRainAmount(rainAmount: Float) {
        rain.setRainAmount(rainAmount / height)
    }
}