package com.example.bcampos.shufflesongs

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.State
import com.example.bcampos.shufflesongs.mock.MockShufflerSongsApplication
import com.example.bcampos.shufflesongs.mock.MockedSongsRepository
import com.example.bcampos.shufflesongs.ui.songslist.SongsListActivity
import com.example.bcampos.shufflesongs.ui.songslist.SongsViewModelFactory
import com.squareup.picasso.Picasso
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config


@Config(application = MockShufflerSongsApplication::class)
@RunWith(AndroidJUnit4::class)
class SongsListFragmentTest {

    @get:Rule
    val rule: ActivityTestRule<SongsListActivity> = ActivityTestRule(SongsListActivity::class.java, true, false)

    @Mock
    var picasso: Picasso? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldShowErrorMessageWhenInErrorState() {
        val mockedSongsRepo = MockedSongsRepository()
        mockedSongsRepo.mockedResponse = State(
            State.Name.ERROR,
            emptyList()
        )

        ApplicationProvider.getApplicationContext<MockShufflerSongsApplication>().songsViewModelFactory = SongsViewModelFactory(mockedSongsRepo)

        rule.launchActivity(Intent())

        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.songs_recycler_view)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun ShouldShowProgressBarWhenInLoadingState() {
        val mockedSongsRepo = MockedSongsRepository()
        mockedSongsRepo.mockedResponse = State(
            State.Name.LOADING,
            emptyList()
        )

        ApplicationProvider.getApplicationContext<MockShufflerSongsApplication>().songsViewModelFactory = SongsViewModelFactory(mockedSongsRepo)

        rule.launchActivity(Intent())

        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.songs_recycler_view)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun shouldShowListWhenInSuccessState() {
        val mockedSongsRepo = MockedSongsRepository()
        val songsList = mutableListOf(
            Song("Ed Sheraan", "love", "track"),
            Song("red hot", "under the bridge", "artist"),
            Song("red hot", "the zephyr song", "track"),
            Song("red hot", "fly song", "artist"),
            Song("Ed Sheraan", "shape of you", "track"))

        mockedSongsRepo.mockedResponse = State(
            State.Name.LOADED,
            songsList
        )

        ApplicationProvider.getApplicationContext<MockShufflerSongsApplication>().songsViewModelFactory = SongsViewModelFactory(mockedSongsRepo)

        rule.launchActivity(Intent())

        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.songs_recycler_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}