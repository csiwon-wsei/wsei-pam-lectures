package pl.wsei.pam.lectures.lecture1.lib

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import pl.wsei.pam.lectures.R

class CustomSpinnerAdapter(context: Context, private val items: List<SpinnerItem>) :
    ArrayAdapter<SpinnerItem>(context, R.layout.spinner_layout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_layout, parent, false)

        val item = getItem(position)

        val spinnerName = view.findViewById<TextView>(R.id.spinnerName)
        val spinnerImage = view.findViewById<ImageView>(R.id.spinnerImage)

        spinnerName.text = item?.name
        spinnerImage.setImageDrawable(item?.image)

        return view
    }
}


data class SpinnerItem(val name: String, val image: Drawable)