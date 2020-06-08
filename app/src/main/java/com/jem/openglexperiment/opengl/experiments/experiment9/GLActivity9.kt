package com.jem.openglexperiment.opengl.experiments.experiment9

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class GLActivity9 : BaseGLActivity() {
    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView9(this)
    }

    override fun getSnackbarMessage(): String? {
        return "Pinch to change middle pattern."
    }
}