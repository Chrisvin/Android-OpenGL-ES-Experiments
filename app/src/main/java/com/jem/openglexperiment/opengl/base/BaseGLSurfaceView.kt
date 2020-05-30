package com.jem.openglexperiment.opengl.base

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

abstract class BaseGLSurfaceView : GLSurfaceView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    protected val glRenderer: Renderer by lazy {
        getRendererInstance()
    }

    init {
        this.setEGLContextClientVersion(2)
        this.debugFlags = DEBUG_CHECK_GL_ERROR or DEBUG_LOG_GL_CALLS
        this.setRenderer(glRenderer)
    }

    abstract fun getRendererInstance(): Renderer

}