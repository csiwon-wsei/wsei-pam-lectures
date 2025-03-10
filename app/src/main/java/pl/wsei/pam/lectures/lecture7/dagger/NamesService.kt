package pl.wsei.pam.lectures.lecture7.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NamesModule::class])
interface NamesService {
    fun names(): RandomNameService
}