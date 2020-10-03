package com.jem.openglexperiment.opengl.experiments.experiment9

import android.content.res.Resources
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceViewRenderer
import com.jem.openglexperiment.opengl.fragmentshader.TrigonometricShapes
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLSurfaceViewRenderer9(val resources: Resources) : BaseGLSurfaceViewRenderer() {

    private lateinit var trigShapes: TrigonometricShapes

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        trigShapes = TrigonometricShapes(resources)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        trigShapes.setResolution(width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        trigShapes.draw()
    }

    fun updateScaleFactor(scaleFactor: Float) {
        trigShapes.updateScaleFactor(scaleFactor)
    }
}