package screen

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class FeatureScreen:ScreenProvider {
    data object Onboarding : FeatureScreen()
}