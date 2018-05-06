package pro.kaczynska.resume.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import pro.kaczynska.resume.R
import pro.kaczynska.resume.ResumeApplication
import pro.kaczynska.resume.manager.IntentRetriever
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Email
import pro.kaczynska.resume.model.Profile
import pro.kaczynska.resume.ui.MainActivity
import javax.inject.Inject


@BindingMethods(
        BindingMethod(type = ImageView::class, attribute = "app:srcCompat", method = "setImageDrawable"))
class CandidateProfileViewModel(context: Context, callback: RedirectCallback) : ViewModel() {

    interface RedirectCallback {
        fun redirect(intent: Intent?)
    }

    private var callback: RedirectCallback

    @Inject
    lateinit var intentRetriever: IntentRetriever
    val candidate = Candidate()
    val profile = Profile()


    init {
        val resumeApplication = (context as MainActivity).application as ResumeApplication
        resumeApplication.component.inject(this)

        this.callback = callback
        candidate.fullName = context.getString(R.string.fullName)
        candidate.phoneNumber = context.getString(R.string.phoneNumber)
        candidate.emailAddress = context.getString(R.string.emailAddress)
        candidate.linkedInId = context.getString(R.string.linkedInId)
        candidate.skypeName = context.getString(R.string.skypeName)
        candidate.photo = context.getDrawable(R.drawable.madzia)

        profile.workHistoryText = context.getString(R.string.history_button_title)
        profile.callNumberText = context.getString(R.string.phone_button_title)
        profile.linkedInProfileText = context.getString(R.string.linkedin_button_title)
        profile.skypeCallText = context.getString(R.string.skype_call)
        profile.sendMessageText = context.getString(R.string.message_text)
        profile.sendEmailText = context.getString(R.string.email_button_title)
    }


    fun onWorkHistoryClicked(view: View) {
        Toast.makeText(view.context, "Open Work History", Toast.LENGTH_LONG).show()
        callback.redirect(intentRetriever.getWorkHistoryIntent(view.context))

    }

    fun onCallNumberClicked(view: View) {
        callback.redirect(intentRetriever.getPhoneCallIntent(candidate.phoneNumber))
    }

    fun onLinkedInProfileClicked(view: View) {
        callback.redirect(intentRetriever.getLinkedInProfileIntent(candidate.linkedInId,
                view.context.packageManager))
    }

    fun onSkypeCallClicked(view: View) {
        callback.redirect(intentRetriever.getSkypeIntent(candidate.skypeName, view.context.packageManager))
    }

    fun onSendMessageClicked(view: View) {
        callback.redirect(intentRetriever.getComposeMessageIntent(profile.sendMessageText))
    }

    fun onSendEmailClicked(view: View) {
        val context = view.context;
        val email = Email(candidate.emailAddress,
                context.getString(R.string.email_title),
                context.getString(R.string.email_header) + context.getString(R.string.email_footer))
        callback.redirect(intentRetriever.getComposeEmailIntent(email, context.packageManager))
    }

}