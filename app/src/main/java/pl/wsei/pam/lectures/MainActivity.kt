package pl.wsei.pam.lectures

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.lecture3.Lecture03Activity
import pl.wsei.pam.lectures.lecture4.Lecture04Activity
import pl.wsei.pam.lectures.lecture5.Lecture05Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l4Example1Btn)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun runLecture03Activity(v: View){
        startActivity(Intent(this, Lecture03Activity::class.java))
    }

    fun runLecture04Activity(v: View){
        startActivity(Intent(this, Lecture04Activity::class.java))
    }

    fun runLecture05Activity(v: View){
        startActivity(Intent(this, Lecture05Activity::class.java))
    }

}