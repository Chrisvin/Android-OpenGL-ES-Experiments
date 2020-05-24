package com.jem.openglexperiment.opengl.experiment1

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class GLSurfaceView1 : GLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val renderer: GLSurfaceViewRenderer1

    init {
        setEGLContextClientVersion(2)
        renderer = GLSurfaceViewRenderer1()
        setRenderer(renderer)
    }

}