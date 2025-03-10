package pl.wsei.pam.lectures.lecture2.layouts

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import pl.wsei.pam.lectures.R

class TableLayoutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = TableLayout(this).apply {
            setColumnStretchable(0, true)
            setColumnStretchable(2, true)

            setBackgroundColor(getColor(R.color.colorTertiary))
            layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(16)
            }
        }

        rootLayout.addView(TableRow(this).also {
            it.setBackgroundColor(getColor(R.color.colorPrimary))
            it.addView(Button(this).also{
                it.text = "Strechable"
            })
            it.addView(Button(this).apply{
                text = "Schrink"
                setOnClickListener{
                    rootLayout.setColumnStretchable(1, !rootLayout.isColumnStretchable(1))

                }
            })
            it.addView(Button(this).apply{
                text = "Collaps 0"
                setOnClickListener {
                    rootLayout.setColumnCollapsed(0, !rootLayout.isColumnCollapsed(0))
                    rootLayout.setColumnCollapsed(1, !rootLayout.isColumnCollapsed(1))
                }
            })
        })
        rootLayout.addView(TableRow(this).also {
            it.addView(TextView(this).also {
                it.text = "Cell 1"
                it.setBackgroundResource(R.drawable.border)
            })
            it.addView(TextView(this).also {
                it.text = "Cell 2"
                it.setBackgroundResource(R.drawable.border)
            })
            it.addView(TextView(this).also {
                it.text = "Cell 3"
                it.gravity = Gravity.RIGHT
                it.setBackgroundResource(R.drawable.border)
            })
        })
        rootLayout.addView(TableRow(this).also {
            it.addView(TextView(this).also {
                it.text = "Cell 4"
                it.setBackgroundResource(R.drawable.border)
            })
            it.addView(TextView(this).also {
                it.text = "Cell 5"
                it.setBackgroundResource(R.drawable.border)
            })
            it.addView(TextView(this).also {
                it.text = "Cell 6"
                it.gravity = Gravity.RIGHT
                it.setBackgroundResource(R.drawable.border)
            })
        })
        rootLayout.addView(Button(this).apply{
            text = "Spanned"
            setOnClickListener {

            }
        })
        setContentView(rootLayout)
    }
}