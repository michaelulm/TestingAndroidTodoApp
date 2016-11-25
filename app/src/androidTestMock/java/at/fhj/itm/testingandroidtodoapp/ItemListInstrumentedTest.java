package at.fhj.itm.testingandroidtodoapp;

import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * tests current ListView and initial state,
 * this test is created for usage with mock flavor
 * @author Michael Ulm
 */
public class ItemListInstrumentedTest {

  @Rule
  public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

  @Test
  public void testNumberOfInitialItems() throws Exception {
    ListView listview = (ListView) main.getActivity().findViewById(R.id.lvItems);
    assertThat(listview.getCount(), is(3));
  }
}