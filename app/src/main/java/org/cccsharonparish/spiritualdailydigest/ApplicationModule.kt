package org.cccsharonparish.spiritualdailydigest

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.cccsharonparish.core.common.helpers.preference.ISettings
import org.cccsharonparish.core.common.helpers.preference.Settings
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideSetting(@ApplicationContext context: Context):ISettings{
        return Settings(context)
    }
}