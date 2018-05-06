package pro.kaczynska.resume.di

import android.app.Application
import dagger.Module
import dagger.Provides
import pro.kaczynska.resume.manager.IntentRetriever

@Module
class ResumeApplicationModule(private val application : Application) {

    @Provides
    fun provideIntentRetriever(): IntentRetriever {
        return IntentRetriever()
    }
}