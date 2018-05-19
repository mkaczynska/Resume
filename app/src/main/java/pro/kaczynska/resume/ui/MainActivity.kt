package pro.kaczynska.resume.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pro.kaczynska.resume.R
import pro.kaczynska.resume.ResumeApplication
import pro.kaczynska.resume.databinding.ActivityMainBinding
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Profile
import pro.kaczynska.resume.presenter.CandidateProfilePresenter
import pro.kaczynska.resume.viewmodel.CandidateProfileViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val presenter = createPresenter()
        val viewModel = createViewModel()
        bindData(viewModel, presenter)
    }

    private fun bindData(viewModel: CandidateProfileViewModel, presenter: CandidateProfilePresenter) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.presenter = presenter
        binding.setLifecycleOwner(this)
        setContentView(binding.root)
    }

    private fun createPresenter(): CandidateProfilePresenter {
        val injectDagger: (CandidateProfilePresenter) -> Unit = { presenter -> (application as ResumeApplication).component.inject(presenter) }
        return CandidateProfilePresenter(injectDagger, ::redirect)
    }

    private fun createViewModel(): CandidateProfileViewModel {
        val viewModel = ViewModelProviders.of(this).get(CandidateProfileViewModel::class.java)
        viewModel.init(::provideCandidateData, ::provideProfileData)
        return viewModel
    }

    private fun redirect(intent: Intent?) {
        if (intent != null) {
            startActivity(intent)
        }
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
