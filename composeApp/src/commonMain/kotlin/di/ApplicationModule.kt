package di

import org.cccsharonparish.core.common.helpers.Connection
import org.cccsharonparish.core.common.helpers.Device
import org.cccsharonparish.core.data.realm.LocalDb
import org.cccsharonparish.core.data.repo.ContentRepo
import org.cccsharonparish.core.data.repo.IContentRepo
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.data.repo.PreferenceRepo
import org.koin.dsl.module

val appModule = module {
    single(createdAtStart = true) {
        LocalDb().instance
    }
    single<IPreferenceRepo>{
        PreferenceRepo(get())
    }
    single<IContentRepo>{
        ContentRepo(get(), get())
    }
    single {
        Connection()
    }
    single {
        Device(get())
    }
}