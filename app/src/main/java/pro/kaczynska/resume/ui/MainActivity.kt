package pro.kaczynska.resume.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pro.kaczynska.resume.ResumeApplication
import pro.kaczynska.resume.databinding.ActivityMainBinding
import pro.kaczynska.resume.dataprovider.DataProvider
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
        viewModel.init(DataProvider(this))
        return viewModel
    }

    private fun redirect(intent: Intent?) = startActivity(intent)
}
