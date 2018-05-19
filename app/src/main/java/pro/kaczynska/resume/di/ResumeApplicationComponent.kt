package pro.kaczynska.resume.di

import dagger.Component
import pro.kaczynska.resume.presenter.CandidateProfilePresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(ResumeApplicationModule::class)])
interface ResumeApplicationComponent {
    fun inject(presenter: CandidateProfilePresenter)
}