package com.example.bcampos.shufflesongs

import android.app.Application
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.example.bcampos.shufflesongs.ui.songslist.SongsListFragment
import com.example.bcampos.shufflesongs.ui.songslist.SongsListViewModel
import org.hamcrest.core.IsNot.not
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(application = Application::class, manifest = Config.DEFAULT_MANIFEST_NAME)
@RunWith(AndroidJUnit4::class)
class SongsListFragmentTest {

    @Before
    fun loadFragment() {
        val scenario = launchFragmentInContainer<SongsListFragment>()
    }

    @Test
    fun shouldShowHelloWord() {

        val scenario = launchFragmentInContainer<SongsListFragment>()

        /*Espresso.onView(ViewMatchers.withText("batata"))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))*/

        onView(withId(R.id.message)).check(matches(withText("Bruno")))
    }

    @Test
    fun shouldShowErrorMessageWhenInErrorState() {
        onView(withId(R.id.error_message)).check(matches((isDisplayed())))
        onView(withId(R.id.message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun ShouldShowProgressBarWhenInLoadingState() {
        onView(withId(R.id.error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.message)).check(matches((isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches((isDisplayed())))
    }

    @Test
    fun shouldShowListWhenInSuccessState() {
        onView(withId(R.id.error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.message)).check(matches((isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }
}