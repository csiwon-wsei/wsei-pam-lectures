package pl.wsei.pam.lectures.lecture3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.lecture3.group.L3GroupViewActivity
import pl.wsei.pam.lectures.lecture3.image.L3GetImageActivity
import pl.wsei.pam.lectures.lecture3.navigation.L3NavigationActivity
import pl.wsei.pam.lectures.lecture3.parameters.L3ParameterActivity
import pl.wsei.pam.lectures.lecture3.photo.L3TakePhotoActivity
import pl.wsei.pam.lectures.lecture3.recycler.L3RecyclerViewActivity

class Lecture03Activity : AppCompatActivity() {
    lateinit var mText: TextView
    lateinit var mSeekBar: SeekBar
    lateinit var mSeekBarLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_l3_main)
        mText = findViewById(R.id.l3inputText)
        mSeekBar = findViewById(R.id.l3seekBar)

        mSeekBarLabel = findViewById<TextView?>(R.id.l3SeekBarLabel).also {
            it.text ="0"
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l3main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /**
         * Ustawienie słucha dla zdarzenia zmiany położenia SekkeBar
         * Słuchaczem jest anonimowy obiekt implementujący interfejs słuchacza
         */
        mSeekBar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mSeekBarLabel.text = "${mSeekBar.progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })

    }

    /**
     * Tworzenie intencji z przekazaniem parametrów do uruchamianej aktywności
     */
    public fun runExample1(v: View) {
        val intentP = Intent(this, L3ParameterActivity::class.java)
        /**
         * Metoda putExtra umieszcza w intencji wartość o podanym kluczu
         */
        intentP.putExtra("name", mText.text.toString())
        /**
         * Przeciążone metody putExtra akceptują takie typy proste, łańcuchy jak i tablice tych typów:
         * int,
         */
        intentP.putExtra("seek", mSeekBar.progress)
        /**
         * Przykład przekazania tablicy
         */
        val arr: DoubleArray = doubleArrayOf(1.2, 2.4, 3.5);
        intentP.putExtra("array", arr);

        startActivity(intentP)
    }

    /**
     * Tworzenie intencji za akcją - uruchomienie aktywności służącej do wysyłania sms'a
     * Stałe zawarte w klasie Intent określają akcje.
     */
    public fun runExample2(v: View) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:12346556"))
        intent.putExtra("sms_body", "Sending new message")
        startActivity(intent)
    }

    /**
     * Tworzenie intencji akcji wykonanania fotografii.
     * Wywołana aktywność zwraca wynik, który w tej aktywności można przetważać
     */
    fun runExample3(v: View) {
        startActivity(Intent(this, L3GetImageActivity::class.java))
    }

    fun runExample4(v: View) {
       startActivity(Intent(this, L3TakePhotoActivity::class.java))
    }

    fun runExample5(v: View) {
        startActivity(Intent(this, L3GroupViewActivity::class.java))
    }

    fun runExample6(v: View){
        startActivity(Intent(this, L3RecyclerViewActivity::class.java))
    }

    fun runExample7(v: View){
        startActivity(Intent(this, L3NavigationActivity::class.java))
    }
}