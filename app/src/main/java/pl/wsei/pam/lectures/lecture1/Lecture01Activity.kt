package pl.wsei.pam.lectures.lecture1

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.R.id.l1Main
import pl.wsei.pam.lectures.lecture1.buttons.L01ButtonsActivity
import pl.wsei.pam.lectures.lecture1.lib.createButton
import pl.wsei.pam.lectures.lecture1.lib.createLayout
import pl.wsei.pam.lectures.lecture1.text.L01TextActivity
import pl.wsei.pam.lectures.lecture1.widgets.L01WidgetActivity
import androidx.core.view.ViewCompat as ViewCompat1


class Lecture01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_l1_main)
        ViewCompat1.setOnApplyWindowInsetsListener(findViewById(l1Main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mLayout = findViewById<LinearLayout>(l1Main);

        val mButton1 = createButton(this, "Buttons", "Przykłady różnych przycisków").also {
            it.layoutParams = createLayout(1.0F)
            it.setOnClickListener(){
                startActivity(Intent(this, L01ButtonsActivity::class.java))
            }
        }

        val mButton2 = createButton(this, "Text", "Przykłady pól tekstowych").also {
            it.layoutParams = createLayout(1.0F)
            it.setOnClickListener(){
                startActivity(Intent(this, L01TextActivity::class.java))
            }
        }

        val mButton3 = createButton(this, "Widgets", "Przykłady różnych widgetów").also {
            it.layoutParams = createLayout(1.0F)
            it.setOnClickListener(){
                startActivity(Intent(this, L01WidgetActivity::class.java))
            }
        }

        mLayout.addView(mButton1)
        mLayout.addView(mButton2)
        mLayout.addView(mButton3)
    }
}

