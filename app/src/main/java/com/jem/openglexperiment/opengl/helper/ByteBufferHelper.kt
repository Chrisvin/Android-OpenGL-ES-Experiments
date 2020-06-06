package com.jem.openglexperiment.opengl.helper

import java.nio.*
import kotlin.reflect.KFunction1

object ByteBufferHelper {

    fun allocateDirectFloatBuffer(coords: FloatArray): FloatBuffer {
        return allocateDirectBuffer(coords.size * 4, ByteBuffer::asFloatBuffer) {
            (it as FloatBuffer).put(coords)
        }
    }

    fun allocateDirectLongBuffer(coords: LongArray): LongBuffer {
        return allocateDirectBuffer(coords.size * 2, ByteBuffer::asLongBuffer) {
            (it as LongBuffer).put(coords)
        }
    }

    private fun <K : Buffer> allocateDirectBuffer(
        capacity: Int,
        bufferFormat: KFunction1<ByteBuffer, K>,
        innerBlock: (buffer: Buffer) -> Unit
    ): K {
        return ByteBuffer.allocateDirect(capacity).run {
            order(ByteOrder.nativeOrder())
            bufferFormat.invoke(this).apply {
                innerBlock.invoke(this)
                position(0)
            }
        }
    }

}