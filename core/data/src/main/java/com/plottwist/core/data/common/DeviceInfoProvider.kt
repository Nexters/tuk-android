package com.plottwist.core.data.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import jakarta.inject.Inject

class DeviceInfoProvider @Inject constructor(
    private val context: Context
) {

    @SuppressLint("HardwareIds")
    fun getDeviceSSAID(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getDeviceType(): String {
        return "android"
    }

    fun getAppVersion(): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "UNKNOWN"
        } catch (e: PackageManager.NameNotFoundException) {
            "UNKNOWN"
        }
    }

    fun getOsVersion(): String {
        return "android ${Build.VERSION.RELEASE ?: "unknown_os"}"
    }
}
