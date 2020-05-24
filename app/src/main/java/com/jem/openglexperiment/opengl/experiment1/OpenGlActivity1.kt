package com.jem.openglexperiment.opengl.experiment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jem.openglexperiment.R

class OpenGlActivity1 : AppCompatActivity() {

    private lateinit var glSurfaceView: GLSurfaceView1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView1(this)
        setContentView(glSurfaceView)
    }
}
