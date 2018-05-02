package pro.kaczynska.resume.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import pro.kaczynska.resume.R
import pro.kaczynska.resume.model.Candidate
import android.graphics.drawable.Drawable
import android.databinding.BindingAdapter
import android.widget.ImageView
import pro.kaczynska.resume.model.Profile


class CandidateProfileViewModel(context: Context) : ViewModel() {

    private val candidate = Candidate()
    private val profile = Profile()


    init {
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
        profile.sendEmailText = context.getString(R.string.email_title)
    }

    @BindingAdapter("srcCompat")
    fun bindSrcCompat(imageView: ImageView, drawable: Drawable) {
        imageView.setImageDrawable(drawable)
    }


}