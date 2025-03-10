package pl.wsei.pam.lectures.lecture1.widgets

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.lecture1.lib.CustomSpinnerAdapter
import pl.wsei.pam.lectures.lecture1.lib.SpinnerItem

class L01WidgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_l1_widget)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l01WidgetsLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // val mLayout = findViewById<LinearLayout>(R.id.l01WidgetsLayout)
        val mSpinner = findViewById<Spinner>(R.id.l1Spinner)

        // Pobierz obrazek z zasobów
        val myImage: Drawable? = ContextCompat.getDrawable(this, R.drawable.baseline_manage_search_24)

        // Utwórz listę SpinnerItem
        val spinnerItems = listOf(
            SpinnerItem("Adam", myImage!!),
            SpinnerItem("Ewa", myImage),
            SpinnerItem("Karol", myImage),
            SpinnerItem("Władek", myImage),
            SpinnerItem("Włodek", myImage)
        )

        // Utwórz adapter
        val adapter = CustomSpinnerAdapter(this, spinnerItems)

        // Ustaw adapter dla Spinner
        mSpinner.adapter = adapter

    }
}