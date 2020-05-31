package com.jem.openglexperiment.opengl.experiments.experiment8

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity8 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView8(this)
    }
}