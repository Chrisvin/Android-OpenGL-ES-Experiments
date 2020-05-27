package com.jem.openglexperiment.opengl.experiments.experiment4

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity4 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView4(this)
    }
}