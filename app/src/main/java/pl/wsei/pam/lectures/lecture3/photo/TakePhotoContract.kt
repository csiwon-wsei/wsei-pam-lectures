package pl.wsei.pam.lectures.lecture3.photo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.os.BundleCompat


class TakePictureContract(private val locationForPhotos: Uri) :
    ActivityResultContract<String?, Bitmap?>() {

    override fun createIntent(context: Context, input: String?): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(
            MediaStore.EXTRA_OUTPUT,
            Uri.withAppendedPath(locationForPhotos, input)
        )
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(MainActivity.this, AUTHORITY, f));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*")
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bitmap? {
        return if (resultCode != Activity.RESULT_OK || intent == null) {
            null
        } else BundleCompat.getParcelable(Bundle.EMPTY,"data", Bitmap::class.java)!!
    }
}

