package com.jem.openglexperiment.opengl.experiments.experiment3

import android.content.Context
import android.util.AttributeSet
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView3: BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer3()
    }
}