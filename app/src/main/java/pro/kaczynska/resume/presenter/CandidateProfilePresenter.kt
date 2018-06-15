package pro.kaczynska.resume.presenter

import android.content.Intent
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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
    lateinit var intentRetriever: IntentRetriever

    fun onWorkHistoryClicked(view: View) {
        Toast.makeText(view.context, "Open Work History", Toast.LENGTH_LONG).show()
        redirectOperation(intentRetriever.getWorkHistoryIntent(view.context))

    }

    fun onCallNumberClicked(view: View, candidate: Candidate) {
        redirectOperation(intentRetriever.getPhoneCallIntent(candidate.phone, view.context.packageManager))
    }

    fun onLinkedInProfileClicked(view: View, candidate: Candidate) {
        redirectOperation(intentRetriever.getLinkedInProfileIntent(candidate.linkedInId,
                view.context.packageManager))
    }

    fun onWhatsAppMessageClicked(view: View, candidate: Candidate) {
        redirectOperation(intentRetriever.getWhatsAppMessageIntent(candidate.phone, view.context.packageManager))
    }

    fun onSendSMSClicked(view: View, profile: Profile, candidate: Candidate) {
        redirectOperation(intentRetriever.getComposeSMSIntent(profile.messageBody, candidate.phone, view.context.packageManager))
    }

    fun onSendEmailClicked(view: View, candidate: Candidate) {
        val context = view.context
        val email = Email(candidate.email,
                context.getString(R.string.emailTitle),
                context.getString(R.string.emailHeader) + context.getString(R.string.emailFooter))
        redirectOperation(intentRetriever.getComposeEmailIntent(email, context.packageManager))
    }
}

@BindingAdapter("srcCompat")
fun loadImage(view: ImageView, photo: Drawable) {
    view.setImageDrawable(photo)
}