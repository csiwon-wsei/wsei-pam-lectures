package pl.wsei.pam.lectures.lecture2

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pl.wsei.pam.lectures.lecture1.lib.createLayout

class ProgrammaticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tworzenie układu aktywności
        val mainLayout = LinearLayout(this)
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.setBackgroundColor(Color.LTGRAY)
        mainLayout.gravity = Gravity.CENTER
        mainLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        val myTextView = TextView(this)
        myTextView.text = "Witaj, świecie!\nAktywność utworzona programowo"
        myTextView.textSize = 24f
        myTextView.setTextColor(Color.BLACK)
        myTextView.gravity = Gravity.CENTER
        myTextView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 32)
        }

        val myButton = Button(this)
        myButton.text = "Odczytaj parametry intencji!"
        myButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        myButton.setOnClickListener {
            // 5. Odczyt parametrów przekazanych do aktywności
            var name = intent.getStringExtra("name")
            var age = intent.getIntExtra("age", -1)
            var lectures = intent.getStringArrayExtra("lectures")

            mainLayout.addView(TextView(this).also {
                it.text = "Parametry odczytane z intencji"
                it.layoutParams = createLayout(0f)
                it.gravity = Gravity.CENTER
            })

            mainLayout.addView(TextView(this).also {
                it.text = "Name: $name\nAge: $age\nLectures: ${lectures?.joinToString(", ")}"
                it.layoutParams = createLayout(0f)
                it.gravity = Gravity.CENTER
            })
        }

        // 4. Dodaj widoki do kontenera
        mainLayout.addView(myTextView)
        mainLayout.addView(myButton)

        // 6. Ustaw główny widok
        setContentView(mainLayout)
    }
}