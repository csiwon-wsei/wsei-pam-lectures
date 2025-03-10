package pl.wsei.pam.lectures.lecture6.workmanager
import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.OkHttpClient
import okhttp3.Request

class DownloadWorker(
    val context: Context,
    private val parameters: WorkerParameters
) : Worker(context, parameters) {
    override fun doWork(): Result {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(parameters.inputData.getString("url")?:"")
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            context.openFileOutput("animal.jpg", Context.MODE_PRIVATE).use{
                it.write(response.body?.bytes())
                return Result.success(Data.Builder().putString("filename", "animal.jpg").build())
            }
        }
        return Result.failure()
    }
}