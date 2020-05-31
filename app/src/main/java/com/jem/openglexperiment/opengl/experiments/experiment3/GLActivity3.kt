package com.jem.openglexperiment.opengl.experiments.experiment3

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity3 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView3(this)
    }

    override fun getSnackbarMessage(): String? {
        return "Pinch to scale."
    }
}