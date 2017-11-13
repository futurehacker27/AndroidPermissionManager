package com.mrcompexpert.pm

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler

class PermissionActivity : Activity() {

    private lateinit var perms: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(Activity.RESULT_CANCELED)
        perms = intent.getStringArrayExtra(EXTRA_DATA)

        if (hasPermission(perms) || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            returnResult()
        } else {
            requestPermissions(perms, RC_PERM)
        }

    }

    private fun returnResult() {
        val intent = Intent()
        intent.putExtra(EXTRA_RESULT, hasPermission(perms))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Handler().post { returnResult() }
    }


}