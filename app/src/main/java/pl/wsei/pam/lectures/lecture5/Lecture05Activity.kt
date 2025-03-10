package pl.wsei.pam.lectures.lecture5

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pl.wsei.pam.lectures.databinding.ActivityL5MainBinding
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Lecture05Activity : AppCompatActivity() {
    lateinit var mBinding: ActivityL5MainBinding
    lateinit var mData: AppDatabase
    lateinit var mResult: Deferred<String>
    lateinit var mExecutor: Executor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = ActivityL5MainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mData = AppDatabase.getInstance(this)
        mExecutor = Executors.newSingleThreadExecutor()
        mBinding.l5SaveBtn.setOnClickListener {
            val entity =
                TaskEntity(
                    0,
                    mBinding.l5TaskName.text.toString(),
                    mBinding.l5TaskDone.isChecked
                )
//            mExecutor.execute() {
//                mData.taskDao().insert(entity);
//                for (t in mData.taskDao().findAll()) {
//                    mBinding.l5TaskList.append("${t.toString()}\n")
//                }
//            }
            lifecycleScope.launch(Dispatchers.IO) {
                mData.taskDao().insert(entity)
                mBinding.l5TaskList.text = ""
                for (t in mData.taskDao().findAll()) {
                    withContext(Dispatchers.Main) {
                        mBinding.l5TaskList.append("$t\n")
                    }
                }
            }
        }
    }

}