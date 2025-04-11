package di

import org.cccsharonparish.core.data.config.IRemoteConfig
import org.cccsharonparish.core.data.config.RemoteConfig
import org.cccsharonparish.core.data.firestore.Firestore
import org.koin.dsl.module
import screen.about.AboutScreenModel
import screen.home.HomeScreenModel
import screen.options.MoreOptionsScreenModel

val screenModelModule = module {
    factory {
        HomeScreenModel(get(), get())
    }
    factory {
        AboutScreenModel()
    }

    factory {
        MoreOptionsScreenModel(get())
    }

    factory<IRemoteConfig>{
        RemoteConfig()
    }

    factory{
        Firestore()
    }
}