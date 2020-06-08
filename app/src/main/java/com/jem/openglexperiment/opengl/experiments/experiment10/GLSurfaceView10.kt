package com.jem.openglexperiment.opengl.experiments.experiment10

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView10 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer10()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.run {
            (glRenderer as? GLSurfaceViewRenderer10?)?.setRainAmount(y)
        }
        return true
    }
}