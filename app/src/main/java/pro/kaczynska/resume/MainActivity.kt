package pro.kaczynska.resume

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.pm.PackageManager
import android.content.ActivityNotFoundException
import android.content.Intent.ACTION_VIEW
import android.util.Log
import android.content.ComponentName


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workHistoryButton = findViewById<Button>(R.id.workHistory)
        workHistoryButton.setOnClickListener {
            val workHistoryIntent = Intent(applicationContext, WorkHistoryActivity::class.java)
            startActivity(workHistoryIntent)
        }

        val callButton = findViewById<Button>(R.id.call)
        callButton.setOnClickListener {
            val callUri = Uri.parse(getString(R.string.resume_phone_number))
            val callIntent = Intent(Intent.ACTION_DIAL, callUri)
            startActivity(callIntent)
        }

        val emailButton = findViewById<Button>(R.id.sendEmail)
        emailButton.setOnClickListener {
            composeEmail(arrayOf(getString(R.string.email_address)),
                    getString(R.string.email_title),
                    getString(R.string.email_header) + getString(R.string.email_footer))
        }

        val linkedInButton = findViewById<Button>(R.id.linkedIn)
        linkedInButton.setOnClickListener {
            val linkedInId = getString(R.string.linkedInId)
            openLinkedInPage(linkedInId)
        }

        val skypeButton = findViewById<Button>(R.id.skype)
        skypeButton.setOnClickListener {
            openSkype(getString(R.string.skype_name))
        }

        val whatsappButton = findViewById<Button>(R.id.whatsapp)
        whatsappButton.setOnClickListener {
            openWhatsapp()
        }
    }

    private fun openWhatsapp() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.message_body))
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    fun composeEmail(addresses: Array<String>, subject: String, body: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openLinkedInPage(linkedId: String) {
        var linkedIntent = Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@$linkedId"))
        val packageManager = getPackageManager()
        val list = packageManager.queryIntentActivities(linkedIntent, PackageManager.MATCH_DEFAULT_ONLY)
        if (list.isEmpty()) {
            linkedIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=$linkedId"))
        }
        startActivity(linkedIntent)
    }

    fun skype(userName: String) {
        try {
            val intentData = Uri.parse("skype:$userName?call&video=true")
            val skypeVideo = Intent(ACTION_VIEW, intentData)
            startActivity(skypeVideo)


        } catch (e: ActivityNotFoundException) {
            Log.e("SKYPE CALL", "Skype failed", e)
        }

    }

    private fun openSkype(userName: String) {

        // Make sure the Skype for Android client is installed
        if (!isSkypeClientInstalled()) {
            goToMarket()
            return
        }

        val mySkypeUri = "skype:$userName?chat"
        // Create the Intent from our Skype URI.
        val skypeUri = Uri.parse(mySkypeUri)
        val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

        // Restrict the Intent to being handled by the Skype for Android client only.
        myIntent.component = ComponentName("com.skype.raider", "com.skype.raider.Main")
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        try {
            startActivity(myIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    fun isSkypeClientInstalled(): Boolean {
        val myPackageMgr = getPackageManager()
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }

        return true
    }

    /**
     * Install the Skype client through the market: URI scheme.
     */
    fun goToMarket() {
        val marketUri = Uri.parse("market://details?id=com.skype.raider")
        val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(myIntent)
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
