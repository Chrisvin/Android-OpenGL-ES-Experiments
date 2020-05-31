package com.jem.openglexperiment.opengl.base

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseGLActivity : AppCompatActivity() {

    protected val glSurfaceView: GLSurfaceView by lazy {
        getGLSurfaceViewInstance()
    }

    protected open fun getSnackbarMessage(): String? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(glSurfaceView)
        getSnackbarMessage()?.let { message ->
            Snackbar.make(glSurfaceView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Close") {}
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        glSurfaceView.onResume()
    }

    override fun onPause() {
        glSurfaceView.onPause()
        super.onPause()
    }

    abstract fun getGLSurfaceViewInstance(): GLSurfaceView
}
