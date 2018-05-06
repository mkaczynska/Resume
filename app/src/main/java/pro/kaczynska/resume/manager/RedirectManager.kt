package pro.kaczynska.resume.manager

import android.content.ComponentName
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

    override fun getPhoneCallIntent(phoneNumber: String): Intent {
        val callUri = Uri.parse(phoneNumber)
        return Intent(Intent.ACTION_DIAL, callUri)
    }

    override fun getComposeMessageIntent(message: String): Intent {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, message)
        sendIntent.type = "text/plain"
        return sendIntent
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

    override fun getSkypeIntent(skypeName: String, packageManager: PackageManager): Intent {
        // Make sure the Skype for Android client is installed
        if (!isSkypeClientInstalled(packageManager)) {
            return getSkypeMarketIntent()
        }

        val mySkypeUri = "skype:$skypeName?chat"
        // Create the Intent from our Skype URI.
        val skypeUri = Uri.parse(mySkypeUri)
        val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

        // Restrict the Intent to being handled by the Skype for Android client only.
        myIntent.component = ComponentName("com.skype.raider", "com.skype.raider.Main")
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        return myIntent
    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    private fun isSkypeClientInstalled(packageManager: PackageManager): Boolean {
        try {
            packageManager.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }

    /**
     * Install the Skype client through the market: URI scheme.
     */
    private fun getSkypeMarketIntent(): Intent {
        val marketUri = Uri.parse("market://details?id=com.skype.raider")
        val intent = Intent(Intent.ACTION_VIEW, marketUri)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        return intent
    }

    //// A sample member ID value to open
//        val targetID = "abcd1234";
//
//        DeepLinkHelper deepLinkHelper = DeepLinkHelper.getInstance();
//
//// Open the target LinkedIn member's profile
//        deepLinkHelper.openOtherProfile(thisActivity, targetID, new DeeplinkListener () {
//            @Override
//            public void onDeepLinkSuccess() {
//                // Successfully sent user to LinkedIn app
//            }
//
//            @Override
//            public void onDeepLinkError(LiDeepLinkError error) {
//                // Error sending user to LinkedIn app
//            }
//        });
//    }

}