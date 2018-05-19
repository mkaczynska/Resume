package pro.kaczynska.resume.viewmodel

import android.arch.lifecycle.ViewModel
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Profile


class CandidateProfileViewModel : ViewModel() {

    lateinit var candidate: Candidate
    lateinit var profile: Profile

    fun init(provideCandidateData: () -> Candidate, provideProfileData: () -> Profile) {
        candidate = provideCandidateData()
        profile = provideProfileData()
    }
}