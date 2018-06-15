package pro.kaczynska.resume.manager

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import pro.kaczynska.resume.model.Email
import pro.kaczynska.resume.ui.WorkHistoryActivity


class IntentRetriever : IIntentRetriever {

    override fun getWorkHistoryIntent(context: Context): Intent {
        return Intent(context, WorkHistoryActivity::class.java)
    }

    override fun getLinkedInProfileIntent(linkedInId: String, packageManager: PackageManager): Intent {
        var linkedIntent = Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@$linkedInId"))
        val list = packageManager.queryIntentActivities(linkedIntent, PackageManager.MATCH_DEFAULT_ONLY)
        if (list.isEmpty()) {
            linkedIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=$linkedInId"))
        }
        return linkedIntent
    }

    override fun getPhoneCallIntent(phoneNumber: String, packageManager: PackageManager): Intent? {
        val callUri = Uri.parse("tel:$phoneNumber")
        val callIntent = Intent(Intent.ACTION_DIAL, callUri)
        if (callIntent.resolveActivity(packageManager) != null) {
            return callIntent
        }
        return null
    }

    override fun getComposeSMSIntent(message: String, phoneNumber: String, packageManager: PackageManager): Intent? {
        val smsUri = Uri.parse("smsto:$phoneNumber")
        val sendIntent = Intent(Intent.ACTION_SENDTO)
        sendIntent.data = smsUri
        sendIntent.putExtra("sms_body", message)
        if (sendIntent.resolveActivity(packageManager) != null) {
            return sendIntent
        }
        return null
    }

    override fun getComposeEmailIntent(email: Email, packageManager: PackageManager): Intent? {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email.address))
        intent.putExtra(Intent.EXTRA_SUBJECT, email.subject)
        intent.putExtra(Intent.EXTRA_TEXT, email.body)
        if (intent.resolveActivity(packageManager) != null) {
            return intent
        }
        return null
    }

    override fun getWhatsAppMessageIntent(phoneNumber: String, packageManager: PackageManager): Intent? {
        val sendIntent = Intent(Intent.ACTION_SENDTO)
        sendIntent.data = Uri.parse("sms:$phoneNumber")
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(packageManager) != null) {
            return sendIntent
        }
        return null
    }
}