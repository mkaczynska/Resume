package pro.kaczynska.resume.manager

import android.content.Intent
import android.net.Uri
import pro.kaczynska.resume.model.Email


class IntentManager : IntentRetriever {

    private val packageName = "com.whatsapp"

    override fun linkedInApp(linkedInId: String): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@$linkedInId"))
    }

    override fun linkedInSite(linkedInId: String): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=$linkedInId"))
    }


    override fun phoneCall(phoneNumber: String): Intent {
        val callUri = Uri.parse("tel:$phoneNumber")
        return Intent(Intent.ACTION_DIAL, callUri)
    }

    override fun smsApp(message: String, phoneNumber: String): Intent {
        val smsUri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = smsUri
        intent.putExtra("sms_body", message)
        return intent
    }

    override fun emailApp(email: Email): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email.address))
        intent.putExtra(Intent.EXTRA_SUBJECT, email.subject)
        intent.putExtra(Intent.EXTRA_TEXT, email.body)
        return intent
    }

    override fun whatsApp(phoneNumber: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("sms:$phoneNumber")
        intent.setPackage(packageName)
        return intent
    }

    override fun whatsAppStore(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
    }

    override fun whatsAppSite(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
    }
}
