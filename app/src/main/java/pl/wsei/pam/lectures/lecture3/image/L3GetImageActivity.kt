package pl.wsei.pam.lectures.lecture3.image

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R

class L3GetImageActivity : AppCompatActivity() {
    lateinit var mImageView: ImageView
    lateinit var mButton: Button
    lateinit var mGetImage: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_l3_get_image)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l4Example1Btn)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mButton = findViewById(R.id.l3GetImageBtn)
        mImageView = findViewById(R.id.l3ImageView)
        mGetImage =
            registerForActivityResult<String, Uri>(
                ActivityResultContracts.GetContent()
            ) {
                result: Uri? ->
                    if (result != null) {
                        mImageView.setImageURI(result)
                    }
            }
    }

    fun OnGetImageButtonClick(v: View) {
        try {
            mGetImage.launch("image/*")
        } catch (e: Exception) {
            Toast.makeText(
                this,
                e.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}