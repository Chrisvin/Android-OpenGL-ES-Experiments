package com.jem.openglexperiment.opengl.experiments.experiment10

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity10 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView10(this)
    }

    override fun getSnackbarMessage(): String? {
        return "Touch to change rain amount."
    }
}