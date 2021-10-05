package com.zaydhisyam.memories

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zaydhisyam.memories.android_test.EspressoIdlingResource
import com.zaydhisyam.memories.ui.PostListActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test

class InstrumentTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(PostListActivity::class.java)
        IdlingRegistry.getInstance().register(
            EspressoIdlingResource.idlingResource
        )
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(
            EspressoIdlingResource.idlingResource
        )
    }

    private fun checkRecyclerView(rvId: Int) {
        val postListRv = onView(withId(rvId))

        postListRv.check(matches(isDisplayed()))

        postListRv.perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                (0..5).random()
            )
        )

        postListRv.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                (0..5).random(),
                ViewActions.click()
            )
        )
    }

    private fun checkTextView(textViewId: Int) {
        val textView = onView(withId(textViewId))

        textView.check(matches(isDisplayed()))

        textView.check(matches(not(withText(""))))
    }

    @Test
    fun loadPostListActivity() {
        checkRecyclerView(R.id.rv_post_list)
    }

    @Test
    fun loadDetailPostActivity() {
        onView(withId(R.id.rv_post_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..20).random(),
                    ViewActions.click()
                )
            )

        checkTextView(R.id.tv_post_user_name)

        checkTextView(R.id.tv_post_title)

        checkTextView(R.id.tv_post_body)

        onView(withId(R.id.rv_comment_list)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailUserActivity() {
        onView(withId(R.id.rv_post_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..20).random(),
                    ViewActions.click()
                )
            )

        onView(withId(R.id.tv_post_user_name))
            .perform(ViewActions.click())

        checkTextView(R.id.tv_user_name)

        checkTextView(R.id.tv_user_email)

        checkTextView(R.id.tv_user_address)

        checkTextView(R.id.tv_user_company)

        checkRecyclerView(R.id.rv_album_list)
    }

    @Test
    fun loadPhotoListActivity() {
        onView(withId(R.id.rv_post_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..20).random(),
                    ViewActions.click()
                )
            )

        onView(withId(R.id.tv_post_user_name))
            .perform(ViewActions.click())

        onView(withId(R.id.rv_album_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..5).random(),
                    ViewActions.click()
                )
            )

        checkRecyclerView(R.id.rv_photo_list)
    }

    @Test
    fun loadPhotoViewActivity() {
        onView(withId(R.id.rv_post_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..20).random(),
                    ViewActions.click()
                )
            )

        onView(withId(R.id.tv_post_user_name))
            .perform(ViewActions.click())

        onView(withId(R.id.rv_album_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..5).random(),
                    ViewActions.click()
                )
            )

        onView(withId(R.id.rv_photo_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    (0..5).random(),
                    ViewActions.click()
                )
            )

        onView(withId(R.id.photo_view)).check(matches(isDisplayed()))

        onView(withId(R.id.photo_view)).check(matches(not(withResourceName(""))))
    }
}