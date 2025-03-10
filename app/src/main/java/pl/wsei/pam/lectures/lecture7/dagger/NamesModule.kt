package pl.wsei.pam.lectures.lecture7.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NamesModule {
    @Provides
    @Singleton
    fun provideNamesService() = RandomNameService()
}