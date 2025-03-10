package pl.wsei.pam.lectures.lecture6.workmanager

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.databinding.ActivityL6WorkManagerBinding

class L6WorkManagerActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityL6WorkManagerBinding
    val workTag = "donwload"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL6WorkManagerBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    fun onClickGlideButton(v: View) {
        Glide
            .with(this)
            .load(mBinding.l6WorkManagerInputUrl.text.toString())
            .fitCenter()
            .apply(RequestOptions()
                .placeholder(R.drawable.wsei_logo_svg)  // Placeholder image
                .error(R.drawable.baseline_error_24)    // Error image in case of loading failure
            )
            .into(mBinding.l6WorkManagerImageView)
    }

    fun onClickDownloadButton(v: View) {
        val data = Data.Builder()
            .putString("url", mBinding.l6WorkManagerInputUrl.text.toString())
            .build()
        val workRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setInputData(data)
            .build()
        WorkManager
            .getInstance(this)
            .enqueueUniqueWork(
                "WORK",
                ExistingWorkPolicy.KEEP,
                workRequest
            )
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.id).observe(this) {
            if (it == null) {
                return@observe
            }
            if (it.state.isFinished && it.state == WorkInfo.State.SUCCEEDED) {
                val file = it.outputData.getString("filename")
                val stream = BitmapFactory.decodeStream(openFileInput(file))
                Glide
                    .with(this)
                    .load(stream)
                    .fitCenter()
                    .into(mBinding.l6WorkManagerImageView)
                Log.i("RESULT", "Output $file")
            }
        }
    }
}