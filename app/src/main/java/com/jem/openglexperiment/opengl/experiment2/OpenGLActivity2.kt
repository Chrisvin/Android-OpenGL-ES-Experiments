package com.jem.openglexperiment.opengl.experiment2

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jem.openglexperiment.opengl.base.BaseGLActivity

class OpenGLActivity2: BaseGLActivity() {

    override fun getGLSurfaceViewInstance(): GLSurfaceView {
        return GLSurfaceView2(this)
    }

}