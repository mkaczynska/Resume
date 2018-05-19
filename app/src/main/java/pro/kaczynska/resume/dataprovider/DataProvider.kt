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
    }

    override fun provide(profile: Profile) {
        profile.workHistoryText = context.getString(R.string.history_button_title)
        profile.callNumberText = context.getString(R.string.phone_button_title)
        profile.linkedInProfileText = context.getString(R.string.linkedin_button_title)
        profile.skypeCallText = context.getString(R.string.skype_call)
        profile.sendMessageText = context.getString(R.string.message_text)
        profile.sendEmailText = context.getString(R.string.email_button_title)
    }

}

//
//class ResumeDataProvider(private val context: Context) : DataProvider {
//
//    override fun provideCandidateData(): Candidate {
//        return Candidate(
//                fullName = context.getString(R.string.fullName),
//                phone = context.getString(R.string.phoneNumber),
//                email = context.getString(R.string.emailAddress),
//                linkedInId = context.getString(R.string.linkedInId),
//                skype = context.getString(R.string.skypeName),
//                photo = context.getDrawable(R.drawable.madzia))
//    }
//
//    override fun provideProfileData(): Profile {
//        return Profile(
//                workHistoryText = context.getString(R.string.history_button_title),
//                callNumberText = context.getString(R.string.phone_button_title),
//                linkedInProfileText = context.getString(R.string.linkedin_button_title),
//                skypeCallText = context.getString(R.string.skype_call),
//                sendMessageText = context.getString(R.string.message_text),
//                sendEmailText = context.getString(R.string.email_button_title))
//    }
//
//}

