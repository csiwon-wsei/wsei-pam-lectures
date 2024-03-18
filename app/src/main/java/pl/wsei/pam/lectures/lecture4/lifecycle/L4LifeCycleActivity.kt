package pl.wsei.pam.lectures.lecture4.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.databinding.ActivityL4LifeCycleBinding
import pl.wsei.pam.lectures.lecture4.lifecycle.ui.main.SectionsPagerAdapter

class L4LifeCycleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityL4LifeCycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityL4LifeCycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab
        fab.setOnClickListener { view ->

        }
    }
    override fun onStart() {
        super.onStart()
        Log.i("LIFE CYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFE CYCLE", "onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.i("LIFE CYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFE CYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFE CYCLE", "onDestroy")
    }

}