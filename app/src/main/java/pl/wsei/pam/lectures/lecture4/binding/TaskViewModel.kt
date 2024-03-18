package pl.wsei.pam.lectures.lecture4.binding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.Random

class TaskViewModel() : ViewModel() {
    //two way binding
    private val mTitle = MutableLiveData<String>()
    private val rand = Random()
    //transformacja
    private val wordsArr = arrayOf("Hello World", "Welcome to the jungle", "Programming C#", "Visit me", "Go for a walk")

    val title: LiveData<String>
        get() {
            return mTitle
        }

    val words: LiveData<Int> = mTitle.map { w -> w.split(" ").size }

    //one way binding
    var active: Boolean = false
    private var mDeadline: LocalDate? = null
    var deadline: String?
        get() {
            return mDeadline?.toString()
        }
        set(value) = try {
            mDeadline = LocalDate.parse(value)
        } catch (e: DateTimeParseException) {

        }

    fun setTitle(title: String) {
        mTitle.value = title
    }

    fun randomTitle() {
        mTitle.value = wordsArr[rand.nextInt(wordsArr.size)]
    }

    override fun toString(): String {
        return "TaskViewModel(title=${title.value}, active=$active, deadline=$deadline)"
    }
}