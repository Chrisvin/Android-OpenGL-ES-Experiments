package com.jem.openglexperiment.opengl.experiments.experiment7

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView7 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer7()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            (glRenderer as? GLSurfaceViewRenderer7?)?.updateTouchLocation(it.x, it.y)
        }
        return true
    }
}