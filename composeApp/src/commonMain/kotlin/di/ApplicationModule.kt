package di

import org.cccsharonparish.core.data.realm.LocalDb
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.data.repo.PreferenceRepo
import org.koin.dsl.module
import org.cccsharonparish.feature.home.HomeScreenModel

val appModule = module {
    single(createdAtStart = true) {
        LocalDb().instance
    }
    single<IPreferenceRepo>{
        PreferenceRepo(get())
    }
    factory {
        HomeScreenModel()
    }
}