package pl.wsei.pam.lectures.lecture1.text

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R

class L01TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_l1_text)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l01TextLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mLayout = findViewById<LinearLayout>(R.id.l01TextLayout)


        /// Plain text edit
        val plainTextEdit = EditText(this).also{
            it.textSize = 24.0F
            it.inputType = EditorInfo.TYPE_CLASS_TEXT
        }

        mLayout.addView(TextView(this).apply{
            text = "Edit text with plain keyboard"
            textSize = 12.0F
        })
        mLayout.addView(plainTextEdit)


        /// Number text edit
        val numberEditText = EditText(this).also{
            it.textSize = 24.0F
            it.inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
        }

        mLayout.addView(TextView(this).apply{
            text = "Edit text with number keyboard"
            textSize = 12.0F
        })
        mLayout.addView(numberEditText)

        /// Date text edit
        val dateEditText = EditText(this).also{
            it.textSize = 24.0F
            it.inputType = EditorInfo.TYPE_CLASS_DATETIME
        }

        mLayout.addView(TextView(this).apply{
            text = "Edit date with number keyboard"
            textSize = 12.0F
        })
        mLayout.addView(dateEditText)


    }
}


