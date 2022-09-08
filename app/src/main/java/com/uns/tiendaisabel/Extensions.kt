package com.uns.tiendaisabel

import android.util.Patterns

fun String.isAnEmailValid(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(this).matches()
}