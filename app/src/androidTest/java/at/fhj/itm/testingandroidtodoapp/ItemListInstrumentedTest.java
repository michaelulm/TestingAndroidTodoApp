package at.fhj.itm.testingandroidtodoapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import static at.fhj.itm.testingandroidtodoapp.R.menu.main;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItemListInstrumentedTest {

  @Rule
  public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

  @Test
  public void testNumberOfInitialItems() throws Exception {
    ListView listview = (ListView) main.getActivity().findViewById(R.id.lvItems);
    assertThat(listview.getCount(), is(3));
  }
}