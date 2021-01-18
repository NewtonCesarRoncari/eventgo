package com.newton.eventgo.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.newton.eventgo.R
import com.newton.eventgo.view.fragment.LoginFragment
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CasesFragmentTest {

    @get:Rule
    val fragment = FragmentTestRule(NavigationActivity::class.java, LoginFragment::class.java)

    @Test
    fun clickLoginNoInputData() {
        clickButtonLogin()
    }

    @Test
    fun clickLoginWithOnlyInputName() {
        insertNameField("John")
        clickButtonLogin()
    }

    @Test
    fun clickLoginWithInputNameAndSpaces() {
        insertNameField("John Marston")
        clickButtonLogin()
    }

    @Test
    fun clickLoginWithOnlyInputEmailInvalid() {
        insertEmailField("John")
        clickButtonLogin()
    }

    @Test
    fun clickLoginWithOnlyInputEmailValid() {
        insertEmailField("John@email.com")
        clickButtonLogin()
    }

    @Test
    fun clickLoginWithValidNameAndEmail() {
        insertNameField("John")
        insertEmailField("John@email.com")
        clickButtonLogin()
    }

    private fun insertNameField(string: String) {
        val textInputEditText = onView(
            allOf(
                withId(R.id.login_name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.loginNameTextInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(ViewActions.replaceText(string), ViewActions.closeSoftKeyboard())
    }

    private fun insertEmailField(string: String) {
        val textInputEditText = onView(
            allOf(
                withId(R.id.login_email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.loginEmailTextInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(ViewActions.replaceText(string), ViewActions.closeSoftKeyboard())
    }

    private fun clickButtonLogin() {
        val materialButton = onView(
            allOf(
                withId(R.id.login_btn), withText("login"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.frame_navigation),
                        1
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
