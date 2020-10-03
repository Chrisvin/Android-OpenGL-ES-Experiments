package com.jem.openglexperiment.opengl.experiments.experiment9

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView9 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val scaleGestureDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                detector?.scaleFactor?.let {
                    (glRenderer as? GLSurfaceViewRenderer9?)?.updateScaleFactor(it - 1f)
                }
                return true
            }
        })

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer9(resources)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }
}