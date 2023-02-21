package com.decode.hope.util

import android.content.res.ColorStateList
import androidx.compose.ui.graphics.Color
import androidx.core.widget.ImageViewCompat
import com.decode.hope.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


fun changeIconColor(floatingActionButton: FloatingActionButton, color: Int){
    ImageViewCompat.setImageTintList(
        floatingActionButton,
        ColorStateList.valueOf(color)
    );
}

fun changeIconColor(floatingActionButton: ExtendedFloatingActionButton, color: Int){
//    ImageViewCompat.setImageTintList(
//        floatingActionButton,
//        ColorStateList.valueOf(color)
//    );
}

fun Long.toTimeDateString(): String {
    val dateTime = java.util.Date(this)
    val format = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US)
    return format.format(dateTime)
}
