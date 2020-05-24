package com.jem.openglexperiment.opengl.base

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseGLActivity : AppCompatActivity() {

    protected val glSurfaceView: GLSurfaceView by lazy {
        getGLSurfaceViewInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(glSurfaceView)
    }

    abstract fun getGLSurfaceViewInstance(): GLSurfaceView
}
