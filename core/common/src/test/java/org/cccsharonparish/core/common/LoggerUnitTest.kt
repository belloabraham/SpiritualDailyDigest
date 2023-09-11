package org.cccsharonparish.core.common

import com.google.common.truth.Truth.assertThat
import org.cccsharonparish.core.common.helpers.logging.Logger
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class LoggerUnitTest {

    @Before
    fun setUp() {
        Timber.plant(timberTree)
    }

    @After
    fun cleanUp() {
        Timber.uproot(timberTree)
    }

    @Test
    fun `error with Throwable, message, and args logs the error with message and args`() {
        val throwable = Throwable("Test error message")
        val message = "Error occurred: %s"
        val args = "testArg"

        Logger.logError(throwable, message, args)
        assertThat(log).contains("Error occurred: testArg")
    }


    @Test
    fun `logWarning with message and args should call Timber's warning method with message and args`() {
        val message = "Warning message: %s"
        val args = "Argument"

        Logger.logWarning(message, args)
        assertThat(log).contains("Warning message: Argument")
    }

    @Test
    fun `debug with Throwable, message, and args should call Timber's debug method with message and args`() {
        val throwable = Throwable("Test error message")

        val message = "Debug message: %s"
        val args = "Argument"

        Logger.debug(throwable, message, args)
        assertThat(log).contains("Debug message: Argument")
    }

    @Test
    fun `debug with message and args should call Timber's debug method with message and args`() {
        val message = "Debug message: %s"
        val args = "Argument"

        Logger.debug(message, args)
        assertThat(log).contains("Debug message: Argument")
    }


    companion object {
        var log: String? = null
        val timberTree = object : Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                // Capture the log message for testing
                log = message
            }
        }
    }
}
