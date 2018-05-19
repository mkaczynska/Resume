package pro.kaczynska.resume.viewmodel

import android.arch.lifecycle.ViewModel
import pro.kaczynska.resume.dataprovider.DataProvider
import pro.kaczynska.resume.model.Candidate
import pro.kaczynska.resume.model.Profile


class CandidateProfileViewModel : ViewModel() {

    var candidate: Candidate = Candidate()
    var profile: Profile = Profile()

    fun init(dataProvider: DataProvider) {
        dataProvider.provide(candidate)
        dataProvider.provide(profile)
    }
}