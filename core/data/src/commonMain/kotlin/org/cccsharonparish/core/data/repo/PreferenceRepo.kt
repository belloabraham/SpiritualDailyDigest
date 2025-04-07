package org.cccsharonparish.core.data.repo

import org.cccsharonparish.core.data.entities.Preference
import io.realm.kotlin.MutableRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class PreferenceRepo(private val localDb: Realm,  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPreferenceRepo {

    private fun getPreference(mutableRealm: MutableRealm):  Preference? {
        return try {
           mutableRealm.findLatest(localDb.query<Preference>().find().first())
        } catch (e: Exception) {
            null
        }
    }

    override fun getUserExitedOnboardingScreen(): Boolean {
        return try {
             localDb.query<Preference>().find().first().userExitedOnboarding
        } catch (e: Exception) {
            Preference().userExitedOnboarding
        }
    }


    override suspend fun setUserExitedOnboardingScreen(value: Boolean) {
        withContext(dispatcher) {
            localDb.write {
                val preference = getPreference(this) ?: Preference()
                copyToRealm(preference.apply {
                    userExitedOnboarding = value
                }, UpdatePolicy.ALL)
            }
        }
    }

    override fun getFontSize(): Float {
        return try {
            localDb.query<Preference>().find().first().fontSize
        } catch (e: Exception) {
            Preference().fontSize
        }
    }

    override suspend fun setFontSize(value: Float) {
        withContext(dispatcher) {
            localDb.write {
                val preference = getPreference(this) ?: Preference()
                copyToRealm(preference.apply {
                    fontSize = value
                }, UpdatePolicy.ALL)
            }
        }
    }

    override fun getLanguageIndex(): Int {
        return try {
            localDb.query<Preference>().find().first().languageIndex
        } catch (e: Exception) {
            Preference().languageIndex
        }
    }

    override suspend fun setLanguageIndex(value: Int) {
        withContext(dispatcher) {
            localDb.write {
                val preference = getPreference(this) ?: Preference()
                copyToRealm(preference.apply {
                    languageIndex = value
                }, UpdatePolicy.ALL)
            }
        }
    }

}