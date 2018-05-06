package pro.kaczynska.resume.di

import dagger.Component
import pro.kaczynska.resume.viewmodel.CandidateProfileViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(ResumeApplicationModule::class)])
interface ResumeApplicationComponent {
    fun inject(viewModel: CandidateProfileViewModel)
}