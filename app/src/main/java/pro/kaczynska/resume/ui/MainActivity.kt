package pro.kaczynska.resume.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pro.kaczynska.resume.BR
import pro.kaczynska.resume.R
import pro.kaczynska.resume.databinding.ActivityMainBinding
import pro.kaczynska.resume.viewmodel.CandidateProfileViewModel


class MainActivity : AppCompatActivity(), CandidateProfileViewModel.RedirectCallback {

    private lateinit var candidateViewModel: CandidateProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        candidateViewModel = CandidateProfileViewModel(this, this)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.setVariable(BR.viewModel, candidateViewModel)
    }

    override fun redirect(intent: Intent?) {
        if (intent != null) {
            startActivity(intent)
        }
    }
}
