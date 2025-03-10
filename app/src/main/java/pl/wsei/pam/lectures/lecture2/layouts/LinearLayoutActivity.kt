package pl.wsei.pam.lectures.lecture2.layouts

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.ui.Modifier
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import pl.wsei.pam.lectures.R


class LinearLayoutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply{
                setMargins(16)
            }
        }


        val layout1 = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also {
                it.setMargins(16)
            }
        }

        repeat(5) { index ->
            layout1.addView(Button(ContextThemeWrapper(this, R.style.AppButton), null, 0).also {
                it.text = "${index + 1}"
                it.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT).also {
                        it.setMargins(16)
                        it.weight = 1.0F
                }
                it.gravity = Gravity.CENTER_HORIZONTAL
            })
        }
        rootLayout.addView(TextView(this).also{
            it.text = "LineraLayout with weight and width as MATCH_PARENT"
        })

        rootLayout.addView(layout1);

        val layout2 = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also {
                it.setMargins(16)
            }
        }

        repeat(5) { index ->
            layout2.addView(Button(ContextThemeWrapper(this, R.style.AppButton), null, 0).also {
                it.text = "${index + 1}"
                it.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT).also {
                    it.setMargins(16)

                }
               it.gravity = Gravity.CENTER_HORIZONTAL
            })
        }
        rootLayout.addView(TextView(this).also{
            it.text = "LinearLayout without weight and width as WRAP_CONTENT"
        })

        rootLayout.addView(layout2)


        // nested layout
        val layout3 = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).also {
                it.setMargins(16)
            }
        }

        val layout31 = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).also {
                it.setMargins(16)
                it.weight = 1.0f
            }
        }

        layout3.addView(Button(ContextThemeWrapper(this, R.style.AppButton), null, 0).also{
            it.text = "0"
            it.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).also {
                it.setMargins(16)
                it.weight = 1.0f
            }
        })
        layout3.addView(layout31)
        repeat(5) { index ->
            layout31.addView(Button(ContextThemeWrapper(this, R.style.AppButton), null, 0).also {
                it.text = "${index + 1}"
                it.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT).also {
                    it.setMargins(16)
                    it.weight = 1.0f

                }
                it.gravity = Gravity.CENTER
            })
        }
        rootLayout.addView(TextView(this).also{
            it.text = "Nested LinearLayouts"
        })

        rootLayout.addView(layout3)

        setContentView(rootLayout)
    }
}