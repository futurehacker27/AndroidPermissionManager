package com.mrcompexpert.pm.demo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mrcompexpert.pm.OnPermissionResult
import com.mrcompexpert.pm.PermissionManager
import com.mrcompexpert.pm.hasPermission
import kotlinx.android.synthetic.main.per_mgr_demo_act.*

class PermissionManagerDemo : AppCompatActivity() {

    private val pm by lazy { PermissionManager(this) }
    private val rcPer = 222

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.per_mgr_demo_act)

        pm.listener = object : OnPermissionResult {
            override fun onPermissionResult(rq: Int, granted: Boolean) {
                permissionStatus.text = "Permission Status = ${hasPermission(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))}"
            }

        }

        val perm = hasPermission(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE))

        permissionStatus.text = "Permission Status = ${perm}"

        if (!perm) {
            pm.requestPermission(rcPer,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pm.onActivityResult(requestCode, resultCode, data)
    }
}