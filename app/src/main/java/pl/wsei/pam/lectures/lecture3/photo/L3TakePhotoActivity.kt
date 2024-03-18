package pl.wsei.pam.lectures.lecture3.photo

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.databinding.ActivityL3TakePhotoBinding
import java.lang.Exception

class L3TakePhotoActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityL3TakePhotoBinding
    lateinit var mTakePhoto: ActivityResultLauncher<String?>
    lateinit var mPictureUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l3_take_photo)
        mBinding = ActivityL3TakePhotoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mPictureUri = Uri.parse(applicationInfo.dataDir)
        mBinding.l3TakePhotoButton.setOnClickListener(::onClickTakePhotoButton);
        mTakePhoto = registerForActivityResult(TakePictureContract(mPictureUri)) { result ->
            if (result != null)
                mBinding.l3TakePhotoImageView.setImageBitmap(result)
        }
    }

    fun onClickTakePhotoButton(v: View) {
        try {
            mTakePhoto.launch("image.jpg")
        } catch (e: Exception){
            Toast.makeText(this,"${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}