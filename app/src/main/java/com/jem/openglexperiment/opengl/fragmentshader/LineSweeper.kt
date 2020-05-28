package com.jem.openglexperiment.opengl.fragmentshader

import com.jem.openglexperiment.opengl.base.BaseFragmentShader

class LineSweeper : BaseFragmentShader() {
    override fun getFragmentShaderCode(): String {
        return "precision mediump float;" +
                "" +
                "float plot (float t, float pct){" +
                "   return smoothstep(pct - 0.5, pct, t) - " +
                "       smoothstep(pct, pct + 0.5, t);" +
                "}" +
                "" +
                "void main() {" +
                "   float theta = vTime * 0.002;" +
                "   vec2 coords = gl_FragCoord.xy / vResolution.xy;" +
                "   vec3 color = mix(" +
                "       vec3(0.509,0.470,0.755)," +
                "       mix(" +
                "           vec3(0.350,0.990,1.000)," +
                "           vec3(1.0)," +
                "           abs(sin(theta))" +
                "       )," +
                "       plot(" +
                "           abs(cos(theta)) + coords.x," +
                "           abs(sin(theta)) + coords.y" +
                "       )" +
                "   );" +
                "   gl_FragColor = vec4(color,1.0);" +
                "}"
    }

    override fun handleFragmentAttributes() {
    }
}