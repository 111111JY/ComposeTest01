package com.example.composetest01.ui.theme

import android.widget.Toast

fun String.showToast(duration:Int= Toast.LENGTH_SHORT){
    Toast.makeText(ApplicationActivity.context,this,duration).show()
}

fun Int.showToast(duration:Int= Toast.LENGTH_SHORT){
    Toast.makeText(ApplicationActivity.context,this,duration).show()
}