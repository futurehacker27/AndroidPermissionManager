package com.mrcompexpert.pm

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

const val EXTRA_DATA = "extra_data"
const val EXTRA_RESULT = "extra_result"
const val RC_PERM = 111
const val RC_PERM_ACT = 112


fun Context.hasPermission(perms: Array<String>): Boolean {
    var result = true
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        for (per in perms)
            result = result && checkSelfPermission(per) == PackageManager.PERMISSION_GRANTED
    }
    return result
}