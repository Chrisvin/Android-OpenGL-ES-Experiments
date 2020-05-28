package com.jem.openglexperiment.opengl.experiments.experiment5

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity5 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView5(this)
    }
}