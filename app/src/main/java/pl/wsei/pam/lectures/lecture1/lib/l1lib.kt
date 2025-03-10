package pl.wsei.pam.lectures.lecture1.lib

import android.content.Context
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import pl.wsei.pam.lectures.R

fun createButton(context: Context, label: String, tooltip: String): Button {
    return Button(ContextThemeWrapper(context, R.style.AppButton), null, 0).apply {
        text = label
        height = 20
        tooltipText = tooltip
    }
}

fun createImageButton(context: Context): ImageButton {
    return ImageButton(context).also {
        it.setImageResource(R.drawable.wsei_logo_svg)
    }
}

fun createText(context: Context, text: String): TextView {
    return TextView(context).also {
        it.text = text
    }
}

fun createLayout(weight: Float): LayoutParams {
    return LayoutParams(
        LayoutParams.MATCH_PARENT,
        LayoutParams.WRAP_CONTENT
    ).also {
        it.setMargins(16, 16, 16, 16)
        it.weight = weight
    }
}

fun createChip(context: Context, text: String): Chip {
    val chip = Chip(context).also {
        it.text = text
        it.isChecked = true
        it.isCheckable = true
        it.isCloseIconVisible = true
        it.isChipIconVisible = true
        it.setChipIconResource(R.drawable.sharp_attach_email_24)
    }
    return chip
}

fun createChipGroup(context: Context): ChipGroup {
    return ChipGroup(context)
}