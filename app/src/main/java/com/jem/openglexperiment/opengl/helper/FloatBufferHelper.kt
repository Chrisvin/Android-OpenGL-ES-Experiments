package com.jem.openglexperiment.opengl.helper

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

object FloatBufferHelper {

    fun allocateDirectBuffer(capacity: Int, coords: FloatArray): FloatBuffer {
        return ByteBuffer.allocateDirect(capacity).run {
            order(ByteOrder.nativeOrder())
            asFloatBuffer().apply {
                put(coords)
                position(0)
            }
        }
    }

}