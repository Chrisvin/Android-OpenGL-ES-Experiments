package com.jem.openglexperiment.opengl.experiments.experiment6

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity6 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView6(this)
    }
}