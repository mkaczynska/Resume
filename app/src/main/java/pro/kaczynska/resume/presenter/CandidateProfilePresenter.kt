package pro.kaczynska.resume.presenter

import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import pro.kaczynska.resume.R
import pro.kaczynska.resume.manager.IntentRetriever
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Email
import pro.kaczynska.resume.model.Profile
import javax.inject.Inject


class CandidateProfilePresenter(injectDagger: (CandidateProfilePresenter) -> Unit, redirect: (Intent?) -> Unit) {

    private val redirectOperation: (Intent?) -> Unit = redirect

    init {
        injectDagger(this)
    }

    @Inject
    lateinit var intent: IntentRetriever

    private fun valid(intent: Intent, packageManager: PackageManager): Boolean {
        return intent.resolveActivity(packageManager) != null
    }

    fun onCallNumberClicked(candidate: Candidate) {
        redirectOperation(intent.phoneCall(candidate.phone))
    }

    fun onLinkedInProfileClicked(view: View, candidate: Candidate) {
        val linkedIntent =
                if (valid(intent.linkedInApp(candidate.linkedInId), view.context.packageManager))
                    intent.linkedInApp(candidate.linkedInId)
                else intent.linkedInSite(candidate.linkedInId)
        redirectOperation(linkedIntent)
    }

    fun onWhatsAppMessageClicked(view: View, candidate: Candidate) {
        val pm = view.context.packageManager

        val whatsAppIntent = when {
            valid(intent.whatsApp(candidate.phone), pm) -> intent.whatsApp(candidate.phone)
            valid(intent.whatsAppStore(), pm) -> intent.whatsAppStore()
            else -> intent.whatsAppSite()
        }
        redirectOperation(whatsAppIntent)
    }

    fun onSendSMSClicked(profile: Profile, candidate: Candidate) {
        redirectOperation(intent.smsApp(profile.messageBody, candidate.phone))
    }

    fun onSendEmailClicked(view: View, candidate: Candidate) {
        val context = view.context
        val email = Email(candidate.email,
                context.getString(R.string.emailTitle),
                context.getString(R.string.emailHeader) + context.getString(R.string.emailFooter))
        redirectOperation(intent.emailApp(email))
    }
}

@BindingAdapter("srcCompat")
fun loadImage(view: ImageView, photo: Drawable) {
    view.setImageDrawable(photo)
}