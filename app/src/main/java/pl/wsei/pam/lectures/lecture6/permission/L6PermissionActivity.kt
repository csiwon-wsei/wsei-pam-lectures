package pl.wsei.pam.lectures.lecture6.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import pl.wsei.pam.lectures.databinding.ActivityL6PermissionBinding

/**
 * Zgoda w manifeście:
 * <uses-permission android:name="android.permission.READ_CONTACTS" />
 */

class L6PermissionActivity : AppCompatActivity() {
    private var mContactActivityLauncher: ActivityResultLauncher<Intent>? = null
    private var mRequestPermissionLauncher: ActivityResultLauncher<String>? = null
    private lateinit var mContext: Context
    private var mContactDataUri: Uri? = null
    private lateinit var mBinding: ActivityL6PermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL6PermissionBinding.inflate(layoutInflater);
        setContentView(mBinding.root)
        mContext = this.applicationContext
        mBinding.l6PermissionButton.setOnClickListener(this::selectContactButtonListener)
        mContactActivityLauncher = getActivityResultLauncher()
        mRequestPermissionLauncher = getRequestPermissionLauncher()
    }

    private fun getRequestPermissionLauncher(): ActivityResultLauncher<String> {
        return registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                processPhoneNumber()
            } else {
                Toast.makeText(
                    mContext,
                    "No permission for reading contact data",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun processPhoneNumber() {
        val phone = getContactPhoneFromContentResolver(mContactDataUri)
        if (phone != null) {
            mBinding.l6PermissionContactView.text= phone.toString()
        } else {
            mBinding.l6PermissionContactView.text = "Phone number not found"
        }
    }

    private fun askForReadContactPermissions() {
        if (ContextCompat.checkSelfPermission(
                mContext,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            processPhoneNumber()
        } else {
            mRequestPermissionLauncher!!.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun getActivityResultLauncher(): ActivityResultLauncher<Intent> {
        return registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                mContactDataUri = result.data!!.data
                askForReadContactPermissions()
            }
        }
    }

    private fun selectContactButtonListener(e: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        mContactActivityLauncher!!.launch(intent)
    }

    private fun getContactPhoneFromContentResolver(contactUri: Uri?): Uri? {
        val id = contactUri?.lastPathSegment
        val uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, id)
        var result: Uri? = null
        contentResolver.query(uri, null, null, null, null).use { nameCursor ->
            if (nameCursor!!.moveToFirst()) {
                var index =
                    nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val name = nameCursor.getString(index)
                index = nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val number = nameCursor.getString(index)
                result = Uri.parse(String.format("tel:%s", number))
            }
        }
        return result
    }
}