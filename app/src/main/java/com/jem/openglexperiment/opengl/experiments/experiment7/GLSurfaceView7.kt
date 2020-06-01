package com.jem.openglexperiment.opengl.experiments.experiment7

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView7 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val singleTapDetector: GestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                e?.let {
                    (glRenderer as? GLSurfaceViewRenderer7?)?.addTouchLocation(it.x, it.y)
                }
                return true
            }
        })

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer7()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        singleTapDetector.onTouchEvent(event)
        return true
    }

    private fun undoTouchLocation() {
        (glRenderer as? GLSurfaceViewRenderer7?)?.undoTouchLocation()
    }
}