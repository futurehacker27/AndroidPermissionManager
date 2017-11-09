package com.mrcompexpert.pm

interface OnPermissionResult {
    fun onPermissionResult(rq: Int, granted: Boolean)
}