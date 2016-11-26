package at.fhj.itm.testingandroidtodoapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static at.fhj.itm.testingandroidtodoapp.R.menu.main;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * tests removing an existing item from todo list,
 * this test is created for usage with mock flavor
 * @author Michael Ulm
 */
@RunWith(AndroidJUnit4.class)
public class RemoveItemTest {

    public static final String STRING_TO_BE_TYPED = "Nice!";
    public static final String STRING_TO_FIND = "Awesome!";

    /**
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

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
     * removes Item from the ListView and verify correct number of items in list through after and before assert of current list
     */
    @Test
    public void removeItem() {

        ListView listview = (ListView) mActivityRule.getActivity().findViewById(R.id.lvItems);

        // first check size of current list
        assertThat(listview.getCount(), is(3));

        // add new Item to existing list
        onView(withId(R.id.etNewItem))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.btnAddItem)).perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(3)
                .check(matches(withText(containsString(STRING_TO_BE_TYPED))));

        // after add new item check size of current list again
        assertThat(listview.getCount(), is(4));

        // remove item with long touch
        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(3)
                .perform(longClick());


        // after removing list should contain only 3 items again
        assertThat(listview.getCount(), is(3));

    }

    /**
     * removes Item from the ListView (by find them by String Value) and verify correct number of items in list through after and before assert of current list
     */
    @Test
    public void findAndRemoveItem() {

        ListView listview = (ListView) mActivityRule.getActivity().findViewById(R.id.lvItems);

        // first check size of current list
        assertThat(listview.getCount(), is(3));

        // add new Item to existing list
        onView(withId(R.id.etNewItem))
                .perform(typeText(STRING_TO_FIND), closeSoftKeyboard());
        onView(withId(R.id.btnAddItem)).perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(3)
                .check(matches(withText(containsString(STRING_TO_FIND))));

        // after add new item check size of current list again
        assertThat(listview.getCount(), is(4));

        // remove item with long touch
        onView(withText(containsString(STRING_TO_FIND)))
                .perform(longClick());

        // after removing list should contain only 3 items again
        assertThat(listview.getCount(), is(3));

    }

    /**
     * removes all predefined items from the list and checks correct implementation of remove method at long click
     */
    @Test
    public void removeAllItems(){

        ListView listview = (ListView) mActivityRule.getActivity().findViewById(R.id.lvItems);

        // starts with three items
        assertThat(listview.getCount(), is(3));

        // remove item with long touch
        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(2)
                .perform(longClick());

        // after removing item, list should contain only 2 items
        assertThat(listview.getCount(), is(2));

        // remove item with long touch
        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(1)
                .perform(longClick());

        // after removing item, list should contain only 1 items
        assertThat(listview.getCount(), is(1));

        // remove item with long touch
        onData(anything())
                .inAdapterView(withId(R.id.lvItems))
                .atPosition(0)
                .perform(longClick());

        // after removing item, list should be empty
        assertThat(listview.getCount(), is(0));
    }

    /**
     * currently needed to write Items AFTER Test => Needed because of my implementation TODO do another Refactoring
     */
    @After
    public void teardown(){
         ArrayList<String> tmpItems = new ArrayList<>();
            tmpItems.add("Awesome Lesson");
            tmpItems.add("Setup Test Method");
            tmpItems.add("Make a joke!");
            FileStorage.getInstance().writeItems(tmpItems);
    }

}
