package pl.wsei.pam.lectures.lecture1.buttons

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.TEXT_ALIGNMENT_CENTER
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Space
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import pl.wsei.pam.lectures.R

class L01ButtonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_l1_buttons)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l01ButtonsLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mLayout = findViewById<LinearLayout>(R.id.l01ButtonsLayout)
        val context = this

        /// Button
        val textButton = Button(this).also{
            it.text = "Text button\n(click)"
            it.tooltipText = "Tooltip"
            it.setOnClickListener(){
                Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show()
            }
            it.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).also {
                it.weight = 1.0F
                it.setMargins(16, 16, 16, 16)
            }
        }
        mLayout.addView(textButton)

        /// Styled Button
        val styledButton = Button(ContextThemeWrapper(context, R.style.AppButton), null, 0).also{
            it.text = "Styled text button\n(click)"
            it.tooltipText = "Tooltip"
            it.setOnClickListener(){
                Toast.makeText(context, "Styled button clicked", Toast.LENGTH_SHORT).show()
            }
            it.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 1.0F
                setMargins(16, 16, 16, 16)
            }
        }
        mLayout.addView(styledButton)

        /// ImageButton
        val imageButton = ImageButton(this).also {
            it.setImageResource(R.drawable.wsei_logo_svg)
            it.setOnClickListener(){
                Toast.makeText(context, "Image Button clicked", Toast.LENGTH_SHORT).show()
            }
            it.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 1.0F
                setMargins(16, 16, 16, 16)
            }
        }
        mLayout.addView(imageButton)

        /// MaterialButton
        val materialButton = MaterialButton(this).also{
            it.text = "Material button"
            it.textAlignment = MaterialButton.TEXT_ALIGNMENT_CENTER
            it.setIconResource(R.drawable.sharp_attach_email_24)
            it.setOnClickListener(){
                Toast.makeText(context, "Material Button clicked", Toast.LENGTH_SHORT).show()
            }
            it.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 1.0F
                setMargins(16, 16, 16, 16)
            }
            it.iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START

        }
        mLayout.addView(materialButton)

        /// Switch
        val switchButton = SwitchCompat(this).also {
            it.text = "Switch (Off)"
            it.isActivated = true
            it.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 1.0F
                setMargins(16, 16, 16, 16)
            }
        }
        switchButton.setOnClickListener(){
            if(switchButton.text.endsWith("(Off)")){
                switchButton.text = "Switch (On)"
            } else {
                switchButton.text = "Switch (Off)"
            }
        }
        mLayout.addView(switchButton)

        /// Checkbox
        val checkboxButton = CheckBox(this).also{
            it.text = "Check box (unchecked)"
            it.isChecked = false
            it.setOnCheckedChangeListener(){ sender, _ ->
                if(it.text.endsWith("(unchecked)")) {
                    it.text = "Check box (checked)"
                    sender.setBackgroundResource(R.color.colorTertiary)
                } else {
                    it.text = "Check box (unchecked)"
                    sender.setBackgroundResource(R.color.white)
                }
            }
        }
        mLayout.addView(checkboxButton)

        /// RadioGroup and Radio
        val radioGroup = RadioGroup(this).apply {
            layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 1.0F
                setMargins(16, 16, 16, 16)
            }
        }
        radioGroup.setOnClickListener(){
            Toast.makeText(context, "Image Button clicked ${radioGroup.checkedRadioButtonId}", Toast.LENGTH_SHORT).show()
        }
        radioGroup.addView(RadioButton(this).also {
            it.text = "Option 1"
        })
        radioGroup.addView(RadioButton(this).also {
            it.text = "Option 2"
        })
        radioGroup.addView(RadioButton(this).also {
            it.text = "Option 3"
        })
        mLayout.addView(TextView(context).apply {
            text = "Radio group"
            textAlignment = TEXT_ALIGNMENT_CENTER
        })
        mLayout.addView(radioGroup)

        /// ChipGroup and Chip
        val chipGroup = ChipGroup(this).apply {
            layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 1.0F
                setMargins(16, 16, 16, 16)
            }
        }
        chipGroup.addView(Chip(this).apply {
            text = "Chip 1"
            setChipIconResource(R.drawable.baseline_manage_search_24)
            isChecked = true
            isCloseIconVisible = true
            isCheckable = true
            setOnCloseIconClickListener {
                chipGroup.removeView(it)
            }
        })
        chipGroup.addView(Chip(this).apply {
            text = "Chip 2"
            setChipIconResource(R.drawable.baseline_error_24)
            isChecked = false
            isCheckable = true
        })
        chipGroup.addView(Chip(this).apply {
            text = "Chip 1"
            setChipIconResource(R.drawable.sharp_attach_email_24)
            isChecked = true
            isCheckable = true
        })
        mLayout.addView(Space(this ))
        mLayout.addView(TextView(this).apply {
            text = "Chip group"
            textAlignment = TEXT_ALIGNMENT_CENTER
        })
        mLayout.addView(chipGroup)
    }



}