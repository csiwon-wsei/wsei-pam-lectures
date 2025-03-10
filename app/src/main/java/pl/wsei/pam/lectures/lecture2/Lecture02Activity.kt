package pl.wsei.pam.lectures.lecture2

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.lecture2.layouts.ConstraintLayoutActivity
import pl.wsei.pam.lectures.lecture2.layouts.FrameLayoutActivity
import pl.wsei.pam.lectures.lecture2.layouts.LinearLayoutActivity
import pl.wsei.pam.lectures.lecture2.layouts.TableLayoutActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class Lecture02Activity : AppCompatActivity() {

    lateinit var mPhotoImage: ImageView
    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_l2_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l2main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mPhotoImage = findViewById<ImageView>(R.id.l2PhotoImage)
    }


    fun runSMSIntent(view: View) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:12346556"))
        intent.putExtra("sms_body", "Sending new message")
        startActivity(intent)
    }

    fun runPhotoIntent(view: View) {
        dispatchTakePictureIntent()
    }

    fun runFrameLayoutActivity(view: View) {
        startActivity(Intent(this, FrameLayoutActivity::class.java))
    }

    fun runProgrammingActivityWithParameters(view: View) {
        var intent = Intent(this, ProgrammaticActivity::class.java)
        intent.putExtra("name", "Marcin")
        intent.putExtra("age", 23)
        intent.putExtra("lectures", arrayOf("Android", "Java", "Kotlin"))
        startActivity(intent)
    }

    fun runLinearLayoutActivity(view: View) {
        startActivity(Intent(this, LinearLayoutActivity::class.java))
    }

    fun runTableLayoutActivity(view: View) {
        startActivity(Intent(this, TableLayoutActivity::class.java))
    }

    fun runConstraintLayoutActivity(view: View) {
        startActivity(Intent(this, ConstraintLayoutActivity::class.java))
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            photoFile?.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "${packageName}.fileprovider", // Poprawny identyfikator
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                resultLauncher.launch(takePictureIntent)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            setPic()
        }
    }

    private fun setPic() {
        // Get the dimensions of the View
        val targetW: Int = mPhotoImage.width
        val targetH: Int = mPhotoImage.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            BitmapFactory.decodeFile(currentPhotoPath, this)

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.max(1, Math.min(photoW / targetW, photoH / targetH))

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            mPhotoImage.setImageBitmap(bitmap)
        }
    }



}
