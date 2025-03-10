package pl.wsei.pam.lectures.lecture6.network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pl.wsei.pam.lectures.databinding.ActivityL6NetworkBinding

class L6NetworkActivity : AppCompatActivity() {
    lateinit var mService: NBPApiService
    lateinit var mBinding: ActivityL6NetworkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL6NetworkBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mService = RetrofitInstance.getRetrofitInstance().create(NBPApiService::class.java)
        this.lifecycleScope.launch {
            val table = mService.getTable("C")
            mBinding.l6NetworkTable.text = table[0].table
            mBinding.l6NetworkNumber.text = table[0].no
            mBinding.l6NetworkEffectiveDate.text = table[0].effectiveDate.toString()
        }
    }
}