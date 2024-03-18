package pl.wsei.pam.lectures.lecture4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pl.wsei.pam.lectures.databinding.ActivityL4MainBinding
import pl.wsei.pam.lectures.lecture4.binding.L4BindingActivity
import pl.wsei.pam.lectures.lecture4.fragments.L4FragmentActivity
import pl.wsei.pam.lectures.lecture4.lifecycle.L4LifeCycleActivity

class Lecture04Activity : AppCompatActivity() {
    lateinit var mBinding: ActivityL4MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL4MainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    fun l4Example01Run(v: View){
        startActivity(Intent(this, L4BindingActivity::class.java))
    }
    fun l4Example02Run(v: View){
        startActivity(Intent(this, L4FragmentActivity::class.java))
    }
    fun l4Example03Run(v: View){
        startActivity(Intent(this, L4LifeCycleActivity::class.java))
    }
    fun l4Example04Run(v: View){

    }

}