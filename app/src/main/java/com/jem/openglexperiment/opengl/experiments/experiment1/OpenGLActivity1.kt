package com.jem.openglexperiment.opengl.experiments.experiment1

import android.opengl.GLSurfaceView
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class OpenGLActivity1 : BaseGLActivity() {

    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView1(
            this
        )
    }

}
