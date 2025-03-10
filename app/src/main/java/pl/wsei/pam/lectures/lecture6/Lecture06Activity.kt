package pl.wsei.pam.lectures.lecture6

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.databinding.ActivityL6MainBinding
import pl.wsei.pam.lectures.lecture6.network.L6NetworkActivity
import pl.wsei.pam.lectures.lecture6.permission.L6PermissionActivity
import pl.wsei.pam.lectures.lecture6.workmanager.L6WorkManagerActivity

class Lecture06Activity : AppCompatActivity() {
    private lateinit var mBinding: ActivityL6MainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL6MainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    fun startPermissionActivity(v: View){
        startActivity(Intent(this, L6PermissionActivity::class.java))
    }
    fun startNetworkActivity(v: View){
        startActivity(Intent(this, L6NetworkActivity::class.java))
    }
    fun startSensorActivity(v: View){
        startActivity(Intent(this, L6WorkManagerActivity::class.java))
    }
}