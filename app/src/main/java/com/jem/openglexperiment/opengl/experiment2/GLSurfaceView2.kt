package com.jem.openglexperiment.opengl.experiment2

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.jem.openglexperiment.opengl.base.BaseGLSurfaceView

class GLSurfaceView2 : BaseGLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val TOUCH_SCALE_FACTOR: Float = 0.2f
    private var previousX: Float = 0f
    private var previousY: Float = 0f

    init {
        renderMode = RENDERMODE_WHEN_DIRTY
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val x: Float = e.x
        val y: Float = e.y
        when (e.action) {
            MotionEvent.ACTION_MOVE -> {
                var dx: Float = x - previousX
                var dy: Float = y - previousY
                // reverse direction of rotation above the mid-line
                if (y > height / 2) {
                    dx *= -1
                }
                // reverse direction of rotation to left of the mid-line
                if (x < width / 2) {
                    dy *= -1
                }
                (glRenderer as? GLSurfaceViewRenderer2?)?.updateAngle((dx + dy) * TOUCH_SCALE_FACTOR)
                requestRender()
            }
        }
        previousX = x
        previousY = y
        return true
    }

    override fun getRendererInstance(): Renderer {
        return GLSurfaceViewRenderer2()
    }

}