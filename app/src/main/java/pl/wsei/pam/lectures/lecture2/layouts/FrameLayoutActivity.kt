package pl.wsei.pam.lectures.lecture2.layouts

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import pl.wsei.pam.lectures.R

class FrameLayoutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootLayout = FrameLayout(this).apply{
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            ).also{
                it.gravity = Gravity.CENTER
                it.setMargins(32)
            }
        }
        rootLayout.addView(TextView(this).also {
            it.text = "Frame Layout\nchildren overlapping each other"
            it.setBackgroundColor(getColor(R.color.colorTertiary))
        })

        rootLayout.addView(Button(this).also{
            it.text = "Big Center"
            it.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).also{
                it.gravity = Gravity.CENTER
                it.width = 400
                it.height = 400
            }
        })

        rootLayout.addView(Button(this).also{
            it.text = "Center"
            it.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).also{
                it.gravity = Gravity.CENTER
            }
        })


        rootLayout.addView(Button(this).also{
            it.text = "Left"
            it.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).also{
                it.gravity = Gravity.START or Gravity.CENTER_VERTICAL
            }
        })

        rootLayout.addView(Button(this).also{
            it.text = "Rogth Bottom"
            it.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).also{
                it.gravity = Gravity.BOTTOM or Gravity.END
            }
        })




        setContentView(rootLayout)
    }
}