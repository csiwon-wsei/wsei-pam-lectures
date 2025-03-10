package pl.wsei.pam.lectures

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.lecture1.Lecture01Activity
import pl.wsei.pam.lectures.lecture2.Lecture02Activity
import pl.wsei.pam.lectures.lecture3.Lecture03Activity
import pl.wsei.pam.lectures.lecture4.Lecture04Activity
import pl.wsei.pam.lectures.lecture5.Lecture05Activity
import pl.wsei.pam.lectures.lecture6.Lecture06Activity
import pl.wsei.pam.lectures.lecture7.Lecture07Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Lectures)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l1MainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun runLecture01Activity(view: View) {
        startActivity(Intent(this, Lecture01Activity::class.java))
    }

    fun runLecture02Activity(view: View) {
        startActivity(Intent(this, Lecture02Activity::class.java))
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

    fun runLecture06Activity(v: View){
        startActivity(Intent(this, Lecture06Activity::class.java))
    }

    fun runLecture07Activity(v: View){
        startActivity(Intent(this, Lecture07Activity::class.java))
    }
}