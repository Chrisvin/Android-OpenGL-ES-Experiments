package com.jem.openglexperiment.opengl.fragmentshader

import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class SimplePulse : BaseFragmentShader() {
    override fun getFragmentShaderCode(): String {
        // Reference Source: https://www.shadertoy.com/view/Md3XRB
        return "precision mediump float;" +
                "void main() {" +
                "   float position = length((gl_FragCoord.xy / vResolution.xy) - 0.5);" +
                "   gl_FragColor = smoothstep(2.4, 7.4, 1.6 * sin(position * 30.0 - 0.01 * vTime) + (0.4/position) - vec4(0.0));" +
                "}"
    }

    override fun handleFragmentAttributes() {
    }
}