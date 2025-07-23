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
        return "Android"
    }

    fun getAppVersion(): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "unknown_version"
        } catch (e: PackageManager.NameNotFoundException) {
            "unknown_version"
        }
    }

    fun getOsVersion(): String {
        return "Android ${Build.VERSION.RELEASE ?: "unknown_os"}"
    }
}