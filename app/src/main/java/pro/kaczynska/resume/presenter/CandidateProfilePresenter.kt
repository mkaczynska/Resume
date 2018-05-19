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

    fun onCallNumberClicked(candidate: Candidate) {
        redirectOperation(intentRetriever.getPhoneCallIntent(candidate.phone))
    }

    fun onLinkedInProfileClicked(view: View, candidate: Candidate) {
        redirectOperation(intentRetriever.getLinkedInProfileIntent(candidate.linkedInId,
                view.context.packageManager))
    }

    fun onSkypeCallClicked(view: View, candidate: Candidate) {
        redirectOperation(intentRetriever.getSkypeIntent(candidate.skype, view.context.packageManager))
    }

    fun onSendMessageClicked(profile: Profile) {
        redirectOperation(intentRetriever.getComposeMessageIntent(profile.sendMessageText))
    }

    fun onSendEmailClicked(view: View, candidate: Candidate) {
        val context = view.context;
        val email = Email(candidate.email,
                context.getString(R.string.email_title),
                context.getString(R.string.email_header) + context.getString(R.string.email_footer))
        redirectOperation(intentRetriever.getComposeEmailIntent(email, context.packageManager))
    }
}

@BindingAdapter("srcCompat")
fun loadImage(view: ImageView, photo: Drawable) {
    view.setImageDrawable(photo)
}