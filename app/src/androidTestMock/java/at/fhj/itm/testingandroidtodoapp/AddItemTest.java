package at.fhj.itm.testingandroidtodoapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * tests add method for new todo item,
 * this test is created for usage with mock flavor
 * @author Michael Ulm
 */
@RunWith(AndroidJUnit4.class)
public class AddItemTest {

    public static final String STRING_TO_BE_TYPED = "Nice!";
    public static final String STRING_NOT_EXISTS = "This String should NOT exists!";

    /**
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * adds Item to the ListView, clicks and verify the correct value
     */
    @Test
    public void addItem() {
        // Types the pre-defined text
        onView(withId(R.id.etNewItem))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        // after typing we perform button click for adding text
        onView(withId(R.id.btnAddItem)).perform(click());

        // check 4. entry, because 3 Demo Entries already exists
        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(3)
                .check(matches(withText(containsString(STRING_TO_BE_TYPED))));

        // activate line to see test will fail
        //onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(3).check(matches(withText(containsString(STRING_NOT_EXISTS))));
        //onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(3).check(matches(withText(containsString(STRING_NOT_EXISTS))));

        // triggers click on item
        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(3)
                .perform(click());

        /* modified source, so currently nothing appears at click on list item

            // only finds current text ..
            onView(withId(R.id.etNewItem))
                    .check(matches(withText(containsString(STRING_TO_BE_TYPED))));

            // .. and verify that entry is displayed on the screen
            onView(withId(R.id.etNewItem))
                    .check(matches(withText(containsString(STRING_TO_BE_TYPED))))
                    .check(matches(isDisplayed()));
        */

        // onView(withId(R.id.lvItems)).check(matches(withText(STRING_TO_BE_TYPED))); => will not work, because it's a list
        // take care that espresso returns a String Information about the complete Object at matching texts!
    }


    /**
     * adds Item to the ListView, clicks and verify the correct value by find view
     */
    @Test
    public void addItemAndFindText() {
        // Types the pre-defined text
        onView(withId(R.id.etNewItem))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        // after typing we perform button click for adding text
        onView(withId(R.id.btnAddItem)).perform(click());

        // check by find on view
        onView(withText(containsString(STRING_TO_BE_TYPED)))
                .check(matches(isDisplayed()));
    }

}
