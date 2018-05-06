package pro.kaczynska.resume.manager

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import pro.kaczynska.resume.model.Email

interface IIntentRetriever {

    fun getWorkHistoryIntent(context: Context) : Intent
    fun getLinkedInProfileIntent(linkedInId: String, packageManager: PackageManager): Intent
    fun getPhoneCallIntent(phoneNumber: String): Intent
    fun getComposeMessageIntent(message: String): Intent
    fun getComposeEmailIntent(email: Email, packageManager: PackageManager) : Intent?
    fun getSkypeIntent(skypeName: String, packageManager: PackageManager) : Intent

}