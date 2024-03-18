package pl.wsei.pam.lectures.lecture3.navigation

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import pl.wsei.pam.lectures.R

class L3NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l3_navigation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.l3_menu, menu)
        val mFab = findViewById<FloatingActionButton>(R.id.l3Fab)
        mFab.setOnClickListener() {
            Snackbar.make(
                it,
                "Return to previous activity",
                Snackbar.LENGTH_LONG
            )
                .setAction("Return") { finish() }
                .show()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.l3MenuItemEmail -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.putExtra(Intent.EXTRA_EMAIL, "student@wsei.edu.pl")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Registration")
                intent.putExtra(Intent.EXTRA_TEXT, "Click to register your account!")
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No activity to send email!", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.l3MenuItemReturn -> {
                finish()
            }

            else -> {

            }
        }
        return true
    }
}