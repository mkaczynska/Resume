package pro.kaczynska.resume.dataprovider

import android.content.Context
import pro.kaczynska.resume.R
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Profile

interface Provider {

    fun provide(candidate: Candidate)

    fun provide(profile: Profile)
}

class DataProvider(private val context: Context) : Provider {

    override fun provide(candidate: Candidate) {
        candidate.fullName = context.getString(R.string.fullName)
        candidate.phone = context.getString(R.string.phoneNumber)
        candidate.email = context.getString(R.string.emailAddress)
        candidate.linkedInId = context.getString(R.string.linkedInId)
        candidate.skype = context.getString(R.string.skypeName)
        candidate.photo = context.getDrawable(R.drawable.madzia)
        candidate.photoDescription = context.getString(R.string.photoDescription)
    }

    override fun provide(profile: Profile) {
        profile.workHistoryText = context.getString(R.string.workHistoryText)
        profile.callNumberText = context.getString(R.string.phoneCallText)
        profile.linkedInProfileText = context.getString(R.string.linkedInText)
        profile.skypeCallText = context.getString(R.string.skypeCallText)
        profile.sendMessageText = context.getString(R.string.sendMessageText)
        profile.sendEmailText = context.getString(R.string.sendEmailText)
        profile.messageBody = context.getString(R.string.messageBody)
    }

}

