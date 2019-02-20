package com.example.bcampos.shufflesongs

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.example.bcampos.shufflesongs.ui.songslist.SongsListFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(application = Application::class, manifest = Config.DEFAULT_MANIFEST_NAME)
@RunWith(AndroidJUnit4::class)
class SongsListFragmentTest {

    @Test
    fun shouldShowHelloWord() {

        val scenario = launchFragmentInContainer<SongsListFragment>()
        Espresso.onView(ViewMatchers.withText("batata"))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//        onView(withId(R.id.message)).check(matches(withText("SongsListFragment")))
    }
}