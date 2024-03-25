package pl.wsei.pam.lectures.lecture3.group

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.databinding.ActivityL3GroupViewBinding

class L3GroupViewActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityL3GroupViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null){
            val seek = savedInstanceState.getString("text");
            mBinding.l3GroupBtn01.text = seek
        }
        mBinding = ActivityL3GroupViewBinding.inflate(layoutInflater);
        setContentView(mBinding.root)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_l3_group_view)

        mBinding.choice = "Select radio"
        mBinding.l3RadioGroup.setOnCheckedChangeListener() { radioGroup: RadioGroup, i: Int ->
            mBinding.choice = "Selected ${findViewById<RadioButton>(i).text}"
        }
    }

    fun onClickShowHideButton(v: View){
        Toast.makeText(this, "Selected: ${mBinding.choice}", Toast.LENGTH_SHORT).show()
        if (mBinding.l3Group.visibility == View.GONE) {
            mBinding.l3Group.visibility = View.VISIBLE
            mBinding.l3GroupBtn01.text = "Hide"
        } else {
            mBinding.l3Group.visibility = View.GONE
            mBinding.l3GroupBtn01.text = "Show"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text", mBinding.l3GroupBtn01.text.toString())
    }
}