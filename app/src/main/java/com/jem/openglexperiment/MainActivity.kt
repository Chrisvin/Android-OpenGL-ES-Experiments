package com.jem.openglexperiment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.jem.openglexperiment.opengl.base.BaseGLActivity
import com.jem.openglexperiment.opengl.experiments.experiment1.GLActivity1
import com.jem.openglexperiment.opengl.experiments.experiment2.GLActivity2
import com.jem.openglexperiment.opengl.experiments.experiment3.GLActivity3
import com.jem.openglexperiment.opengl.experiments.experiment4.GLActivity4
import com.jem.openglexperiment.opengl.experiments.experiment5.GLActivity5
import com.jem.openglexperiment.opengl.experiments.experiment6.GLActivity6
import com.jem.openglexperiment.opengl.experiments.experiment7.GLActivity7
import com.jem.openglexperiment.opengl.experiments.experiment8.GLActivity8
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addActivityButtons(
            GLActivity1::class.java,
            GLActivity2::class.java,
            GLActivity3::class.java,
            GLActivity4::class.java,
            GLActivity5::class.java,
            GLActivity6::class.java,
            GLActivity7::class.java,
            GLActivity8::class.java
        )
    }

    private fun addActivityButtons(vararg activities: Class<out BaseGLActivity>) {
        activities.forEachIndexed { index, activity ->
            val button = Button(this@MainActivity).apply {
                setOnClickListener {
                    startActivity(Intent(this@MainActivity, activity))
                }
                text = "Experiment ${index + 1}"
            }
            activityList.addView(
                button,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

}
