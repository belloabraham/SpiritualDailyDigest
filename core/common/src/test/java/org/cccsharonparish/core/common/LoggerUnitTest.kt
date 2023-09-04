package org.cccsharonparish.core.common

import com.google.common.truth.Truth.assertThat
import org.cccsharonparish.core.common.helpers.utils.Logger
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class LoggerUnitTest {

    @Before
    fun setUp() {
        // Initialize Timber for testing
        Timber.plant(TestTree())
    }

    @Test
    fun `error with Throwable logs the error`() {
        val throwable = Throwable("Test error message")

        Logger.error(throwable)

        // Use Google Truth to verify that Timber logged the error
        assertThat(TestTree.log).contains("Test error message")
    }

    @Test
    fun `error with Throwable, message, and args logs the error with message and args`() {
        val throwable = Throwable("Test error message")
        val message = "Error occurred: %s"
        val args = "testArg"

        Logger.error(throwable, message, args)

        // Use Google Truth to verify that Timber logged the error with the provided message and args
        assertThat(TestTree.log).contains("Error occurred: testArg")
    }

    // Custom Timber Tree for testing
    private class TestTree : Timber.Tree() {
        companion object {
            var log: String? = null
        }

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            // Capture the log message for testing
            log = message
        }
    }
}
