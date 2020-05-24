package com.jem.openglexperiment.opengl.experiment1

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class OpenGLActivity1 : BaseGLActivity() {

    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView1(this)
    }

}
