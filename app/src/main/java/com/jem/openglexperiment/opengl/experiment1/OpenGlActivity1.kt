package com.jem.openglexperiment.opengl.experiment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OpenGLActivity1 : AppCompatActivity() {

    private lateinit var glSurfaceView: GLSurfaceView1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView1(this)
        setContentView(glSurfaceView)
    }
}
