package pl.wsei.pam.lectures.lecture2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.lecture2.layouts.ConstraintLayoutActivity
import pl.wsei.pam.lectures.lecture2.layouts.FrameLayoutActivity
import pl.wsei.pam.lectures.lecture2.layouts.LinearLayoutActivity
import pl.wsei.pam.lectures.lecture2.layouts.TableLayoutActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Lecture02Activity : AppCompatActivity() {
    private val REQUEST_READ_CONTACTS_PERMISSION = 100
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
        val intent = Intent(Intent.ACTION_SENDTO, "smsto:12346556".toUri())
        // intent.putExtra(Intent.EXTRA_STREAM, attachment) // dodanie załącznika w postaci URI
        intent.putExtra("sms_body", "Sending new message")

        startActivity(intent)

    }

    fun runContactIntent(view: View) {
        checkAndRequestContactsPermission()
    }

    private fun checkAndRequestContactsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Uprawnienia nie są nadane, poproś o nie
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_READ_CONTACTS_PERMISSION
            )
        } else {
            // Uprawnienia są nadane, można uruchomić intencję
            pickContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Uprawnienia zostały nadane, uruchom intencję
                pickContact()
            } else {
                // Uprawnienia nie zostały nadane
                Toast.makeText(
                    this,
                    "Uprawnienia do kontaktów nie zostały przyznane",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun pickContact() {
        contactResultLauncher.launch(Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI))
    }


    private var contactResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val contactData: Uri? = result.data?.data
                contactData?.let { uri ->
                    val contactId = uri.lastPathSegment
                    if (contactId != null) {
                        fetchContactDetails(contactId)
                    }
                }
            }
        }

    @SuppressLint("Range")
    private fun fetchContactDetails(contactId: String) {
        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
        )

        val contentResolver = contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            ContactsContract.Contacts._ID + " = ?",
            arrayOf(contactId),
            null
        )

        cursor?.use { c ->
            if (c.moveToFirst()) {
                val nameIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val name = c.getString(nameIndex)
                Log.d("Contact", "Name: $name")

                //Pobierz numery telefonów
                val phoneCursor = getPhoneNumbers(contactId)
                phoneCursor?.use { phoneC ->
                    while (phoneC.moveToNext()) {
                        val phoneNumber =
                            phoneC.getString(phoneC.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        Log.d("Contact", "Phone: $phoneNumber")
                    }
                }
                phoneCursor?.close()
                // Pobierz adresy email
                val emailCursor = getEmails(contactId)
                emailCursor?.use { emailC ->
                    while (emailC.moveToNext()) {
                        val email =
                            emailC.getString(emailC.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
                        Log.d("Contact", "Email: $email")
                    }
                }
                emailCursor?.close()
            }
        }
        cursor?.close()
    }

    private fun getPhoneNumbers(contactId: String): Cursor? {
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val phoneProjection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
        val phoneSelectionArgs = arrayOf(contactId)
        return contentResolver.query(
            phoneUri,
            phoneProjection,
            phoneSelection,
            phoneSelectionArgs,
            null
        )
    }

    private fun getEmails(contactId: String): Cursor? {
        val emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI
        val emailProjection = arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS)
        val emailSelection = ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?"
        val emailSelectionArgs = arrayOf(contactId)
        return contentResolver.query(
            emailUri,
            emailProjection,
            emailSelection,
            emailSelectionArgs,
            null
        )
    }



    fun runPhotoIntent(view: View) {
        dispatchTakePictureIntent()
    }

    private var photoResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            setPic()
        }
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
                photoResultLauncher.launch(takePictureIntent)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
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

    // Przykładowa aktywność z układem liniowym
    fun runLinearLayoutActivity(view: View) {
        startActivity(Intent(this, LinearLayoutActivity::class.java))
    }

    // Przykładowa aktywność z ukłądem tablicowym
    fun runTableLayoutActivity(view: View) {
        startActivity(Intent(this, TableLayoutActivity::class.java))
    }

    // Przykładowa aktywność z układem constraint
    fun runConstraintLayoutActivity(view: View) {
        startActivity(Intent(this, ConstraintLayoutActivity::class.java))
    }

    // Przykładowa aktywność z układem frame
    fun runFrameLayoutActivity(view: View) {
        startActivity(Intent(this, FrameLayoutActivity::class.java))
    }

    // Przykładowe wywołanie intencji z parametrami
    fun runProgrammingActivityWithParameters(view: View) {
        var intent = Intent(this, ProgrammaticActivity::class.java)
        intent.putExtra("name", "Marcin")
        intent.putExtra("age", 23)
        intent.putExtra("lectures", arrayOf("Android", "Java", "Kotlin"))
        startActivity(intent)
    }

}
