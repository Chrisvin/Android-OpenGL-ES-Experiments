package com.jem.openglexperiment.opengl.experiments.experiment8

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView8 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer8()
    }
}