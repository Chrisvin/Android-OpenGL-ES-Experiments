package com.jem.openglexperiment.extension

import android.content.res.Resources

fun Resources.getStringFromAsset(filename: String): String {
    val codeStream = this.assets.open(filename)
    val code = codeStream.bufferedReader().use { it.readText() }
    codeStream.close()
    return code
}
