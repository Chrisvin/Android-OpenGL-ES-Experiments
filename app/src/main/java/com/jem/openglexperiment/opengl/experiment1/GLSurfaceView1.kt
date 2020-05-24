package com.jem.openglexperiment.opengl.experiment1

import android.content.Context
import android.util.AttributeSet
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView1 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer1()
    }
}