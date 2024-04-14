package org.cccsharonparish.core.data.repo

import org.cccsharonparish.core.data.entities.Preference
import io.realm.kotlin.MutableRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query

class PreferenceRepo(private val localDb: Realm) : IPreferenceRepo {

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
        localDb.write {
            val preference = getPreference(this) ?: Preference()
            copyToRealm(preference.apply {
                userExitedOnboarding = value
            }, UpdatePolicy.ALL)
        }
    }

}