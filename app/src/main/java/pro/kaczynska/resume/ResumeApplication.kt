package pro.kaczynska.resume

import android.app.Application
import pro.kaczynska.resume.di.DaggerResumeApplicationComponent
import pro.kaczynska.resume.di.ResumeApplicationComponent
import pro.kaczynska.resume.di.ResumeApplicationModule

class ResumeApplication : Application() {

   lateinit var component: ResumeApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerResumeApplicationComponent.builder()
                .resumeApplicationModule(ResumeApplicationModule(this))
                .build()
    }
}