package pl.wsei.pam.lectures.lecture4.binding

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import pl.wsei.pam.lectures.databinding.ActivityL4BindingBinding
import java.time.LocalDate

class L4BindingActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityL4BindingBinding
    val mViewModel: TaskViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL4BindingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.lifecycleOwner = this
        mViewModel.also {
            it.active = true
            it.deadline = "2024-03-29"
            it.setTitle("Init title")
        }
        mBinding.taskViewModel = mViewModel
        //przykład własnego obserwatora tytułu
        val observer = Observer<String>{
            value -> Toast.makeText(this, "Date changed $value", Toast.LENGTH_SHORT).show()
        }
        //Rejestracja obserwatora
        mViewModel.title.observe(this, observer)
    }

    fun onClickSetDateBtn(v: View) {
        val picker: DatePickerDialog = DatePickerDialog(this).also {
            it.setOnDateSetListener() { picker, year, moth, day ->
                run {
                    //to przypisanie do właściwości modelu nie powoduje aktualizacji widoku
                    //właściwość deadline ma wiązanie jednokierunkowe
                    mBinding.taskViewModel?.deadline = LocalDate.of(year, moth + 1, day).toString()
                    //mBinding.taskViewModel = mBinding.taskViewModel
                }
            }
        }
        picker.show()
    }

    fun onClickShowModelBtn(v: View){
        //zmiana tytułu w modelu powoduje aktualizację widoku
        mBinding.taskViewModel?.randomTitle()
        //przykład tworzenia okna dialogowego
//        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//        builder.setMessage("${mBinding.taskViewModel}")
//            .setTitle("View Model!")
//            .setPositiveButton("Ok") { _, _ -> }
//            .create()
//            .show()
    }


}