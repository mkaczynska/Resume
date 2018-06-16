package pro.kaczynska.resume.di

import android.app.Application
import dagger.Module
import dagger.Provides
import pro.kaczynska.resume.manager.IntentRetriever
import pro.kaczynska.resume.manager.IntentManager

@Module
class ResumeApplicationModule(private val application: Application) {

    @Provides
    fun provideIntentRetriever(): IntentRetriever {
        return IntentManager()
    }
}