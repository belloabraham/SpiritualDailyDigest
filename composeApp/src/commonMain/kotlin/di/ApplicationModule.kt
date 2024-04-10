package di

import org.koin.dsl.module
import org.cccsharonparish.core.data.preference.DataStore
import org.cccsharonparish.core.data.preference.IDatastore
import org.cccsharonparish.core.data.preference.dataStorePreferences

val appModule = module {
    single<IDatastore> {
        DataStore(dataStorePreferences())
    }
}