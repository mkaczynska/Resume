package pro.kaczynska.resume.manager

import android.content.Intent
import pro.kaczynska.resume.model.Email

interface IntentRetriever {
    fun linkedInApp(linkedInId: String): Intent
    fun linkedInSite(linkedInId: String): Intent
    fun phoneCall(phoneNumber: String): Intent
    fun smsApp(message: String, phoneNumber: String): Intent
    fun whatsApp(phoneNumber: String): Intent
    fun emailApp(email: Email): Intent
    fun whatsAppStore(): Intent
    fun whatsAppSite(): Intent
}