package pl.wsei.pam.lectures.lecture3.parameters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.wsei.pam.lectures.databinding.ActivityL3ParameterBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class L3ParameterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityL3ParameterBinding


    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityL3ParameterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.l3Button.text="hello"

        /**
         * Odczyt przekazany w intencji danych w uruchomionej aktywności
         * intent jest właściwościa klasy aktywności
         */
        val name = intent.getStringExtra("name")

        /**
         * Odczyt typów prostych wymaga podania jako drugiego parametru właściwości domyślnej
         * Jeśli w intencji nie ma takiej danej, to metoda getExtraXXX zwróci wartość domyślną
         */
        val seek = intent.getIntExtra("seek", -1)

        /**
         * Odczyt tablcy liczb typu Double
         */
        val arr = intent.getDoubleArrayExtra("array")

        binding.l3Parameters.text = "name: ${name}\nseek: ${seek}\narray: ${arr?.get(0)}"
    }
}