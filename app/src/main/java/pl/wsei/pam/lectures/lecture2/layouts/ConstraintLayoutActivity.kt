package pl.wsei.pam.lectures.lecture2.layouts

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.appcompat.app.AppCompatActivity as AppCompatActivity1

class ConstraintLayoutActivity : AppCompatActivity1() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

        val button1 = Button(this).apply {
            text = "Top"
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).also {
                //it.setMargins(16)
                it.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                it.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                it.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            }
        }


        val button2 = Button(this).apply {
            id = View.generateViewId()
            text = "Left"
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).also {
                //it.setMargins(16)
                it.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                it.topToBottom = button1.id
            }
        }

        val button3 = Button(this).apply {
            text = "Right"
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).also {
               //it.setMargins(16)
                it.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                it.topToBottom= button1.id
            }
        }

        val button4 = Button(this).apply {
            text = "Center"
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            ).also {
                //it.setMargins(16)
                it.startToEnd = button2.id
                it.endToStart = button3.id
                it.topToBottom= button1.id
                it.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }
        }

        button2.layoutParams.apply {
            (this as ConstraintLayout.LayoutParams).endToStart = button4.id
        }
        button3.layoutParams.apply {
            (this as ConstraintLayout.LayoutParams).startToEnd = button4.id
        }

        val button5 = Button(this).apply {
            text = "Bottom Left"
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).also {
                //it.setMargins(16)
                it.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                it.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                it.endToStart = button4.id
            }
        }

        rootLayout.addView(button1)
        rootLayout.addView(button2)
        rootLayout.addView(button3)
        rootLayout.addView(button4)
        rootLayout.addView(button5)

        setContentView(rootLayout)
    }
}