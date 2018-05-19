package pro.kaczynska.resume.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pro.kaczynska.resume.R
import pro.kaczynska.resume.databinding.ActivityMainBinding
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Profile
import pro.kaczynska.resume.presenter.CandidateProfilePresenter
import pro.kaczynska.resume.viewmodel.CandidateProfileViewModel


class MainActivity : AppCompatActivity(), CandidateProfilePresenter.RedirectCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val presenter = CandidateProfilePresenter(this, this)
        setContentView(bindData(createViewModel(), presenter).root)
    }

    override fun redirect(intent: Intent?) {
        if (intent != null) {
            startActivity(intent)
        }
    }

    private fun bindData(viewModel: CandidateProfileViewModel, presenter: CandidateProfilePresenter): ActivityMainBinding {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.presenter = presenter
        binding.setLifecycleOwner(this)
        return binding
    }

    private fun createViewModel(): CandidateProfileViewModel {
        val viewModel = ViewModelProviders.of(this).get(CandidateProfileViewModel::class.java)
        viewModel.createCandidate(::provideCandidateData)
        viewModel.createProfile(::provideProfileData)
        return viewModel
    }

    private fun provideCandidateData(): Candidate {
        return Candidate(
                fullName = getString(R.string.fullName),
                phone = getString(R.string.phoneNumber),
                email = getString(R.string.emailAddress),
                linkedInId = getString(R.string.linkedInId),
                skype = getString(R.string.skypeName),
                photo = getDrawable(R.drawable.madzia))
    }

    private fun provideProfileData(): Profile {
        return Profile(
                workHistoryText = getString(R.string.history_button_title),
                callNumberText = getString(R.string.phone_button_title),
                linkedInProfileText = getString(R.string.linkedin_button_title),
                skypeCallText = getString(R.string.skype_call),
                sendMessageText = getString(R.string.message_text),
                sendEmailText = getString(R.string.email_button_title))
    }
}
