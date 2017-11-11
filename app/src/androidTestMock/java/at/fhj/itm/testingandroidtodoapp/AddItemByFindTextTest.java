package at.fhj.itm.testingandroidtodoapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

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
public class AddItemByFindTextTest {

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
     * prepare FileStorage with some items, beforeClass needed because otherwise ActivityTestRule will be initialized before
     */
    @BeforeClass
    public static void setupBeforeRun(){
        ArrayList<String> tmpItems = new ArrayList<>();
        tmpItems.add("Awesome Lesson");
        tmpItems.add("Setup Test Method");
        tmpItems.add("Make a joke!");
        FileStorage.getInstance().writeItems(tmpItems);
    }

    /**
     * adds Item to the ListView, clicks and verify the correct value by find view
     */
    @Test
    public void addItemAndFindText() {
        // Types the pre-defined text
        onView(withId(R.id.etNewItem))
                // VIEW MATCHER => method to locate a view within the current view hierarchy
                //
                // matcher will (hopefully) find a matching view, e.g. find a view "withId"

                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
                // VIEW ACTIONS => methods which can be passed by perform(...), e.g. "click" or "typeText"
                //
                // actions normally "do something"


        // after typing we perform button click for adding text
        onView(withId(R.id.btnAddItem)).perform(click());

        // check by find on view
        onView(withText(containsString(STRING_TO_BE_TYPED)))


                .check(matches(isDisplayed()));
                // VIEW ASSERTIONS => Most of the time, you will use the "matches" assertion,
                //                    which uses a View matcher to assert the state of the currently selected view.
                //
                // assertions will check the state with another reference
                // => if this matches it's a positive result (expected), in other case a negative result (not expected)
    }

}
