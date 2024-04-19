package di

import org.koin.dsl.module
import screen.home.HomeScreenModel

val screenModelModule = module {
    factory {
        HomeScreenModel(get())
    }
}